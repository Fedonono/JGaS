/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mathematics.Function.Model;

import Mathematics.Point;
import MvcPattern.Model;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Population.Function.FunctionRefreshEvent;

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
    
    /**
     * Si dépasse les bornes, on lui attribue un nombre au hasard entre celle-ci
     * 
     * @param value
     * @param min
     * @param max
     * @return
     */
    public Double minMaxDom(Double value, double min, double max) {
        if (value < min || value > max) {
            value = Math.random()*(max-min)+min;
        }
        return value;
    }

    public void inDomaine(Point points) {
        int dimension = points.size();
        for (int i = 1; i <= dimension; i++) {
            points.set(i, minMaxDom(points.get(i), domaine.get(2*i-2), domaine.get(2*i-1)));
        }
    }
    
    public abstract double getResult(Point points);
}
