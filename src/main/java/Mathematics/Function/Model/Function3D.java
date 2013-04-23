/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mathematics.Function.Model;

import Mathematics.Point;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import javax.swing.JPanel;

/**
 *
 * @author nono
 */
public class Function3D extends Function {
    public Function3D(String function, Point domaine) throws UnknownFunctionException, UnparsableExpressionException {
        super(function, domaine);
    }

    public double getZ(double x, double y) {
        this.calc.setVariable("x", x);
        this.calc.setVariable("y", y);
        return this.calc.calculate();
    }

    @Override
    public double getResult(Point points) {
        return getZ(points.get(0), points.get(1));
    }

    @Override
    public void inDomaine(Point points) {
        super.minMax(points.get(0), domaine.get(0), domaine.get(1));
        super.minMax(points.get(1), domaine.get(2), domaine.get(3));
    }
}
