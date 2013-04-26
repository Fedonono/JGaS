/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems.Extremum;

import Mathematics.Function.Model.Function;
import Mathematics.Function.Model.Function2D;
import Mathematics.Point;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Operators.CrossOver.Function.FunctionAverageCrossOverOperator;
import geneticalgorithm.Operators.Evaluation.Function.FunctionMaximumEvaluationOperator;
import geneticalgorithm.Operators.Evaluation.Function.FunctionMinimumEvaluationOperator;
import geneticalgorithm.Operators.Mutation.Function.FunctionStepMutationOperator;
import geneticalgorithm.Population.Function.PopulationFunction;
import geneticalgorithm.Population.Function.PopulationFunction2DUI;
import geneticalgorithm.Population.Function.PopulationFunctionController;
import geneticalgorithm.Population.Function.PopulationFunctionUI;
import geneticalgorithm.Population.Individuals.FunctionIndividual.FunctionIndividual;
import geneticalgorithm.Population.Population;
import geneticalgorithm.Problems.Problem;
import geneticalgorithm.Problems.Problem;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nono
 */
public class Extremum1D extends Problem {

    private String strFunc = "sin(x)";
    private String pbLabel = "Min1D Problem";
    private double xMin = -10.0;
    private double xMax = 10.0;
    private double plotStep = 0.1;
    /**
     * Couleur de la courbe.
     */
    protected Color plotColor = Color.DARK_GRAY;
    /**
     * Couleur des individus du meilleur jusqu'aux individus moins bons.
     */
    protected Color[] indColor = {Color.RED, Color.PINK, Color.ORANGE, Color.GREEN, Color.BLUE};

    public Extremum1D() {
        this.addCrossOverOperator(new FunctionAverageCrossOverOperator());
        this.addMutationOperator(new FunctionStepMutationOperator());
        this.addEvaluationOperator(new FunctionMinimumEvaluationOperator());
        this.addEvaluationOperator(new FunctionMaximumEvaluationOperator());
        
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
            Logger.getLogger(Extremum1D.class.getName()).log(Level.SEVERE, "Incorrect input function.", ex);
        }
        function.addView(fDUI);
        pop.addView(fDUI);

        for (int i = 0; i < popSize; i++) {
            pop.add(new FunctionIndividual(function, (Math.random() * (xMax - xMin)) + xMin));
        }
        return pop;
    }
}
