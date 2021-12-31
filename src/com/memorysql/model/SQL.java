package com.memorysql.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.memorysql.model.operator.Operator;

/*In-memory SQL-like Database
Problem Statement

The objective is to design and implement an in-memory SQL-like database, which should support the following set of operations / functionality:
  
It should be possible to create or delete tables in a database.
A table definition comprises columns which have types. They can also have constraints
The supported column types are string and int.
The string type can have a maximum length of 20 characters.
The int type can have a minimum value of -1024 and a maximum value of 1024.
Support for mandatory fields (tagging a column as required)
It should be possible to insert records in a table.
It should be possible to print all records in a table.
It should be possible to filter and display records whose column values match a given value.


// Table => {Employee}
// Columns = {Integer ID, String name, String department, Integer salary}
// Map 
// 1 -> {1,Deepanshu,Engineering,10000}
// filter=> {Deepanshu, =, name, Employee}
// map.data.filter(emp->emp(index).filter(deepanshu))
 * */

public class SQL {
	Database db;
	public SQL(Database db){
		this.db = db;
	}
	public List<Row> Query(String tableName, Object value, String columnname, String columnsToSelect, Operator op) {
		List<Row> results = new ArrayList<Row>();
		Set<Entry<Integer, Row>> data = db.getTable(tableName).getRows().entrySet();
		for (Map.Entry<Integer, Row> entry : data) {
			if (op.applyOp(entry.getValue().getValues().get(columnname), value))
				results.add(entry.getValue());
		}
		return results;
	}

	// INSERT INTO EMPLOYEE values("");
	public void insert(String insertQuery) {
		String[] splitedQuery = insertQuery.toLowerCase().split("\\s");
		String[] values = parseValues(splitedQuery[3]);
		Table table = db.getTable(splitedQuery[2]);
		if (table == null) {
			System.out.print("Table not found");
			return;
		}
		Set<String> columns = table.getColumns().keySet();

		if (values.length != columns.size()) {
			System.out.print("Values length invalid");
			return;
		}
		LinkedHashMap<String, Object> record = new LinkedHashMap<String, Object>();

		var wrapper = new Object() {
			int ordinal = 0;
		};
		columns.forEach(col -> insert(table, record, col, values[wrapper.ordinal++]));

		table.insert(record);
	}

	private void insert(Table table, Map<String, Object> record, String col, String value) {
		if (table.getColumns().get(col).type == Type.INTEGER)
			record.put(col, Integer.valueOf(value));
		else
			record.put(col, value);
	}

	private String[] parseValues(String splitedQuery) {
		return splitedQuery.replaceAll("values", "").replaceAll("\"", "").replaceAll("\\)", "").replaceAll("\"", "")
				.replaceAll("\\(", "").replaceAll(";", "").split(",");
	}

}
