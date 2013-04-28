/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.Selection;

import geneticalgorithm.Operators.Operator;
import geneticalgorithm.Population.Population;

/**
 *
 * @author simonneau
 */
public abstract class SelectionOperator extends Operator {
    
    /**
     *
     * @param label
     */
    public SelectionOperator(String label){
        super(label);
    }

    /**
     * select survivorSize individuals form population.
     * @param population
     * @param survivorSize
     * @return
     */
    public abstract Population buildNextGeneration(Population population, int survivorSize);
}
