package com.evaluateexpression.operator;

public abstract class BinaryOperator extends Operator {

	public abstract Number operate(Number number,Number number2);


	public int getPrecedence() {
		return super.getPrecedence();
	}

	public void setPrecedence(int precedence) {
		super.setPrecedence(precedence);
	}
}