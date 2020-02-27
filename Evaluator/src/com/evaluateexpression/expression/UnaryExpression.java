package com.evaluateexpression.expression;

import com.evaluateexpression.operator.UnaryOperator;

public class UnaryExpression extends OperatorExpression<UnaryOperator> {

	private Expression expression;


	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public Number evaluate(){
		return getOperator().operate(expression.evaluate());
	}

	@Override
	public String toString() {
		return "UnaryExpression [expression=" + expression + "]"+super.toString();
	}
}
