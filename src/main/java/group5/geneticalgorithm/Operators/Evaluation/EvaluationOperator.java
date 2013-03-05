/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.geneticalgorithm.Operators.Evaluation;

import group5.geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author simonneau
 */
public interface EvaluationOperator {

    public double evaluate(Individual individual);
}
