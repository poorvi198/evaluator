package com.evaluateexpression.parser;

public class Regex {
	public static final String PLUSMINUS = "[+-]";
	public static final String MULDIV = "[*/]";
	public static final String NUMBER = "(?:\\d+\\.?|\\.\\d)\\d*(?:[Ee][-+]?\\d+)?";
	public static final String TRIGONOMETRIC = "(sin|cos|tan)";
	public static final String OPENINGBRACKET ="(\\()";
	public static final String CLOSINGBRACKET = "(\\))";

}
