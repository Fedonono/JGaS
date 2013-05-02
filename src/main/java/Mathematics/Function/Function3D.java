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
public class Function3D extends Function {
    /**
     *
     * @param function
     * @param domaine
     * @throws UnknownFunctionException
     * @throws UnparsableExpressionException
     */
    public Function3D(String function, Point domaine) throws UnknownFunctionException, UnparsableExpressionException {
        super(function, domaine);
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public double getZ(double x, double y) {
        this.calc.setVariable("x", x);
        this.calc.setVariable("y", y);
        return this.calc.calculate();
    }

    /**
     *
     * @param points
     * @return
     */
    @Override
    public double getResult(Point points) {
        return getZ(points.get(0), points.get(1));
    }
}
