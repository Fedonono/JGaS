/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.Evaluation.Function;

import Mathematics.Point;
import geneticalgorithm.Operators.Evaluation.EvaluationOperator;
import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.Individuals.FunctionIndividual.FunctionIndividual;

/**
 *
 * @author nono
 */
public class FunctionEvaluationOperator extends EvaluationOperator {
    private static String LABEL = "Evaluation";
    public FunctionEvaluationOperator(){
        super(LABEL);
    }
    @Override
    public void evaluate(Individual individual) {
        if (individual instanceof FunctionIndividual) {
            FunctionIndividual individualP = (FunctionIndividual) individual;
            Point points = individualP.getPoint();
            individualP.getFunction().inDomaine(points);
            double score = -individualP.getResult(points);
            individual.setScore(score);
        }
        // IncorrectIndividualException TODO BY ARNAUD
    }
}
