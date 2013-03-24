/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.Evaluation.Function;

import geneticalgorithm.Operators.Evaluation.EvaluationOperator;
import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.Individuals.FunctionIndividual.FunctionIndividual;

/**
 *
 * @author nono
 */
public class FunctionEvaluationOperator extends EvaluationOperator {
    @Override
    public void evaluate(Individual individual) {
        if (individual instanceof FunctionIndividual) {
            FunctionIndividual individualP = (FunctionIndividual) individual;
            double score = -individualP.getResult(individualP.getPoints());
            individualP.setScore(score);
        }
        // IncorrectIndividualException TODO BY ARNAUD
    }
}
