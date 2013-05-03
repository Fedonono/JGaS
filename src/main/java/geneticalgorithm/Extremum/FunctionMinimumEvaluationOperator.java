/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Extremum;

import Mathematics.Point;
import geneticalgorithm.Operators.Evaluation.EvaluationOperator;

/**
 *
 * @author nono
 */
public class FunctionMinimumEvaluationOperator extends EvaluationOperator<FunctionIndividual> {
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
    public void evaluate(FunctionIndividual individual) {
        
            FunctionIndividual individualP = (FunctionIndividual) individual;
            Point points = individualP.getPoint();
            double score = -individualP.getResult(points);
            individual.setScore(score);
    }
}
