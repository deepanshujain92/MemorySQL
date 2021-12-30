package com.memorysql.model;

import java.util.*;

public class Row {
	
	public Row(Integer rowId, Map<String, Object> values) {
		this.values = values;
		this.rowId = rowId;
	}
	
	private Integer rowId;
	
	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public Map<String, Object> getValues() {
		return values;
	}

	public void setValues(Map<String, Object> values) {
		this.values = values;
	}

	private Map<String, Object> values;
	
	

	@Override
	public String toString() {
		return "Row [rowId=" + rowId + ", values=" + values + "]";
	}
	

}
