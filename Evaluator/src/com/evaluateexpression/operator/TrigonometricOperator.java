package com.evaluateexpression.operator;

public class TrigonometricOperator extends UnaryOperator{

	@Override
	public Number operate(Number expr1, Number expr2) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isSigned() {
		return super.isSigned();
	}


	public void setSigned(boolean isSigned) {
		super.setSigned(isSigned);
	}
	
	@Override
	public Number operate(Number expr1) {
		double result;
		if(super.getSymbol().equals("sin"))
		{
			result =  (Math.sin((double) expr1));
		}
		else if(super.getSymbol().equals("cos"))
		{
			result = (Math.cos((double) expr1));
		}
			
		else
			result = (Math.tan((double) expr1));
		
		if(isSigned())
			return -result;
		else return result;
	}

}
