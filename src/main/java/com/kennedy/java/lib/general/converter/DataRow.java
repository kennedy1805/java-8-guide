package com.kennedy.java.lib.general.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataRow {

	private List<String> cells;
	
	public DataRow(String... cellsValue) {
		cells = new ArrayList<>();
		cells.addAll(Arrays.asList(cellsValue));
	}

	public DataRow(List<String> row) {
		cells = new ArrayList<>();
		cells.addAll(row);
	}

	public List<String> getCells() {
		return cells;
	}

	public void setCells(List<String> cells) {
		this.cells = cells;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((cells == null) ? 0 : cells.hashCode());
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
		DataRow other = (DataRow) obj;
		if (cells == null) {
			if (other.cells != null)
				return false;
		} else if (!cells.equals(other.cells))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DataRow [cells=" + cells + "]";
	}
	
}
