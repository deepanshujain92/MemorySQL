package com.memorysql.model.constraint;

public class StringConstrain implements Constraint {

	@Override
	public boolean validate(Object object) {
		return object.getClass().equals(String.class) && object.toString().length() <=20;
 	}

}
