/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems.Min1D;

import Mathematics.Function.Model.Function2D;
import Mathematics.Points;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Population.Individuals.FunctionIndividual.FunctionIndividual;
import Mathematics.Function.Controller.FunctionController;
import Mathematics.Function.View.Function2DUI;
import geneticalgorithm.Operators.CrossOver.Function.FunctionAverageCrossOverOperator;
import geneticalgorithm.Operators.Evaluation.Function.FunctionEvaluationOperator;
import geneticalgorithm.Operators.Mutation.Function.FunctionStepMutationOperator;
import geneticalgorithm.Operators.Selection.TruncationSelectionOperator;
import geneticalgorithm.Population.Population;
import geneticalgorithm.Problems.Problem;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nono
 */
public class Min1D extends Problem {

    private static String strFunc = "sin(x)";
    private static String pbLabel = "Min1D Problem";

    public Min1D() {
        this.addSelectionOperator(TruncationSelectionOperator.getInstance());
        this.addCrossOverOperator(new FunctionAverageCrossOverOperator(this.getCrossProbability()));
        this.addMutationOperator(new FunctionStepMutationOperator(this.getMutationProbability()));
        this.addEvaluationOperator(new FunctionEvaluationOperator());
        
        this.setLabel(pbLabel);
    }
    
    @Override
    public Population createInitialPopulation() {
        Population pop = new Population();
        Function2D function = null;
        Function2DUI f2DUI = null;
        int popSize = this.getPopulationSize();

        try {
            function = new Function2D(strFunc, new Points(0.0,1.0));
            f2DUI = new Function2DUI(new FunctionController(function));
        } catch (UnknownFunctionException ex) {
            Logger.getLogger(Min1D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnparsableExpressionException ex) {
            Logger.getLogger(Min1D.class.getName()).log(Level.SEVERE, null, ex);
        }
        function.addView(f2DUI);
        pop.addView(f2DUI);

        double min = function.getDomaine().get(0);
        double max = function.getDomaine().get(1);
        for (int i = 0; i < popSize; i++) {
            pop.add(new FunctionIndividual(function, (Math.random() * (max -min)) + min));
        }
        return pop;
    }
}
