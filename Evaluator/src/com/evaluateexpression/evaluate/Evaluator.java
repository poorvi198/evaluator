package com.evaluateexpression.evaluate;

import com.evaluateexpression.customexceptions.EvaluateException;
import com.evaluateexpression.expression.Expression;

public class Evaluator {

	public Number eval(Expression exp) throws EvaluateException {
		return exp.evaluate();
		
	}
}
