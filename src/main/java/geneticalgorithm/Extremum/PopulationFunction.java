/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Extremum;

import Mathematics.Function.Function;
import geneticalgorithm.Population.Population;

/**
 *
 * @author nono
 */
public class PopulationFunction extends Population {
    private Function function;

    /**
     *
     * @param function
     */
    public PopulationFunction(Function function) {
        this.function = function;
    }

    /**
     *
     * @return 'this' function.
     */
    public Function getFunction() {
        return function;
    }

    /**
     * set 'this' function.
     * @param function
     */
    public void setFunction(Function function) {
        this.function = function;
    }
}
