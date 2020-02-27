package com.evaluateexpression.parser;

import com.evaluateexpression.customexceptions.EvaluateException;
import com.evaluateexpression.customexceptions.InvalidExpression;
import com.evaluateexpression.customexceptions.ParseException;
import com.evaluateexpression.evaluate.Evaluator;
import com.evaluateexpression.expression.Expression;
import org.junit.jupiter.api.Test;

import static java.lang.Math.sin;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    Parser parser = new Parser();
    Evaluator eval = new Evaluator();
    @Test
    void parseToken() throws InvalidExpression, ParseException, EvaluateException {
        Expression exp = parser.parseToken("2+sin(30)");
        Number result = eval.eval(exp);

        assertEquals(2+sin(30),result);
        assertEquals(2+sin(30),result,"result should be"+2+sin(30));
    }
}