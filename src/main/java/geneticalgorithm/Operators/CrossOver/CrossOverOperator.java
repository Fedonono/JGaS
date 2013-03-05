/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.CrossOver;

import geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author simonneau
 */
public interface CrossOverOperator {
    public Individual cross(Individual male, Individual female);
}
