/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.CrossOver.Points;

import geneticalgorithm.Operators.CrossOver.CrossOverOperator;
import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.Individuals.Points.Points;
import java.util.ArrayList;

/**
 *
 * @author nono
 */
public class PointsAverageCrossOverOperator extends CrossOverOperator {

    @Override
    public Individual cross(Individual male, Individual female) {
        Points child = null;
        Points maleP = (Points) male;
        Points femaleP = (Points) female;
        
        if (maleP.getDimension() == femaleP.getDimension()) {
            ArrayList<Number> newValues = new ArrayList<>(maleP.getValues());
            newValues.addAll(femaleP.getValues());
            for (Number number : newValues) {
                number = number.doubleValue()/2;
            }
            child = new Points(newValues);
        } else {
            // exception
        }
        
        return child;
    }
    
}
