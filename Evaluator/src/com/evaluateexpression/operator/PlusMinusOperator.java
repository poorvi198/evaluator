package com.evaluateexpression.operator;

public class PlusMinusOperator extends BinaryOperator {


	@Override
	public Number operate(Number expr1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Number operate(Number number, Number number2) {
		if(getSymbol().equals("+"))
		{
			return number.doubleValue()+number2.doubleValue();
		}
		else
			return number.doubleValue()-number2.doubleValue();
	}

	@Override
	public void setPrecedence(int precedence) {
		super.setPrecedence(precedence);
	}

}

