/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mathematics.Function;

import Mathematics.Point;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

/**
 *
 * @author nono
 */
public class Function2D extends Function {
    /**
     *
     * @param function
     * @param domaine
     * @throws UnknownFunctionException
     * @throws UnparsableExpressionException
     */
    public Function2D(String function, Point domaine) throws UnknownFunctionException, UnparsableExpressionException {
        super(function, domaine);
    }

    /**
     *
     * @param x
     * @return
     */
    public double getY(double x) {
        this.calc.setVariable("x", x);
        return this.calc.calculate();
    }

    /**
     *
     * @param points
     * @return
     */
    @Override
    public double getResult(Point points) {
        return getY(points.get(0));
    }
}
