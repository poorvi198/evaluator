package com.evaluateexpression.parser;

class Token{
    private String type;
    private String value;
    
    public Token(String type,String value){
        this.type = type;
        this.value = value;
    }

	@Override
	public String toString() {
		return "Token [type=" + type + ", value=" + value + "]";
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	
}