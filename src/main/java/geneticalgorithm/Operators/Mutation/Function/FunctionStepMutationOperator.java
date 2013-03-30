/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.Mutation.Function;

import Mathematics.Function.Model.Function;
import Mathematics.Points;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Operators.Mutation.MutationOperator;
import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.Individuals.FunctionIndividual.FunctionIndividual;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nono
 */
public class FunctionStepMutationOperator extends MutationOperator {
    
    private static final double randomMin = -1;
    private static final double randomMax = 1;

    public FunctionStepMutationOperator(double prb) {
        super(prb);
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

            for (Double number : individualValues) {
                number = stepMutation(number, this.getRandomNumber(randomMin, randomMax));
            }
            /*try {
                Function.getInstance().inDomaine(individualValues);
            } catch (UnknownFunctionException ex) {
                Logger.getLogger(FunctionStepMutationOperator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnparsableExpressionException ex) {
                Logger.getLogger(FunctionStepMutationOperator.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            return individualP;
        }
        // IncorrectIndividualException TODO BY ARNAUD
        return null;
    }

    private Double stepMutation(Double number, double randomStep) {
        return number.doubleValue()+randomStep;
    }
    
    private double getRandomNumber(double min, double max) {
        return (Math.random() * (max-min)) + min;
    }
}
