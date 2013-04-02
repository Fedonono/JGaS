/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems.Min1D;

import Mathematics.Function.Model.Function2D;
import Mathematics.Points;
import MvcPattern.View;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Population.Individuals.FunctionIndividual.FunctionIndividual;
import Mathematics.Function.Controller.FunctionController;
import Mathematics.Function.View.Function2DUI;
import geneticalgorithm.Population.Population;
import geneticalgorithm.Problems.Problem;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author nono
 */
public class Min1D extends Problem {
    // static pour les tests !
    private static Function2D function;
    private static Function2DUI viewToDel;
    private static FunctionController controller;

    // En faire une random par la suite, ceci sera utile juste pour les tests !
    //@Override
    //public Population createInitialPopulation() {
    public static void main(String[] args) throws UnknownFunctionException, UnparsableExpressionException {
        Population pop = new Population();
        try {
            function = new Function2D("sin(x)", new Points(0.0,1.0));
        } catch (UnknownFunctionException ex) {
            Logger.getLogger(Min1D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnparsableExpressionException ex) {
            Logger.getLogger(Min1D.class.getName()).log(Level.SEVERE, null, ex);
        }
        controller = new FunctionController(function);
        viewToDel = new Function2DUI(controller);
        function.addView(viewToDel);
        
        // Creation individu de test 1
        FunctionIndividual test1 = new FunctionIndividual(function, 0.1);
        
        // Creation individu de test 2
        FunctionIndividual test2 = new FunctionIndividual(function, 0.5);
        
        // Ajout à la population
        pop.addView(viewToDel);
        pop.add(test1);
        pop.add(test2);
        pop.notifyViews();
        JFrame frame = new JFrame();
        frame.add(viewToDel);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    @Override
    public Population createInitialPopulation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
