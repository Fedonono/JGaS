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
public abstract class EvaluationOperator<T extends Individual> extends Operator {

    /**
     *
     * @param label
     */
    public EvaluationOperator(String label){
        super(label);
    }
    /**
     * evaluate individual.
     * @param individual
     */
    public abstract void evaluate(T individual);
}
