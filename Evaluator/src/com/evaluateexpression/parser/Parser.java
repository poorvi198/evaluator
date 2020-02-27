package com.evaluateexpression.parser;

import com.evaluateexpression.customexceptions.InvalidExpression;
import com.evaluateexpression.customexceptions.ParseException;
import com.evaluateexpression.expression.*;
import com.evaluateexpression.operator.*;

import java.util.Stack;

public class Parser {

	private Stack<Object>objectStack = new Stack<>();
	private Stack<Expression>expressionStack1 = new Stack<>();
	private Stack<Operator>operatorStack = new Stack<>();


	//parse tokens and create one stack of constant expression objects and operator objects
	public Expression parseToken(String userInput) throws ParseException, InvalidExpression{
		Stack<Token> tokenList = new Tokenizer().getTokenList(userInput);
		Token token = null;
		while(!tokenList.isEmpty())
		{
			token = tokenList.pop();
			//if token is a number
			if(token.getType().equals("number"))
			{
				objectStack.push(new ConstantExpression(Double.parseDouble(token.getValue())));
			}

			//if token is a sign or an operator
			else if(token.getType().equals("PlusMinus"))
			{
				if((tokenList.isEmpty() || (!(tokenList.peek().getType().equals("number")) && !(tokenList.peek().getType().equals("ClosingBracket")))))
				{
					if(token.getValue().equals("-"))
					{
						if(objectStack.peek() instanceof ConstantExpression)
							((ConstantExpression) objectStack.peek()).setSigned(true);
						else
						{
							if(objectStack.peek() instanceof UnaryOperator)
							{
								((UnaryOperator) objectStack.peek()).setSigned(true);
							}

							else if(objectStack.peek() instanceof Parenthesis)
							{
								((Parenthesis) objectStack.peek()).setSigned(true);
							}

						}
					}
					else
					{
						if(objectStack.peek() instanceof ConstantExpression)
							((ConstantExpression) objectStack.peek()).setSigned(false);
						else
						{
							if(objectStack.peek() instanceof UnaryOperator)
							{
								((UnaryOperator) objectStack.peek()).setSigned(false);
							}
						}
					}
				}
				else
				{
					PlusMinusOperator plusMinusOperator = new PlusMinusOperator();
					plusMinusOperator.setSymbol(token.getValue());
					plusMinusOperator.setPrecedence(5);
					objectStack.push(plusMinusOperator);
				}
			}
			//if token is a multiply divide or trigonometry or opening bracket or closing bracket
			else
			{
				if(token.getType().equals("MulDiv"))
				{
					MulDivOperator mulDivOperator = new MulDivOperator();
					mulDivOperator.setSymbol(token.getValue());
					mulDivOperator.setPrecedence(6);
					objectStack.push(mulDivOperator);
				}
				else if(token.getType().equals("Trigonometric"))
				{
					TrigonometricOperator trigOperator = new TrigonometricOperator();
					trigOperator.setSymbol(token.getValue());
					trigOperator.setPrecedence(7);
					objectStack.push(trigOperator);
				}
				else if(token.getType().equals("OpeningBracket"))
				{
					Operator op = new Parenthesis();
					op.setSymbol("(");
					op.setPrecedence(0);
					objectStack.push(op);
				}
				else if(token.getType().equals("ClosingBracket"))
				{
					Operator op = new Parenthesis();
					op.setSymbol(")");
					op.setPrecedence(0);
					objectStack.push(op);
				}
			}
		}
		Expression expression = parseExpression();
		return expression;
	}

	//create expression and operator stacks
	private Expression parseExpression() throws ParseException{

		while(!objectStack.isEmpty())
		{
			//if token is an expression push it to expression stack
			if(objectStack.peek() instanceof Expression)
			{
				expressionStack1.push((Expression) objectStack.pop());
			}

			//if token is an operator
			else if(objectStack.peek() instanceof Operator)
			{
				//if closing bracket is found create expression objects until opening bracket is not found
				if(((Operator)objectStack.peek()).getSymbol().equals(")"))
				{
					while(!operatorStack.isEmpty() && !(((Operator)operatorStack.peek()).getSymbol().equals("(")))
					{
						if(operatorStack.peek() instanceof BinaryOperator)
							createBinaryExpression();
						else
							createUnaryExpression();
					}
					if(!(((Operator)operatorStack.peek()).getSymbol().equals("(")))
					{
						throw new ParseException("bracket missing");
					}
					if(((Parenthesis)operatorStack.peek()).isSigned())
					{
						((OperatorExpression<Operator>)expressionStack1.peek()).setSigned(true);
					}
					objectStack.pop();
					operatorStack.pop();

				}
				//any other operator is found push it considering the precedence
				else{
					if(operatorStack.isEmpty() || ((Operator)objectStack.peek()).getSymbol().equals("("))
					{
						operatorStack.push((Operator) objectStack.pop());
					}

					else
					{
						if((((Operator)objectStack.peek()).getPrecedence()) >
								(((Operator)operatorStack.peek()).getPrecedence())){

							operatorStack.push((Operator) objectStack.pop());
						}
						else{
							if(operatorStack.peek() instanceof BinaryOperator)
								createBinaryExpression();
							else
								createUnaryExpression();
						}
					}
				}

			}
		}
		//create expressions
		while(!operatorStack.isEmpty())
		{
			if(operatorStack.peek() instanceof BinaryOperator)
				createBinaryExpression();
			else if(operatorStack.peek() instanceof UnaryOperator)
				createUnaryExpression();
		}

		if(expressionStack1.size()!=1)
		{
			throw new ParseException("invalid no. of operator and operands...");
		}
		return expressionStack1.pop();
	}

	//create unary expression
	private void createUnaryExpression() throws ParseException {
		UnaryExpression unaryExp = new UnaryExpression();
		try{
			unaryExp.setExpression(expressionStack1.pop());
			unaryExp.setOperator((UnaryOperator)operatorStack.pop());
			expressionStack1.push(unaryExp);
		}
		catch(Exception exc)
		{
			throw new ParseException("invalid no. of operator and operands.....");
		}

	}

	//create binary expressions
	private void createBinaryExpression() throws ParseException
	{
		BinaryExpression binaryExp = new BinaryExpression();
		try{
			binaryExp.setExpression2(expressionStack1.pop());
			binaryExp.setExpression1(expressionStack1.pop());
			binaryExp.setOperator((BinaryOperator)operatorStack.pop());
			expressionStack1.push(binaryExp);

		}
		catch(Exception exc)
		{
			throw new ParseException("invalid no. of operator and operands....");
		}
	}
}
