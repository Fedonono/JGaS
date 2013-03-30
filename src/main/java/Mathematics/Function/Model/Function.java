/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mathematics.Function.Model;

import GraphicalComponents.IdentifiableComponent;
import Mathematics.Points;
import MvcPattern.Model;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import javax.swing.JPanel;

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
        super.notifyViews(new RefreshEvent(this));
    }

    public void setFunction(Points domaine) throws UnknownFunctionException, UnparsableExpressionException {
        this.domaine = domaine;
        super.notifyViews(new RefreshEvent(this));
    }
    
    public void setFunction(String function, Points domaine) throws UnknownFunctionException, UnparsableExpressionException {
        this.domaine = domaine;
        this.calc = new ExpressionBuilder(function).withVariableNames("x", "y").build();
        this.label = function;
        super.notifyViews(new RefreshEvent(this));
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
