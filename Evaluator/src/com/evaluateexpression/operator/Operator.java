package com.evaluateexpression.operator;

public abstract class Operator {

	private String Symbol;
	private int precedence;


	public abstract Number operate(Number expr1,Number expr2);

	public abstract Number operate(Number expr1);

	public String getSymbol() {
		return Symbol;
	}

	public void setSymbol(String symbol) {
		Symbol = symbol;
	}

	@Override
	public String toString() {
		return "Operator [Symbol=" + Symbol + "]";
	}

	public int getPrecedence() {
		return precedence;
	}

	public void setPrecedence(int precedence) {
		this.precedence = precedence;
	}

}
