package com.kennedy.java.lib.general.converter;

import java.util.ArrayList;
import java.util.List;

public class DataTable {

	private List<DataRow> rows;
	private int maxColSize;
	
	public DataTable() {
		rows = new ArrayList<>();
		maxColSize = 0;
	}
	
	public static DataTable createNew() {
		return new DataTable();
	}

	public DataTable addNewRow(DataRow dataRow) {
		if (dataRow == null || isColumnOutOfTable(dataRow.getCells().size())) {
			throw new IllegalArgumentException();
		}
		if (rows.isEmpty()) {
			maxColSize = dataRow.getCells().size();
		}
		rows.add(dataRow);
		return this;
	}

	public void addNewRow(List<String> row) {
		if (row == null || isColumnOutOfTable(row.size())) {
			throw new IllegalArgumentException();
		}
		if (rows.isEmpty()) {
			maxColSize = row.size();
		}
		rows.add(new DataRow(row));
	}
	
	private boolean isColumnOutOfTable(int columnSize) {
		return columnSize > maxColSize && maxColSize != 0;
	}

	public List<DataRow> getRows() {
		return rows;
	}

	public void setRows(List<DataRow> rows) {
		this.rows = rows;
	}

	public String getCell(int rowIndex, int colIndex) {
		if (rowIndex > rows.size() - 1 || rows.get(rowIndex) == null || colIndex > maxColSize) {
			throw new IllegalArgumentException();
		}
		return rows.get(rowIndex).getCells().get(colIndex);
	}

	public int getMaxColSize() {
		return maxColSize;
	}

	public void setMaxColSize(int maxColSize) {
		this.maxColSize = maxColSize;
	}

	@Override
	public String toString() {
		return "DataTable [rows=" + rows + ", maxColSize=" + maxColSize + "]";
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + maxColSize;
		result = prime * result + ((rows == null) ? 0 : rows.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataTable other = (DataTable) obj;
		if (maxColSize != other.maxColSize)
			return false;
		if (rows == null) {
			if (other.rows != null)
				return false;
		} else if (!rows.equals(other.rows))
			return false;
		return true;
	}
	
}
