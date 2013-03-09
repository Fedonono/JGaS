/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.Mutation;

import geneticalgorithm.Operators.Operator;
import geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author simonneau
 */
public abstract class MutationOperator extends Operator {
    public abstract Individual mutate(Individual individual);
}
