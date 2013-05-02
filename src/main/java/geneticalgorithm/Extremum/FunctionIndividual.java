/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Extremum;

import Mathematics.Function.Function;
import Mathematics.Point;
import geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author nono
 */
public class FunctionIndividual extends Individual {
    
    private Point point;
    private Function function;

    /**
     *
     * @param function
     * @param values
     */
    public FunctionIndividual(Function function, Double... values) {
        this.point = new Point(values);
        this.function = function;
        this.addView(new FunctionIndividualUI());
    }

    /**
     *
     * @param function
     * @param points
     */
    public FunctionIndividual(Function function, Point points) {
        this.function = function;
        this.point = points;
        this.addView(new FunctionIndividualUI());
    }

    /**
     *
     * @return
     */
    public Point getPoint() {
        return point;
    }
    
    /**
     *
     * @return
     */
    public int getDimension() {
        return point.size();
    }

    /**
     *
     * @param function
     */
    public void setFunction(Function function) {
        this.function = function;
    }

    /**
     *
     * @return
     */
    public Function getFunction() {
        return function;
    }
    
    /**
     *
     * @param points
     * @return
     */
    public double getResult(Point points) {
        return this.function.getResult(points);
    }
    
    /**
     *
     * @param p
     */
    public void setPoint(Point p){
        this.point.set(p);
    }
    
    @Override
    protected void set(Individual s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @return
     */
    @Override
    public String xmlSerialization() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
}
