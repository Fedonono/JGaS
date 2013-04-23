/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mathematics.Function.Model;

import geneticalgorithm.Population.Function.FunctionRefreshEvent;
import Mathematics.Point;
import MvcPattern.Model;
import MvcPattern.RefreshEvent;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

/**
 *
 * @author nono
 */
public abstract class Function extends Model {

    private String label = null;
    protected Calculable calc = null;
    protected Point domaine;
    
    protected Function(String function, Point domaine) throws UnknownFunctionException, UnparsableExpressionException {
        this.setFunction(function, domaine);
    }

    public void setFunction(String function) throws UnknownFunctionException, UnparsableExpressionException {
        this.calc = new ExpressionBuilder(function).withVariableNames("x", "y").build();
        this.label = function;
        super.notifyViews(new FunctionRefreshEvent(this));
    }

    public void setFunction(Point domaine) throws UnknownFunctionException, UnparsableExpressionException {
        this.domaine = domaine;
        super.notifyViews(new FunctionRefreshEvent(this));
    }
    
    public void setFunction(String function, Point domaine) throws UnknownFunctionException, UnparsableExpressionException {
        this.domaine = domaine;
        this.calc = new ExpressionBuilder(function).withVariableNames("x", "y").build();
        this.label = function;
        super.notifyViews(new FunctionRefreshEvent(this));
    }

    public String getLabel() {
        return label;
    }

    public void setDomaine(Point domaine) {
        this.domaine = domaine;
        super.notifyViews(new FunctionRefreshEvent(this));
    }

    public Point getDomaine() {
        return domaine;
    }
    
    public Double minMax(Double value, double min, double max) {
        if (value < min) {
            value = min;
        }
        if (value > max) {
            value = max;
        }
        return value;
    }

    public abstract double getResult(Point points);
    public abstract void inDomaine(Point points);
}
