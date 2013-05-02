/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Extremum;

import Mathematics.Point;
import geneticalgorithm.Operators.CrossOver.CrossOverOperator;
import geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author nono
 */
public class FunctionAverageCrossOverOperator extends CrossOverOperator {

    private static String LABEL = "Average";

    /**
     *
     */
    public FunctionAverageCrossOverOperator() {
        super(LABEL);
    }

    /**
     * cross male with female doing the average.
     * @param male
     * @param female
     * @return the male and female average.
     */
    @Override
    public Individual cross(Individual male, Individual female) {

        if (male instanceof FunctionIndividual && female instanceof FunctionIndividual) {

            FunctionIndividual maleP = (FunctionIndividual) male;
            FunctionIndividual femaleP = (FunctionIndividual) female;

            if (maleP.getDimension() == femaleP.getDimension()) {

                Point newPoints = new Point(maleP.getPoint());
                newPoints.average(femaleP.getPoint());

                return new FunctionIndividual(maleP.getFunction(), newPoints);
            } else {
                throw new IllegalArgumentException("Cannot perform cross-over with different length parents.");
            }
        }
        throw new IllegalArgumentException("Cannot perform function average cross-over if the parents aren't compatible with this operator.");
    }
}
