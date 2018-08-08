/*
 * Copyright(c)2016 by AXON IVY AG, CH-6000 Lucerne. http://www.axonivy.com
 * 
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of
 * AXON IVY AG. You shall not disclose such confidential information and
 * shall use it only in accordance with the terms of the license
 * agreement you entered into with AXON IVY AG.
 */
package com.java.ee.working.beans;

import static com.java.ee.working.beans.BeanCopier.Direction.IN;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;

public class FieldCopier {

    static final Map<Class, Translator> TRANSLATORS_FOR_MUTABLE_TYPES = new HashMap<>();

    static {
        TRANSLATORS_FOR_MUTABLE_TYPES.put(BigDecimal.class, new Translator<BigDecimal, BigDecimal>() {
            @Override
            public BigDecimal translateIn(BigDecimal source) {
                return new BigDecimal(source.unscaledValue(), source.scale());
            }

            @Override
            public BigDecimal translateOut(BigDecimal source) {
                return translateIn(source);
            }
        });
        TRANSLATORS_FOR_MUTABLE_TYPES.put(Date.class, new Translator<Date, Date>() {
            @Override
            public Date translateIn(Date source) {
                return new Date(source.getTime());
            }

            @Override
            public Date translateOut(Date source) {
                return translateIn(source);
            }
        });
        TRANSLATORS_FOR_MUTABLE_TYPES.put(Locale.class, new Translator<Locale, Locale>() {
            @Override
            public Locale translateIn(Locale source) {
                return (Locale) source.clone();
            }

            @Override
            public Locale translateOut(Locale source) {
                return translateIn(source);
            }
        });
    }
    final Field fieldDefinition;
    final BeanCopier.Direction direction;
    final Object referenceBean;
    String referenceFieldName;
    final Object referencedBean;
    String referencedFieldName;
    final Mapped mapped;

    public FieldCopier(Object referenceBean, Object referencedBean, Field fieldDefinition, Mapped mapped, BeanCopier.Direction direction) {
        this.referenceBean = referenceBean;
        this.referencedBean = referencedBean;
        this.fieldDefinition = fieldDefinition;
        this.direction = direction;
        this.mapped = mapped;
    }

    public void handleProperty() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, NoSuchFieldException {
        referencedFieldName = mapped.name();
        if (referencedFieldName.length() == 0) {
            referencedFieldName = fieldDefinition.getName();
        }
        referenceFieldName = fieldDefinition.getName();
        Object sourceValue;
        Class targetType;
        if (direction == IN) {
            sourceValue = PropertyUtils.getProperty(referencedBean, referencedFieldName);
            targetType = fieldDefinition.getType();
            PropertyUtils.setProperty(referenceBean, referenceFieldName, getTargetValue(sourceValue, targetType, mapped.translator()));
        } else {
            sourceValue = PropertyUtils.getProperty(referenceBean, referenceFieldName);
            targetType = PropertyUtils.getPropertyType(referencedBean, referencedFieldName);
            PropertyUtils.setProperty(referencedBean, referencedFieldName, getTargetValue(sourceValue, targetType, mapped.translator()));
        }
    }

    Object getTargetValue(Object sourceValue, Class targetType, Class<? extends Translator> translatorType)
            throws IllegalAccessException, InstantiationException {
        if (sourceValue == null) {
            return null;
        }
        Object targetValue = null;
        Translator translator = getTranslator(translatorType);
        if (translator != null) {
            targetValue = translate(sourceValue, translator);
        } else if (isImmutableType(targetType) || targetType.isEnum()) {
            targetValue = sourceValue;
        } else if (Collection.class.isAssignableFrom(targetType)) {
            Class targetElementType = (direction == IN) ? getCollectionElementType(fieldDefinition)
                    : getCollectionElementType(getDeclaredFieldInHierarchy(referencedBean.getClass(), referencedFieldName));
            targetValue = getValueFromCollectionType(sourceValue, targetType, targetElementType);
        } else {
            Translator mutableTypeTranslator = TRANSLATORS_FOR_MUTABLE_TYPES.get(targetType);
            targetValue = translateForMutableType(sourceValue, targetType, mutableTypeTranslator);
        }
        return targetValue;
    }

    private Object translateForMutableType(Object sourceValue, Class targetType, Translator mutableTypeTranslator) {
        Object targetValue;
        if (mutableTypeTranslator != null) {
            targetValue = mutableTypeTranslator.translateIn(sourceValue);
        } else {
            targetValue = (direction == IN) ? BeanCopier.in(sourceValue, targetType) : BeanCopier.out(sourceValue, targetType);
        }
        return targetValue;
    }

    private Object getValueFromCollectionType(Object sourceValue, Class targetType, Class targetElementType)
            throws IllegalAccessException, InstantiationException {
        Object targetValue;
        Collection sourceCollection = (Collection) sourceValue;
        Collection targetCollection = newInstance(targetType);
        for (Object sourceElementValue : sourceCollection) {
            Object targetElementValue = getTargetValue(sourceElementValue, targetElementType, mapped.elementTranslator());
            targetCollection.add(targetElementValue);
        }
        targetValue = targetCollection;
        return targetValue;
    }

    private Object translate(Object sourceValue, Translator translator) {
        return (direction == IN) ? translator.translateIn(sourceValue) : translator.translateOut(sourceValue);
    }
    
    private <T extends Collection> Collection newInstance(Class<T> clazz) {
    	return Set.class.isAssignableFrom(clazz) ? new HashSet<>() : new ArrayList<>(); 
    }
    
    private Field getDeclaredFieldInHierarchy(Class targetType, String fieldName) {
    	while (targetType != null && !targetType.equals(Object.class)) {
    		Field fieldFilter = Arrays.asList(targetType.getDeclaredFields()).stream()
    			.filter(field -> field.getName().equals(fieldName)).findAny().orElse(null);
    		
    		if (fieldFilter != null) {
    			return fieldFilter;
    		}
            targetType = targetType.getSuperclass();
        }
    	
    	return null;
    }

    static Class getCollectionElementType(Field collectionField) {
        ParameterizedType targetElementTypes = (ParameterizedType) collectionField.getGenericType();
        return (Class) targetElementTypes.getActualTypeArguments()[0];
    }

    static Translator getTranslator(Class<? extends Translator> translatorType) throws IllegalAccessException, InstantiationException {
        if (translatorType != null && (!translatorType.equals(Translator.class))) {
            return translatorType.newInstance();
        }
        return null;
    }

    static boolean isImmutableType(Class type) {
        return type.isPrimitive()
                || type == Double.class
                || type == Float.class
                || type == Long.class
                || type == Integer.class
                || type == Short.class
                || type == Character.class
                || type == Byte.class
                || type == Boolean.class
                || type == String.class
                || type == LocalDate.class
                || type == LocalTime.class
                || type == LocalDateTime.class;
    }

}
