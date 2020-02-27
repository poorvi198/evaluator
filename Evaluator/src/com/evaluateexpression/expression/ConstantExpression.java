package com.evaluateexpression.expression;

public class ConstantExpression implements Expression {
	private Number value;
	private boolean isSigned; // negation

	public ConstantExpression(Number value) {
		this.value = value;
	}

	public boolean isSigned() {
		return isSigned;
	}

	public void setSigned(boolean isSigned) {
		this.isSigned = isSigned;
	}

	@Override
	public String toString() {
		return "ConstantExpression [value=" + value + ", isSigned=" + isSigned + "]";
	}

	@Override
	public Number evaluate() {
		if(isSigned)
			return Double.parseDouble("-"+value);
		return value;
	}
}

