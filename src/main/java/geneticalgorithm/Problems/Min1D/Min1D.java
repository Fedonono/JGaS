/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems.Min1D;

import Mathematics.Function.Model.Function;
import Mathematics.Function.Model.Function2D;
import Mathematics.Point;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Operators.CrossOver.Function.FunctionAverageCrossOverOperator;
import geneticalgorithm.Operators.Evaluation.Function.FunctionEvaluationOperator;
import geneticalgorithm.Operators.Mutation.Function.FunctionStepMutationOperator;
import geneticalgorithm.Population.Function.PopulationFunction;
import geneticalgorithm.Population.Function.PopulationFunction2DUI;
import geneticalgorithm.Population.Function.PopulationFunctionController;
import geneticalgorithm.Population.Function.PopulationFunctionUI;
import geneticalgorithm.Population.Individuals.FunctionIndividual.FunctionIndividual;
import geneticalgorithm.Population.Population;
import geneticalgorithm.Problems.Problem;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nono
 */
public class Min1D extends Problem {

    private static String strFunc = "sin(x)";
    private static String pbLabel = "Min1D Problem";
    private static double xMin = -10.0;
    private static double xMax = 10.0;
    private static double plotStep = 0.1;
    /**
     * Couleur de la courbe.
     */
    protected static Color plotColor = Color.DARK_GRAY;
    /**
     * Couleur des individus du meilleur jusqu'aux individus moins bons.
     */
    protected static Color[] indColor = {Color.RED, Color.PINK, Color.ORANGE, Color.GREEN, Color.BLUE};

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
            function = new Function2D(strFunc, new Point(xMin, xMax));
            pop = new PopulationFunction(function);
            fDUI = new PopulationFunction2DUI(strFunc, new PopulationFunctionController(pop), indColor, plotColor, plotStep);
        } catch (UnknownFunctionException | UnparsableExpressionException ex) {
            Logger.getLogger(Min1D.class.getName()).log(Level.SEVERE, "Incorrect input function.", ex);
        }
        function.addView(fDUI);
        pop.addView(fDUI);

        for (int i = 0; i < popSize; i++) {
            pop.add(new FunctionIndividual(function, (Math.random() * (xMax - xMin)) + xMin));
        }
        return pop;
    }
}
