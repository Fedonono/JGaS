/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.CrossOver.Function;

import Mathematics.Points;
import geneticalgorithm.Operators.CrossOver.CrossOverOperator;
import geneticalgorithm.Population.Individuals.FunctionIndividual.FunctionIndividual;
import geneticalgorithm.Population.Individuals.Individual;
import java.util.ArrayList;

/**
 *
 * @author nono
 */
public class FunctionAverageCrossOverOperator extends CrossOverOperator {
    private static String LABEL = "average";
    public FunctionAverageCrossOverOperator(double probability){
        super(probability, LABEL);
    }

    @Override
    public Individual cross(Individual male, Individual female) {
        if ((male instanceof FunctionIndividual) && (female instanceof FunctionIndividual)) {
            FunctionIndividual child = null;
            FunctionIndividual maleP = (FunctionIndividual) male;
            FunctionIndividual femaleP = (FunctionIndividual) female;

            if (maleP.getDimension() == femaleP.getDimension()) {
                Points newPoints = (Points) new ArrayList<>(maleP.getPoints());
                newPoints.addAll(femaleP.getPoints());
                for (Double number : newPoints) {
                    number /= 2;
                }
                child = new FunctionIndividual(newPoints);
            } else {
                // IncorrectIndividualDataException TODO BY ARNAUD
            }

            return child;
        }
        // IncorrectIndividualException TODO BY ARNAUD
        return null;
    }
    
}
