/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mathematics.Function.Model;

import Mathematics.Function.Events.FunctionRefreshEvent;
import Mathematics.Points;
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
    protected Points domaine;
    
    protected Function(String function, Points domaine) throws UnknownFunctionException, UnparsableExpressionException {
        this.setFunction(function, domaine);
    }

    public void setFunction(String function) throws UnknownFunctionException, UnparsableExpressionException {
        this.calc = new ExpressionBuilder(function).withVariableNames("x", "y").build();
        this.label = function;
        super.notifyViews(new FunctionRefreshEvent(this));
    }

    public void setFunction(Points domaine) throws UnknownFunctionException, UnparsableExpressionException {
        this.domaine = domaine;
        super.notifyViews(new FunctionRefreshEvent(this));
    }
    
    public void setFunction(String function, Points domaine) throws UnknownFunctionException, UnparsableExpressionException {
        this.domaine = domaine;
        this.calc = new ExpressionBuilder(function).withVariableNames("x", "y").build();
        this.label = function;
        super.notifyViews(new FunctionRefreshEvent(this));
    }

    public String getLabel() {
        return label;
    }

    public Points getDomaine() {
        return domaine;
    }
    
    public void minMax(Double value, double min, double max) {
        if (value < min) {
            value = min;
        }
        if (value > max) {
            value = max;
        }
    }

    public abstract double getResult(Points points);
    public abstract void inDomaine(Points points);
}
