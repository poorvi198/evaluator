package com.evaluateexpression.parser;

import com.evaluateexpression.customexceptions.InvalidExpression;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

class Tokenizer{
	private Map<String,Pattern> regexOperatorList = new HashMap<>();

	private Stack<Token>tokenList = new Stack<>();

	private void setRegexList(){
		regexOperatorList.put("PlusMinus",Pattern.compile(Regex.PLUSMINUS));
		regexOperatorList.put("MulDiv",Pattern.compile(Regex.MULDIV));
		regexOperatorList.put("OpeningBracket",Pattern.compile(Regex.OPENINGBRACKET));
		regexOperatorList.put("ClosingBracket",Pattern.compile(Regex.CLOSINGBRACKET));
	}


	//tokenize character by character
	public Stack<Token> getTokenList(String s) throws InvalidExpression {
		setRegexList();
		boolean isNotNumber = false;
		String tempString = "";
		String unaryop = "";
		for(int i=0;i<s.length();i++){
			isNotNumber = false;
			if(Character.isWhitespace(s.charAt(i)))
			{
				if(!tempString.equals("") && Pattern.matches(Regex.NUMBER, tempString))
				{
					tokenList.push(new Token("number", tempString));
					tempString="";
				}
				continue;
			}

			//check if it is present in regex list
			for(Map.Entry<String, Pattern> entry : regexOperatorList.entrySet()){
				if(entry.getValue().matcher(s.charAt(i)+"").matches()){
					isNotNumber = true;
					if(!tempString.equals("")){
						if(Pattern.matches(Regex.NUMBER, tempString))
							tokenList.push(new Token("number", tempString));
						else
							throw new InvalidExpression("invalid operand or operator");
					}
					tokenList.push(new Token(entry.getKey(),s.charAt(i)+""));
					tempString = "";
				}
			}
			if(!isNotNumber)
			{
				//checking for number and unary operators
				tempString = tempString+s.charAt(i);
				if(!Pattern.matches(Regex.NUMBER, tempString))
				{
					unaryop = unaryop + tempString;
					tempString = "";
					if(Pattern.matches(Regex.TRIGONOMETRIC, unaryop))
					{
						tokenList.push(new Token("Trigonometric",unaryop));
						unaryop = "";
					}
				}
				else
				{
					if(!unaryop.equals(""))
					{
						if(Pattern.matches(Regex.TRIGONOMETRIC, unaryop))
						{
							tokenList.push(new Token("Trigonometric",unaryop));
							unaryop = "";
						}
						else{
							throw new InvalidExpression("invalid operator");
						}
					}
				}
			}
		}
		if(!tempString.equals("") && Pattern.matches(Regex.NUMBER, tempString))
			tokenList.push(new Token("number", tempString));

			//last token should be a number or a closing bracket
		else if(tempString.equals(""))
		{
			if(!(tokenList.peek().getType().equals("ClosingBracket")))
				throw new InvalidExpression("missing operand");
		}

		else
			throw new InvalidExpression("invalid operand");
		return tokenList;
	}
}