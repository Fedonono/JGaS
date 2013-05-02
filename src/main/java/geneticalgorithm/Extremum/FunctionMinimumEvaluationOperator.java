/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Extremum;

import Mathematics.Point;
import geneticalgorithm.Operators.Evaluation.EvaluationOperator;
import geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author nono
 */
public class FunctionMinimumEvaluationOperator extends EvaluationOperator {
    private static String LABEL = "Minimum";
    /**
     *
     */
    public FunctionMinimumEvaluationOperator(){
        super(LABEL);
    }
    /**
     * evaluate the individual with the opposite of te image function.
     * @param individual
     */
    @Override
    public void evaluate(Individual individual) {
        if (individual instanceof FunctionIndividual) {
            FunctionIndividual individualP = (FunctionIndividual) individual;
            Point points = individualP.getPoint();
            double score = -individualP.getResult(points);
            individual.setScore(score);
        } else {
            throw new IllegalArgumentException("Cannot perform function evaluate if the individual is not compatible with this operator.");
        }
    }
}
