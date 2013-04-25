/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.Mutation.Function;

import Mathematics.Function.Model.Function;
import Mathematics.Point;
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
    
    @Override
    public Individual mutate(Individual individual) {
        
        if (individual instanceof FunctionIndividual) {
            
            FunctionIndividual functionIndividual = (FunctionIndividual) individual;
            Point individualPoint = functionIndividual.getPoint();

            int dim = 1;
            double min,max;
            Function func = functionIndividual.getFunction();
            Point domaine = func.getDomaine();
            Point mutantPoint = new Point();
            
            //creeation des coordonnées mutantes
            for (Double coordinate : individualPoint) {
                min = domaine.get(2*dim-2);
                max = domaine.get(2*dim-1);
                coordinate = stepMutation(coordinate, min, max);
                coordinate = func.minMaxDom(coordinate, min, max);
                mutantPoint.add(coordinate);
                dim++;
            }
            
            //mutation de l'individus
            return new FunctionIndividual(functionIndividual.getFunction(), mutantPoint);
        }
        throw new IllegalArgumentException("Cannot perform function step mutation if the individual is not compatible with this operator.");
    }

    private Double stepMutation(Double number, double min, double max) {
        double randomStep = Math.random()*(max-min);
        
        if(Math.random()>=0.5){
            randomStep *= -1;
        }
        return number.doubleValue()+randomStep;
    }
}
