/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Extremum;

import Mathematics.Point;
import geneticalgorithm.Operators.Evaluation.EvaluationOperator;

/**
 *
 * @author simonneau
 */
public class FunctionMaximumEvaluationOperator extends EvaluationOperator<FunctionIndividual> {

    private static String LABEL = "Maximum";

    /**
     *
     */
    public FunctionMaximumEvaluationOperator() {
        super(LABEL);
    }

    /**
     * evaluate the individual with the image fuction.
     *
     * @param individual
     */
    @Override
    public void evaluate(FunctionIndividual individual) {

        FunctionIndividual individualP = (FunctionIndividual) individual;
        Point points = individualP.getPoint();
        double score = individualP.getResult(points);
        individual.setScore(score);

    }
}
