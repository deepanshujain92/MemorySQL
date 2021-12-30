package com.memorysql.model.constraint;

public class IntegerConstraint implements Constraint{
	@Override
	public boolean validate(Object object) {
		return object.getClass().equals(Integer.class) && (int)(object) <=1024 && (int)(object) >=-1024;
 	}
}
