package com.kennedy.java.lib.general.converter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import com.java.ee.rest.handler.exeption.SystemErrorException;
import com.java.ee.rest.handler.exeption.ValidationException;

public class DataTableUtils {
	private static final int NUMBER_FOUR = 2;
	private static final String SNIPPET_FIELD_NAME_I18NS_PROPERTY_NAME = "snippetFieldNameI18ns";
	private static final String POSTING_TEXT_I18NS_PROPERTY_NAME = "postingTextI18ns";
	private static final char COLON = ':';
    private static final char TABLE_NEW_ROW = '\n';
    private static final int TABLE_NEW_ROW_LENGTH = 1;
    private static final char COLUMN_SEPARATOR = '|';
    private static final String EMPTY_STRING = "    ";
    private static final String LABEL_HEADER_YAML_STR = "label_";
    
	private static final Logger LOGGER = Logger.getLogger(DataTableUtils.class.getName());
	
	private DataTableUtils() {
		//Utility classes should not have public constructors
	}
	
	public static <T> List<T> extractToListObject(DataTable dataTable, Class<T> clazz) {
		DataRow header = dataTable.getRows().get(0);
		int colSize = header.getCells().size();
		int beginCol = 0;
		List<Map<String, String>> listObjectsByMap = new ArrayList<>(dataTable.getRows().size());
		
		for (int rowIdx = 1; rowIdx < dataTable.getRows().size(); rowIdx++) {
			Map<String, String> mapValue = new HashMap<>(colSize);
			DataRow rowIns = dataTable.getRows().get(rowIdx);
			for (int colIdx = beginCol; colIdx < colSize; colIdx++) {
				String title = header.getCells().get(colIdx);
				String value = rowIns.getCells().get(colIdx);
				mapValue.put(title.replace(":", "").toLowerCase(Locale.ENGLISH), value);
			}
			listObjectsByMap.add(mapValue);
		}
		return getListObjectFromMap(listObjectsByMap, clazz);
	}
	
	private static <T> List<T> getListObjectFromMap(List<Map<String, String>> objectByMap, Class<T> clazz) {
		List<Field> fieldsBelongToClass = Arrays.asList(clazz.getDeclaredFields());
		List<T> objects = new ArrayList<>();
		try {
			for (Map<String, String> mapObject : objectByMap) {
				objects.add(extractValueFromMap(mapObject, fieldsBelongToClass, clazz));
			}
		} catch (InstantiationException | IllegalAccessException e) {
			throw new SystemErrorException(e.getMessage(), e);
		}

		return objects;
	}

	private static <T> T extractValueFromMap(Map<String, String> snippetdataField, List<Field> fieldInClass, Class<T> clazz) throws InstantiationException, IllegalAccessException {
		T object = clazz.newInstance();
		for (Field declaredField : fieldInClass) {
			declaredField.setAccessible(true);
			Object valueOfCurrentProperty = getValueCurrentPropertieInYamlSnippetField(declaredField, snippetdataField);
			if (valueOfCurrentProperty == null || String.valueOf(valueOfCurrentProperty).isEmpty()) {
				continue;
			}
			declaredField.set(object, valueOfCurrentProperty);
		}
		return object;
	}
	
	private static Object getValueCurrentPropertieInYamlSnippetField(Field currentField, Map<String, String> fields) {
        if (currentField == null) {
        	return null;
        }
        String fieldName = currentField.getName().toLowerCase(Locale.ENGLISH);
		if (fieldName.endsWith("i18ns")) {
            return createI18N(fields);
        } 
        if (currentField.isAnnotationPresent(DataTableMapped.class)) {
            return resolveValueIfThatFieldIsMarkedYamlAnnotation(currentField, fields);
        }
        if (currentField.getType().isEnum()) {
			return getValueFromEnumClass(currentField, fields.get(fieldName));
		}
        return fields.get(fieldName);
    }

	private static Object resolveValueIfThatFieldIsMarkedYamlAnnotation(Field currentField, Map<String, String> fields) {
		DataTableMapped yaml = currentField.getAnnotation(DataTableMapped.class);
		String nameOfField = yaml.name().toLowerCase(Locale.ENGLISH);
		if (StringUtils.isBlank(nameOfField)) {
			nameOfField = currentField.getName().toLowerCase(Locale.ENGLISH);
		} 
		if (!currentField.getType().isEnum()) {
			return fields.get(nameOfField);
		}
		return getValueFromEnumClass(currentField, fields.get(nameOfField));
	}
	
	private static Object getValueFromEnumClass(Field currentField, String value) {
		try {	
			Class<?> enumClass = currentField.getType();
			Method method = enumClass.getMethod("valueFrom", String.class);
			method.setAccessible(true);
			return method.invoke(null, value);
		} catch (RuntimeException | ReflectiveOperationException exception) {
			LOGGER.log(Level.WARNING, exception.getMessage(), exception);
			throw new SystemErrorException(
					"There is problem when binding value to object from yaml, class=" + currentField.getType() + ", value=\"" + value + "\".", exception);
		}
	}

