/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems.Min1D;

import Mathematics.Function;
import Mathematics.Points;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Population.Individuals.FunctionIndividual.FunctionIndividual;
import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.Population;
import geneticalgorithm.Problems.Problem;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nono
 */
public class Min1D_2D extends Problem {

    // En faire une random par la suite, ceci sera utile juste pour les tests !
    @Override
    public Population createInitialPopulation() {
        Population pop = new Population();
        Function function = null;
        try {
            function = new Function("sin(x)");
        } catch (UnknownFunctionException ex) {
            Logger.getLogger(Min1D_2D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnparsableExpressionException ex) {
            Logger.getLogger(Min1D_2D.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Creation individu de test 1
        Individual test1 = new FunctionIndividual(function, 1d);
        
        // Creation individu de test 2
        Individual test2 = new FunctionIndividual(function, 5d);
        
        // Ajout à la population
        pop.add(test1);
        pop.add(test2);
        return pop;
        
    }
    
}
