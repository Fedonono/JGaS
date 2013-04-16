/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.Evaluation;

import geneticalgorithm.Operators.Operator;
import geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author simonneau
 */
public abstract class EvaluationOperator extends Operator {

    public EvaluationOperator(String label){
        super(label);
    }
    public abstract void evaluate(Individual individual);
}
