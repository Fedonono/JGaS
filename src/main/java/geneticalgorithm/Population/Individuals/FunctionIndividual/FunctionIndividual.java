/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Individuals.FunctionIndividual;

import Mathematics.Function.Model.Function;
import Mathematics.Points;
import geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author nono
 */
public class FunctionIndividual extends Individual {
    
    private Points points;
    private Function function;

    public FunctionIndividual(Function function, Double... values) {
        this.points = new Points(values);
        this.function = function;
        this.addView(new FunctionIndividualUI());
    }

    public FunctionIndividual(Function function, Points points) {
        this.function = function;
        this.points = points;
    }

    public Points getPoints() {
        return points;
    }
    
    public int getDimension() {
        return points.size();
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public Function getFunction() {
        return function;
    }
    
    public double getResult(Points points) {
        return this.function.getResult(points);
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
