/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.CrossOver.Function;

import geneticalgorithm.Operators.CrossOver.CrossOverOperator;
import geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author nono
 */
public class FunctionBarycentreCrossOverOperator extends CrossOverOperator {
private static String LABEL = "Barycentre";
    public FunctionBarycentreCrossOverOperator() {
        super(LABEL);
    }

    @Override
    public Individual cross(Individual male, Individual female) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
