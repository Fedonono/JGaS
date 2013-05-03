/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.TSP;

import geneticalgorithm.Operators.Evaluation.EvaluationOperator;

/**
 *
 * @author simonneau
 */
public class TSPEvaluationOperator extends EvaluationOperator<TSPIndividual>{
    private static String LABEL = "total distance based evaluation";
    
    /**
     *
     */
    public TSPEvaluationOperator(){
        super(LABEL);
    }

    @Override
    public void evaluate(TSPIndividual individual) {
        individual.setScore(-individual.getTotalDistance());
    }
}
