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
    
    private Calculable calc;

    public Function(String function) throws UnknownFunctionException, UnparsableExpressionException {
        calc = new ExpressionBuilder(function).withVariableNames("x","y").build();
    }
    
    public double getResult(Points points) {
        if (points.size() > 2)
            return 0; //TODO add exception by ARNAUD
        
        int dim = 0;
        
        for (Double value : points) {
            switch (dim) {
                case 0:
                    calc.setVariable("x", value);
                    break;
                case 1:
                    calc.setVariable("y", value);
                    break;
                default:
                    // TODO add exception by ARNAUD
                    break;
            }
            dim++;
        }
        return calc.calculate();
    }
    
    public void changeFunction(String function) throws UnknownFunctionException, UnknownFunctionException, UnparsableExpressionException {
        calc = new ExpressionBuilder(function).withVariableNames("x","y").build();
    }
}
