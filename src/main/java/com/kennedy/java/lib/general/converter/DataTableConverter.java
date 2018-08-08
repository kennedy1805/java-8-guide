package com.kennedy.java.lib.general.converter;

import java.util.Arrays;

public class DataTableConverter {

	public DataTable convertToTable(String tableHavingOneRow) {
		
		String[] lines = tableHavingOneRow.split("\n");
		int rowNum = lines.length;
		DataTable datatable = DataTable.createNew();
		for (int i = 0; i < rowNum; i++) {
			String line = lines[i];
			String[] column = line.trim().substring(1, line.length()-1).split("\\|");
			for (int j = 0; j < column.length; j++) {
				column[j] = column[j].trim().length() == 0 ? null : column[j].trim();
			}
			if (column.length == 0) {
				continue;
			}
			datatable.addNewRow(Arrays.asList(column));
		}
		
		return datatable;
	}

}
