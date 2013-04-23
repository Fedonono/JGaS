/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.CrossOver;

import geneticalgorithm.Operators.Operator;
import geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author simonneau
 */
public abstract class CrossOverOperator extends Operator {

    public CrossOverOperator(String label) {
        super(label);
    }

    public abstract Individual cross(Individual male, Individual female);
}
