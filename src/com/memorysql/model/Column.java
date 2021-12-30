package com.memorysql.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.memorysql.model.constraint.Constraint;

public class Column {
	Type type;
	private String name;
	private Set<Constraint> cons;
	public Column(String name, Type type) {
		this.name = name;
		this.type = type;
		this.cons = new HashSet<>();
	}

	public Type getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public Set<Constraint> getCons() {
		return cons;
	}

	public void setCons(Set<Constraint> cons) {
		this.cons = cons;
	}

	public void addCons(Constraint con) {
		cons.add(con);
	}

	public void validate(Object value) {
		Iterator<Constraint> it = cons.iterator();
		while (it.hasNext()) {
			if (!it.next().validate(value)) {
				throw new RuntimeException("Constraint violation");
			}
		}
	}
}
