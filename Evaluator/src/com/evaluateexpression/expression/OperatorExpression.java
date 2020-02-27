package com.evaluateexpression.expression;

import com.evaluateexpression.operator.Operator;

public abstract class OperatorExpression<T extends Operator> implements Expression{

	private T operator;
	private boolean isSigned;

	@Override
	public abstract Number evaluate();

	@Override
	public String toString() {
		return "OperatorExpression [operator=" + operator + "]";
	}

	public T getOperator() {
		return operator;
	}

	public void setOperator(T operator) {
		this.operator = operator;
	}

	public boolean isSigned() {
		return isSigned;
	}

	public void setSigned(boolean isSigned) {
		this.isSigned = isSigned;
	}



}
