/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.Evaluation;

import geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author simonneau
 */
public interface EvaluationOperator {

    public double evaluate(Individual individual);
}
