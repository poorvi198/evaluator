package com.evaluateexpression.customexceptions;

public class ParseException extends Exception {

	public ParseException(String expression){
		super(expression);
	}
}
