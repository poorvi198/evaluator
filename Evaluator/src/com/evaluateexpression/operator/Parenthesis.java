package com.evaluateexpression.operator;

public class Parenthesis extends Operator {

	private boolean isSigned;
	
	@Override
	public Number operate(Number expr1, Number expr2) {
		return null;
	}

	@Override
	public Number operate(Number expr1) {
		return null;
	}

	public String getSymbol() {
		return super.getSymbol();
	}

	public void setSymbol(String symbol) {
		super.setSymbol(symbol);
	}

	public boolean isSigned() {
		return isSigned;
	}

	public void setSigned(boolean isSigned) {
		this.isSigned = isSigned;
	}
}