	private static Set<I18n> createI18N(Map<String, String> snippetdataField) {
        Set<I18n> snippetI18ns = new HashSet<>();
        for (Entry<String, String> e : snippetdataField.entrySet()) {
            if (e.getKey().startsWith(LABEL_HEADER_YAML_STR)) {
                I18n fieldNameI18nEntity = new I18n();
                fieldNameI18nEntity.setLocale(new Locale(e.getKey().replaceAll(LABEL_HEADER_YAML_STR, "")));
                if(!(e.getValue().isEmpty())){
                    fieldNameI18nEntity.setDescription((String) e.getValue());
                }
                snippetI18ns.add(fieldNameI18nEntity);
            }
        }
        return snippetI18ns;
    }
	
    protected static String formatTable(String dataTable) {
        List<String> rows = Arrays.asList(dataTable.split("\n"));
        Map<Long, Long> columnsMaxSize = findColumnsMaxSize(rows);
        Map<Long, List<String>> dataAfterFormat = new HashMap<>(rows.size());
        
        int rowIndex = 0;
        while (rowIndex < rows.size()) {
            String row = rows.get(rowIndex);
            int columnIndex = 0;
            List<String> cells = new ArrayList<>(Arrays.asList(row.split("\\|", -1)));
            cells.remove(0);
            List<String> cellsAfterFormat = new ArrayList<>(cells.size());
            while (columnIndex < cells.size() - 1) {
                String cell = cells.get(columnIndex);
                Long columnMaxSize = columnsMaxSize.get(Long.valueOf(columnIndex));
                cell = formatColumn(cell, columnMaxSize);
                columnIndex++;
                cellsAfterFormat.add(cell);
            }
            dataAfterFormat.put(Long.valueOf(rowIndex), cellsAfterFormat);
            rowIndex++;
        }

        return rebuildDataTableAfterFormat(dataAfterFormat);
    }

    private static String rebuildDataTableAfterFormat(Map<Long, List<String>> dataAfterFormat) {
        StringBuilder tableAfterFormat = new StringBuilder();
        int rowIndex = 0;
        while (rowIndex < dataAfterFormat.size()) {
            List<String> cells = dataAfterFormat.get(Long.valueOf(rowIndex));
            StringBuilder rowAfterFormat = new StringBuilder();
            rowAfterFormat.append('|');
            for (String cell : cells) {
                rowAfterFormat.append(cell).append('|');
            }
            tableAfterFormat.append(rowAfterFormat).append(TABLE_NEW_ROW);
            rowIndex++;
        }
        return tableAfterFormat.toString();
    }

    private static String formatColumn(String cellData, Long columnMaxSize) {
        String fixedSpacePrefix = StringUtils.repeat(" ", NUMBER_FOUR);
        Long columnMaxSizeAfterAddSpace = columnMaxSize + NUMBER_FOUR * 2;
        if (cellData == null) {
        	cellData = "";
        }
        Long numberOfSpaceToAdd = columnMaxSizeAfterAddSpace - cellData.length() - fixedSpacePrefix.length();
        return fixedSpacePrefix + cellData + StringUtils.repeat(" ", numberOfSpaceToAdd.intValue());
    }

    private static Map<Long, Long> findColumnsMaxSize(List<String> rows) {
        Map<Long, Long> columnsMaxSize = new HashMap<>();
        for (String row : rows) {
            int columnIndex = 0;
            List<String> cells = new ArrayList<>(Arrays.asList(row.split("\\|")));
            cells.remove(0);
            while (columnIndex < cells.size()) {
                String cell = cells.get(columnIndex);
                Long cellLength;
                if (columnsMaxSize.get(Long.valueOf(columnIndex)) == null) {
                    cellLength = Long.valueOf(cell.length());
                } else {
                    cellLength = columnsMaxSize.get(Long.valueOf(columnIndex));
                    cellLength = cellLength.compareTo(Long.valueOf(cell.length())) < 0 ? cell.length() : cellLength;
                }
                columnsMaxSize.put(Long.valueOf(columnIndex), cellLength);
                columnIndex++;
            }
        }
        return columnsMaxSize;
    }
    
    public static <T> String convertFromListObjectToStringTable(List<T> inputObjs, Class<T> clazz) {
        List<Field> allFieldsInObject = Arrays.asList(clazz.getDeclaredFields());
        List<Field> workingFields = allFieldsInObject.stream()
                .filter(field -> !field.isAnnotationPresent(DataTableIgnored.class))
                .filter(field -> !field.isSynthetic())
                .collect(Collectors.toList());

        StringBuilder resultTable = new StringBuilder();
        StringBuilder lineHeader = new StringBuilder();
        lineHeader.append(COLUMN_SEPARATOR);
        workingFields.stream().forEach(field -> {
            field.setAccessible(true);
            if (Collection.class.isAssignableFrom(field.getType())) {
                lineHeader.append(handleCollectionField(inputObjs, field));
            } else {
                lineHeader.append(handleFieldName(field)).append(COLON).append(COLUMN_SEPARATOR);
            }
        });
        resultTable.append(lineHeader).append(TABLE_NEW_ROW);
        inputObjs.stream().forEach(obj -> resultTable.append(extractDataInSingleProperties(inputObjs, workingFields, obj)));
        resultTable.setLength(resultTable.length() - TABLE_NEW_ROW_LENGTH);

        return formatTable(resultTable.toString());
    }

