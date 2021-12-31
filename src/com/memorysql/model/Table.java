package com.memorysql.model;

import java.util.*;

public class Table {
	private String tableName;
	private HashMap<Integer, Row> rows;
	private LinkedHashMap<String, Column> columns;
	private Integer autoId;

	Table(String tableName) {
		this.tableName = tableName;
		this.rows = new HashMap<>();
		this.columns = new LinkedHashMap<>();
		this.autoId = 0;
	}
	
	public Map<String, Column> getColumns() {
		return columns;
	}
	
	public String getTableName() {
		return tableName;
	}

	public HashMap<Integer, Row> getRows() {
		return rows;
	}

	public void setRows(HashMap<Integer, Row> rows) {
		this.rows = rows;
	}

	public Integer getAutoId() {
		synchronized (autoId) {
			return autoId++;
		}
	}

	public void setAutoId(Integer autoId) {
		this.autoId = autoId;
	}	
	
	public boolean insert(LinkedHashMap<String, Object> values) {
		Integer id = this.getAutoId();
		Set<String> colNames = columns.keySet();
		for(String columnName: colNames) {
			columns.get(columnName).validate(values.get(columnName));
		}
		
		Row row = new Row(id, values);
		rows.put(id, row);
		
		return true;
	}
	
	public void deleteRow(int rowId) {
		rows.remove(rowId);
	}
	
	public void printAllRecords() {
		System.out.print(rows);
	}
    
	public Column createColumn(String columnName, Type type) {
		if(columns.containsKey(columnName)) {
			System.out.print("Column Already Exist");
			return null;
		}
		Column column = new Column(columnName, type);
		columns.put(columnName, column);
		return column;
	}
	
	public void Query(String query) {
		
		
	}
	
}
