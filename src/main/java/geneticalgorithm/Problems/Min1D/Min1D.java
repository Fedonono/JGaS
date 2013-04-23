/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems.Min1D;

import geneticalgorithm.Population.Function.PopulationFunctionController;
import Mathematics.Function.Model.Function;
import Mathematics.Function.Model.Function2D;
import geneticalgorithm.Population.Function.PopulationFunction2DUI;
import geneticalgorithm.Population.Function.PopulationFunctionUI;
import Mathematics.Points;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Operators.CrossOver.Function.FunctionAverageCrossOverOperator;
import geneticalgorithm.Operators.Evaluation.Function.FunctionEvaluationOperator;
import geneticalgorithm.Operators.Mutation.Function.FunctionStepMutationOperator;
import geneticalgorithm.Operators.Selection.TruncationSelectionOperator;
import geneticalgorithm.Population.Function.PopulationFunction;
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
        this.addCrossOverOperator(new FunctionAverageCrossOverOperator());
        this.addMutationOperator(new FunctionStepMutationOperator());
        this.addEvaluationOperator(new FunctionEvaluationOperator());
        
        this.setLabel(pbLabel);
    }
    
    @Override
    public Population createInitialPopulation() {
        PopulationFunction pop = null;
        Function function = null;
        PopulationFunctionUI fDUI = null;
        int popSize = this.getPopulationSize();

        try {
            function = new Function2D(strFunc, new Points(xMin, xMax));
            pop = new PopulationFunction(function);
            fDUI = new PopulationFunction2DUI(pop.getObservableVolume(), popSize, new PopulationFunctionController(pop));
        } catch (UnknownFunctionException ex) {
            Logger.getLogger(Min1D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnparsableExpressionException ex) {
            Logger.getLogger(Min1D.class.getName()).log(Level.SEVERE, null, ex);
        }
        function.addView(fDUI); // A DELETE ? TODO BY ARNAUD
        pop.addView(fDUI);

        for (int i = 0; i < popSize; i++) {
            pop.add(new FunctionIndividual(function, (Math.random() * (xMax - xMin)) + xMin));
        }
        return pop;
    }
}
