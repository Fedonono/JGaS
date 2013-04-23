/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.CrossOver.Function;

import Mathematics.Points;
import geneticalgorithm.Operators.CrossOver.CrossOverOperator;
import geneticalgorithm.Population.Individuals.FunctionIndividual.FunctionIndividual;
import geneticalgorithm.Population.Individuals.Individual;
import java.util.Iterator;

/**
 *
 * @author nono
 */
public class FunctionAverageCrossOverOperator extends CrossOverOperator {
    private static String LABEL = "average";
    
    public FunctionAverageCrossOverOperator(){
        super( LABEL);
    }

    @Override
    public Individual cross(Individual male, Individual female) {
        
        if ((male instanceof FunctionIndividual) && (female instanceof FunctionIndividual)) {
            
            FunctionIndividual child = null;
            FunctionIndividual maleP = (FunctionIndividual) male;
            FunctionIndividual femaleP = (FunctionIndividual) female;
            
            if (maleP.getDimension() == femaleP.getDimension()) {
                
                Points newPoints = new Points(maleP.getPoints());
                Iterator it = newPoints.iterator();
                Iterator itF = femaleP.getPoints().iterator();
                
                while (it.hasNext()){
                    
                    Double number = (Double) it.next();
                    double fNumber = (double) itF.next();
                    number = (number.doubleValue()+fNumber)/2;//creer une nouvelle referance mais n'écrase pas l'ancienne
                }
                child = new FunctionIndividual(maleP.getFunction(), newPoints);
            } else {
                // IncorrectIndividualDataException TODO BY ARNAUD
                return null;
            }
            return child;
        }
        // IncorrectIndividualException TODO BY ARNAUD
        return null;
    }
    
}
