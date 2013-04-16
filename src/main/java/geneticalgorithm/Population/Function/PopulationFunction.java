/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Function;

import Mathematics.Function.Model.Function;
import geneticalgorithm.Population.Population;

/**
 *
 * @author nono
 */
public class PopulationFunction extends Population {
    private Function function;

    public PopulationFunction(Function function) {
        this.function = function;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }
}
