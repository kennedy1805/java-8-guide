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
import static com.java.ee.working.beans.BeanCopier.Direction.OUT;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BeanCopier {

    static enum Direction {
        IN, OUT
    };

    public static <T> List<T> inList(List source, Class<T> targetElementClass) {
        if (source == null) {
            return null;
        }

        List<T> targetList = new ArrayList<>();
        source.forEach(sourceElement -> {
            targetList.add(in(sourceElement, targetElementClass));
        });
        return targetList;
    }

    public static <T> Set<T> inSet(Set source, Class<T> targetElementClass) {
        if (source == null) {
            return null;
        }

        Set<T> targetList = new HashSet<>();
        source.forEach(sourceElement -> {
            targetList.add(in(sourceElement, targetElementClass));
        });
        return targetList;
    }

    public static <T> T in(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }

        try {
            T target = targetClass.newInstance();
            return in(source, target);
        } catch (ReflectiveOperationException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> T in(Object source, T target) {
        if (source == null) {
            return target;
        }

        try {
            List<Field> targetFieldList = getDeclaredFieldsInHierarchy(target);

            for (Field targetField : targetFieldList) {
                Mapped mapped = getRelevantMapped(targetField, source);
                if (mapped != null) {
                    FieldCopier fc = new FieldCopier(target, source, targetField, mapped, IN);
                    fc.handleProperty();
                }
            }
            return target;
        } catch (ReflectiveOperationException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> List<T> outList(List source, Class<T> targetElementClass) {
        if (source == null) {
            return null;
        }

        List<T> targetList = new ArrayList<>();
        source.forEach(sourceElement -> {
            targetList.add(out(sourceElement, targetElementClass));
        });
        return targetList;
    }

    public static <T> Set<T> outSet(Set source, Class<T> targetElementClass) {
        if (source == null) {
            return null;
        }

        Set<T> targetList = new HashSet<>();
        source.forEach(sourceElement -> {
            targetList.add(out(sourceElement, targetElementClass));
        });
        return targetList;
    }

    public static <T> T out(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }

        try {
            T target = targetClass.newInstance();
            return out(source, target);
        } catch (ReflectiveOperationException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> T out(Object source, T target) {
        if (source == null) {
            return target;
        }

        try {
            List<Field> targetFieldList = getDeclaredFieldsInHierarchy(source);
            for (Field targetField : targetFieldList) {
                Mapped mapped = getRelevantMapped(targetField, target);
                if (mapped != null) {
                    FieldCopier fc = new FieldCopier(source, target, targetField, mapped, OUT);
                    fc.handleProperty();
                }
            }
            return target;
        } catch (ReflectiveOperationException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static <T> List<Field> getDeclaredFieldsInHierarchy(T target) throws SecurityException {
        List<Field> targetFieldList = new ArrayList<>();
        Class currentClass = target.getClass();
        while (currentClass != null && !currentClass.equals(Object.class)) {
            targetFieldList.addAll(Arrays.asList(currentClass.getDeclaredFields()));
            currentClass = currentClass.getSuperclass();
        }
        return targetFieldList;
    }

    static <T> Mapped getRelevantMapped(Field fieldDefintion, Object referencedBean) {
        Mapped mapped = fieldDefintion.getAnnotation(Mapped.class);
        if (!isRelevantMapped(mapped, referencedBean)) {
            mapped = null;
        }
        if (mapped == null) {
            Mappeds mappeds = fieldDefintion.getAnnotation(Mappeds.class);
            if (mappeds != null) {
                for (Mapped m : mappeds.value()) {
                    if (isRelevantMapped(m, referencedBean)) {
                        mapped = m;
                        break;
                    }
                }
            }
        }
        return mapped;
    }

    static boolean isRelevantMapped(Mapped mapped, Object referencedBean) {
        return mapped != null && mapped.other().isAssignableFrom(referencedBean.getClass());
    }

}
