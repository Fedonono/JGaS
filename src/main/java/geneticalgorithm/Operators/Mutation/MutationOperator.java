/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.Mutation;

import geneticalgorithm.Operators.ProbableOperator;
import geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author simonneau
 */
public abstract class MutationOperator extends ProbableOperator {
    
    public MutationOperator(double prb, String label){
        super(prb, label);
    };
    
    public abstract Individual mutate(Individual individual);
}
