/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mathematics.Function;

import Mathematics.Point;
import MvcPattern.Model;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Extremum.FunctionRefreshEvent;

/**
 *
 * @author nono
 */
public abstract class Function extends Model {

    private String label = null;
    /**
     *
     */
    protected Calculable calc = null;
    /**
     *
     */
    protected Point domaine;
    
    /**
     *
     * @param function
     * @param domaine
     * @throws UnknownFunctionException
     * @throws UnparsableExpressionException
     */
    protected Function(String function, Point domaine) throws UnknownFunctionException, UnparsableExpressionException {
        this.setFunction(function, domaine);
    }

    /**
     *
     * @param function
     * @throws UnknownFunctionException
     * @throws UnparsableExpressionException
     */
    public void setFunction(String function) throws UnknownFunctionException, UnparsableExpressionException {
        this.calc = new ExpressionBuilder(function).withVariableNames("x", "y").build();
        this.label = function;
        super.notifyViews(new FunctionRefreshEvent(this));
    }

    /**
     *
     * @param domaine
     * @throws UnknownFunctionException
     * @throws UnparsableExpressionException
     */
    public void setFunction(Point domaine) throws UnknownFunctionException, UnparsableExpressionException {
        this.domaine = domaine;
        super.notifyViews(new FunctionRefreshEvent(this));
    }
    
    /**
     *
     * @param function
     * @param domaine
     * @throws UnknownFunctionException
     * @throws UnparsableExpressionException
     */
    public void setFunction(String function, Point domaine) throws UnknownFunctionException, UnparsableExpressionException {
        this.domaine = domaine;
        this.calc = new ExpressionBuilder(function).withVariableNames("x", "y").build();
        this.label = function;
        super.notifyViews(new FunctionRefreshEvent(this));
    }

    /**
     *
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     *
     * @param domaine
     */
    public void setDomaine(Point domaine) {
        this.domaine = domaine;
        super.notifyViews(new FunctionRefreshEvent(this));
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @param points
     */
    public void inDomaine(Point points) {
        int dimension = points.size();
        for (int i = 1; i <= dimension; i++) {
            points.set(i, minMaxDom(points.get(i), domaine.get(2*i-2), domaine.get(2*i-1)));
        }
    }
    
    /**
     *
     * @param points
     * @return
     */
    public abstract double getResult(Point points);
}
