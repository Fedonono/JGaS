/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.CrossOver;

import geneticalgorithm.Operators.ProbableOperator;
import geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author simonneau
 */
public abstract class CrossOverOperator extends ProbableOperator {

    public CrossOverOperator(double prb, String label) {
        super(prb, label);
    }

    public abstract Individual cross(Individual male, Individual female);
}
