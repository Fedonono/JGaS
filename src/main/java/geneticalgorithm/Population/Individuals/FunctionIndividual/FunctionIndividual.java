/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Individuals.FunctionIndividual;

import Mathematics.Function.Model.Function;
import Mathematics.Point;
import geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author nono
 */
public class FunctionIndividual extends Individual {
    
    private Point point;
    private Function function;

    public FunctionIndividual(Function function, Double... values) {
        this.point = new Point(values);
        this.function = function;
        this.addView(new FunctionIndividualUI());
    }

    public FunctionIndividual(Function function, Point points) {
        this.function = function;
        this.point = points;
        this.addView(new FunctionIndividualUI());
    }

    public Point getPoint() {
        return point;
    }
    
    public int getDimension() {
        return point.size();
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public Function getFunction() {
        return function;
    }
    
    public double getResult(Point points) {
        return this.function.getResult(points);
    }
    
    public void setPoint(Point p){
        this.point.set(p);
    }
    
    @Override
    protected void set(Individual s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String xmlSerialization() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
}
