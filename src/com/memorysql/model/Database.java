package com.memorysql.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.memorysql.model.operator.Operator;

import java.util.Map.Entry;

public class Database {
	private String name;
	private static Map<String, Database> dbMap = new ConcurrentHashMap<>();

	private Database(String dbName) {
		this.name = dbName;
		this.tables = new HashMap<String, Table>();
	}

	public static Database getInstance(String dbName) {
		if (!dbMap.containsKey(dbName)) {
			dbMap.put(dbName, new Database(dbName));
		}
		return dbMap.get(dbName);
	}

	public String getName() {
		return name;
	}

	private Map<String, Table> tables;

	public Table createTable(String tableName) {
		tables.put(tableName, new Table(tableName));
		return tables.get(tableName);
	}

	public Table getTable(String tableName) {
		return tables.get(tableName);
	}

	public void deleteTable(String tableName) {
		tables.remove(tableName);
	}

	public List<Row> Query(String tableName, Object value, String columnname, String columnsToSelect, Operator op) {
		List<Row> results = new ArrayList<Row>();
		Set<Entry<Integer, Row>> data = tables.get(tableName).getRows().entrySet();
		for (Map.Entry<Integer, Row> entry : data) {
			if(op.applyOp(entry.getValue().getValues().get(columnname), value)) 
				results.add(entry.getValue());		
		}
		return results;
	}
}
