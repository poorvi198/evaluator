package com.evaluateexpression.operator;

public abstract class UnaryOperator extends Operator {

	private boolean isSigned;


	@Override
	public abstract Number operate(Number expr1) ;


	public boolean isSigned() {
		return isSigned;
	}


	public void setSigned(boolean isSigned) {
		this.isSigned = isSigned;
	}

}
