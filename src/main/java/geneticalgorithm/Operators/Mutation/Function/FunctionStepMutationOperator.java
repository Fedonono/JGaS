/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.Mutation.Function;

import Mathematics.Points;
import geneticalgorithm.Operators.Mutation.MutationOperator;
import geneticalgorithm.Population.Individuals.FunctionIndividual.FunctionIndividual;
import geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author nono
 */
public class FunctionStepMutationOperator extends MutationOperator {
    
    private static String LABEL = "Random abscissa";
    
    public FunctionStepMutationOperator() {
        super(LABEL);
    }
    
    /**
     * 
     * @param individual
     * @return
     */
    @Override
    public Individual mutate(Individual individual) {
        
        if (individual instanceof FunctionIndividual) {
            
            FunctionIndividual individualP = (FunctionIndividual) individual;
            Points point = individualP.getPoints();

            int dim = 0;
            double min,max;
            Points domaine = individualP.getFunction().getDomaine();
            
            for (Double coordinate : point) {
                min = domaine.get(dim);
                max = domaine.get(dim+1);
                coordinate = stepMutation(coordinate, Math.random()*(max-min)+min); // creer une nouvelle reference sur Double mais n'écrase pas celle du point
                dim++;
            }
            return individualP;
        }
        // IncorrectIndividualException TODO BY ARNAUD
        return null;
    }

    private Double stepMutation(Double number, double randomStep) {
        return number.doubleValue()+randomStep;
    }
}
