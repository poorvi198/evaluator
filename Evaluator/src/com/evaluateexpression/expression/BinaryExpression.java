package com.evaluateexpression.expression;

import com.evaluateexpression.operator.BinaryOperator;

public class BinaryExpression extends OperatorExpression<BinaryOperator> {

	private Expression expression1;
	private Expression expression2;

	@Override
	public String toString() {
		return "BinaryExpression [expression1=" + expression1 + ", expression2=" + expression2 + "Operator="+getOperator()+"]"+super.toString();
	}

	public Expression getExpression1() {
		return expression1;
	}

	public void setExpression1(Expression expression1) {
		this.expression1 = expression1;
	}

	public Expression getExpression2() {
		return expression2;
	}

	public void setExpression2(Expression expression2) {
		this.expression2 = expression2;
	}

	@Override
	public Number evaluate(){
		if(isSigned())
			return -((getOperator().operate(expression1.evaluate(), expression2.evaluate())).doubleValue());
		else
			return getOperator().operate(expression1.evaluate(), expression2.evaluate());
	}


}
