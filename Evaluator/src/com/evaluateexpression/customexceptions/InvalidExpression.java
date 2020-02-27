package com.evaluateexpression.customexceptions;

public class InvalidExpression extends Exception {
	
	public InvalidExpression(String expression){
		super(expression);
	}
}