    private static <T> String handleCollectionField(List<T> inputObjs, Field field) {
        StringBuilder lineHeader = new StringBuilder();
        Class<?> objElementType = getCollectionElementType(field);
        if (objElementType == I18n.class) {
        	String name = field.getName();
        	Set<Locale> allExistedLocale = getAllLocaleFromInputObjs(inputObjs, name);
        	if (name.equals(SNIPPET_FIELD_NAME_I18NS_PROPERTY_NAME)) {
				allExistedLocale.stream().forEach(locale -> lineHeader.append(LABEL_HEADER_YAML_STR).append(locale).append(COLON).append(COLUMN_SEPARATOR));
			} else if (name.equals(POSTING_TEXT_I18NS_PROPERTY_NAME)) {
				allExistedLocale.stream().forEach(locale -> lineHeader.append("PostingText_").append(locale).append(COLON).append(COLUMN_SEPARATOR));
			}
        }
        return lineHeader.toString();
    }

    private static <T> String extractDataInSingleProperties(List<T> inputObjs, List<Field> workingFields, T obj) {
        StringBuilder lineData = new StringBuilder();
        lineData.append(COLUMN_SEPARATOR);
        try {
            for (Field objField : workingFields) {
                if (Collection.class.isAssignableFrom(objField.getType())) {
                    lineData.append(getDataFromCollectionField(inputObjs, obj, objField));
                } else {
                    lineData.append(handleValue(obj, objField));
                    lineData.append(COLUMN_SEPARATOR);
                }
            }
        } catch (ReflectiveOperationException e) {
            throw new SystemErrorException("Can not parse " + obj.getClass() + "to yaml table", e);
        }

        lineData.append(TABLE_NEW_ROW);
        return lineData.toString();
    }

    @SuppressWarnings("unchecked")
    private static <T> String getDataFromCollectionField(List<T> inputObjs, T obj, Field objField) throws ReflectiveOperationException {
        StringBuilder lineData = new StringBuilder();
        Class<?> objElementType = getCollectionElementType(objField);
        if (objElementType == I18n.class) {
            Set<Locale> allExistedLocale = getAllLocaleFromInputObjs(inputObjs, objField.getName());
            Set<I18n> i18nCollection = (Set<I18n>) PropertyUtils.getProperty(obj, objField.getName());
            for (Locale locale : allExistedLocale) {
                Optional<I18n> optionalI18n = i18nCollection.stream().filter(i18n -> i18n.getLocale().equals(locale)).findFirst();
                if (optionalI18n.isPresent()) {
                    String columnData = optionalI18n.get().getDescription() == null ? EMPTY_STRING : optionalI18n.get().getDescription();
                    lineData.append(columnData);
                } else {
                    lineData.append(EMPTY_STRING);
                }
                lineData.append(COLUMN_SEPARATOR);
            }
        }
        return lineData.toString();
    }

    private static String handleValue(Object obj, Field objField) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Object objProperty = PropertyUtils.getProperty(obj, objField.getName());
        if (objField.getType() == Boolean.class) {
            return Boolean.TRUE.equals(objProperty) ? "yes" : "no";
        }

        return (objProperty == null ? EMPTY_STRING : objProperty.toString());
    }

    private static String handleFieldName(Field field) {
        if (field.isAnnotationPresent(DataTableMapped.class)) {
            DataTableMapped yaml = field.getAnnotation(DataTableMapped.class);
            return yaml.name();
        }
        return field.getName();
    }

    private static Set<Locale> getAllLocaleFromInputObjs(List<?> inputObjs, String i18nFieldName) {
        Set<Locale> results = new HashSet<>();
        
        try {
            for (Object obj : inputObjs) {
                Object objProperty = PropertyUtils.getProperty(obj, i18nFieldName);
                if (objProperty == null) {
                    continue;
                }
                Collection<?> i18nCollection = (Collection<?>) objProperty;
                i18nCollection.stream().forEach(rawI18n -> {
                    I18n i18n = (I18n) rawI18n;
                    results.add(i18n.getLocale());
                });
            }
        } catch (ReflectiveOperationException e) {
            throw new ValidationException("Can not parse i18nFieldName!", e);
        }
        return results;
    }

    private static Class<?> getCollectionElementType(Field collectionField) {
        ParameterizedType targetElementTypes = (ParameterizedType) collectionField.getGenericType();
        return (Class<?>) targetElementTypes.getActualTypeArguments()[0];
    }
}
