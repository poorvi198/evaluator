package com.evaluateexpression.Main;

import com.evaluateexpression.customexceptions.EvaluateException;
import com.evaluateexpression.customexceptions.InvalidExpression;
import com.evaluateexpression.customexceptions.ParseException;
import com.evaluateexpression.evaluate.Evaluator;
import com.evaluateexpression.expression.Expression;
import com.evaluateexpression.parser.Parser;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Parser parser = new Parser();
        Evaluator evaluator = new Evaluator();
        while(true)
        {
            //take the input
            String userInput = Main.getInput();
            try{
                //parse
                Expression expression = parser.parseToken(userInput);
                System.out.println(expression);
                System.out.println(evaluator.eval(expression));

            }
            catch(ParseException e){
                System.out.println(e.getMessage());
            }
            catch (InvalidExpression e) {

                System.out.println(e.getMessage());
            }
            catch(EvaluateException e)
            {
                System.out.println("invalid expression");
            }


        }
    }
    private static String getInput(){
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        return userInput;
    }


}
