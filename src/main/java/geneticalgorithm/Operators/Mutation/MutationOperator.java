/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.Mutation;

import geneticalgorithm.Operators.Operator;
import geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @param <T> 
 * @author simonneau
 */
public abstract class MutationOperator <T extends Individual> extends Operator {
    
    /**
     *
     * @param label
     */
    public MutationOperator(String label){
        super(label);
    };
    
    /**
     * mutate individual.
     * @param individual
     * @return the mutant individual coming from individual.
     */
    public abstract T mutate(T individual);
}
