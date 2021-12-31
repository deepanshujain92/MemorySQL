package com.memorysql.model;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Database {
	private static String currentDatabase;
	public static String getCurrentDatabase() {
		return currentDatabase;
	}

	public static void setCurrentDatabase(String currentDatabase) {
		Database.currentDatabase = currentDatabase;
		getInstance(Database.currentDatabase);
	}

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
}
