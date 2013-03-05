/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.Mutation;

import geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author simonneau
 */
public interface MutationOperator {
    public Individual mutate(Individual individual);
}
