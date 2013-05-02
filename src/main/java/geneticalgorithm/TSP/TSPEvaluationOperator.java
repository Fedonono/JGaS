/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.TSP;

import geneticalgorithm.Operators.Evaluation.EvaluationOperator;
import geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author simonneau
 */
public class TSPEvaluationOperator extends EvaluationOperator{
    private static String LABEL = "total distance based evaluation";
    
    /**
     *
     */
    public TSPEvaluationOperator(){
        super(LABEL);
    }

    @Override
    public void evaluate(Individual individual) {
        individual.setScore(-((TSPIndividual)individual).getTotalDistance());
    }
}
