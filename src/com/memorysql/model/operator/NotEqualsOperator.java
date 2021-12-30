package com.memorysql.model.operator;

public class NotEqualsOperator extends Operator {

	public NotEqualsOperator() {
		this.name = "not equals";
		this.sign = "!=";
	}

	@Override
	public boolean applyOp(Object filterValue, Object value) {
		return !(filterValue.equals(value)|| filterValue == value);
	}

}
