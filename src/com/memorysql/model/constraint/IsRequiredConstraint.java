package com.memorysql.model.constraint;

public class IsRequiredConstraint implements Constraint{

	@Override
	public boolean validate(Object object) {
		if(object == null || object == "")
			return false;
		return true;
	}

}
