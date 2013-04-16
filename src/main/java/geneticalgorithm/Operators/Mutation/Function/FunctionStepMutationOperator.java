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
    
    public FunctionStepMutationOperator(double prb) {
        super(prb, LABEL);
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
            Points individualValues = individualP.getPoints();

            int dim = 0;
            double min,max;
            Points domaine = individualP.getFunction().getDomaine();
            for (Double number : individualValues) {
                /*min = domaine.get(dim);
                max = domaine.get(dim+1); FIX TEMPORAIRE TODO BY ARNAUD */
                min = domaine.get(0);
                max = domaine.get(1);
                number = stepMutation(number, Math.random()*(max-min)+min);
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
