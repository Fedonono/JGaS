/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems.Min1D;

import Mathematics.Function.Controller.FunctionController;
import Mathematics.Function.Model.Function;
import Mathematics.Function.Model.Function2D;
import Mathematics.Function.View.Function2DUI;
import Mathematics.Function.View.FunctionUI;
import Mathematics.Points;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Operators.CrossOver.Function.FunctionAverageCrossOverOperator;
import geneticalgorithm.Operators.Evaluation.Function.FunctionEvaluationOperator;
import geneticalgorithm.Operators.Mutation.Function.FunctionStepMutationOperator;
import geneticalgorithm.Operators.Selection.TruncationSelectionOperator;
import geneticalgorithm.Population.Individuals.FunctionIndividual.FunctionIndividual;
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
    protected static double xMin = 0.0;
    protected static double xMax = 1.0;

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
        Function function = null;
        FunctionUI fDUI = null;
        int popSize = this.getPopulationSize();

        try {
            function = new Function2D(strFunc, new Points(xMin, xMax));
            fDUI = new Function2DUI(this.getPopulationSize(), new FunctionController(function));
        } catch (UnknownFunctionException ex) {
            Logger.getLogger(Min1D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnparsableExpressionException ex) {
            Logger.getLogger(Min1D.class.getName()).log(Level.SEVERE, null, ex);
        }
        function.addView(fDUI);
        pop.addView(fDUI);

        for (int i = 0; i < popSize; i++) {
            pop.add(new FunctionIndividual(function, (Math.random() * (xMax - xMin)) + xMin));
        }
        return pop;
    }
}
