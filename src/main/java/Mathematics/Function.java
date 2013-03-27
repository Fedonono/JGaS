/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mathematics;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

/**
 *
 * @author nono
 */
public class Function {
    
    private static Function instance = null;
    private static Calculable calc = null;
    private static String label = null;

    private Function(String function) throws UnknownFunctionException, UnparsableExpressionException {
        this.changeFunction(function);
    }

    private static void changeFunction(String function) throws UnknownFunctionException, UnparsableExpressionException {
        instance.calc = new ExpressionBuilder(function).withVariableNames("x","y").build();
        instance.label = function;
    }
    
    public static Function getInstance() throws UnknownFunctionException, UnparsableExpressionException {
        if (instance == null) {
            instance = new Function("2*x");
        }
        return instance;
    }
    
    public static Function newFunction(String function) throws UnknownFunctionException, UnparsableExpressionException {
        if (instance == null) {
            instance = new Function(function);
        } else {
            instance.changeFunction(function);
        }
        return instance;
    }
    
    public String getLabel() {
        return instance.label;
    }
    
    public double getY(double x) {
        instance.calc.setVariable("x", x);
        return instance.calc.calculate();
    }

    public double getResult(Points points) {
        if (points.size() > 2)
            return 0; //TODO add exception by ARNAUD
        
        int dim = 0;
        
        for (Double value : points) {
            switch (dim) {
                case 0:
                    instance.calc.setVariable("x", value);
                    break;
                case 1:
                    instance.calc.setVariable("y", value);
                    break;
                default:
                    // TODO add exception by ARNAUD
                    break;
            }
            dim++;
        }
        return instance.calc.calculate();
    }
}
