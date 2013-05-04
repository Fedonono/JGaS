/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.CrossOver;

import geneticalgorithm.Operators.Operator;
import geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @param <T> 
 * @author simonneau
 */
public abstract class CrossOverOperator<T extends Individual> extends Operator {

    /**
     *
     * @param label
     */
    public CrossOverOperator(String label) {
        super(label);
    }

    /**
     * cross male with female.
     * @param male
     * @param female
     * @return return the crossover between male and female.
     */
    public abstract T cross(T male, T female);
}
