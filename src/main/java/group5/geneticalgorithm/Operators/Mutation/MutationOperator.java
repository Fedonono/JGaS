/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.geneticalgorithm.Operators.Mutation;

import group5.geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author simonneau
 */
public interface MutationOperator {
    public Individual mutate(Individual individual);
}
