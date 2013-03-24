/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package isen.cir3.group5.geneticalgorithm;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

/**
 *
 * @author nono
 */
public class TestFrame {

    public static void main(String[] args) throws UnknownFunctionException, UnparsableExpressionException {
        Calculable calc = new ExpressionBuilder("sin(x)").withVariableNames("x","y").build();
        System.out.println(calc.calculate(1, 2));
    }
}
