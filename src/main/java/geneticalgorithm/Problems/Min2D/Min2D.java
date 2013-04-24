/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems.Min2D;

import Mathematics.Function.Model.Function;
import Mathematics.Function.Model.Function3D;
import Mathematics.Point;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Population.Function.PopulationFunction;
import geneticalgorithm.Population.Function.PopulationFunction3DUI;
import geneticalgorithm.Population.Function.PopulationFunctionController;
import geneticalgorithm.Population.Function.PopulationFunctionUI;
import geneticalgorithm.Population.Individuals.FunctionIndividual.FunctionIndividual;
import geneticalgorithm.Population.Population;
import geneticalgorithm.Problems.Min1D.Min1D;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nono
 */
public class Min2D extends Min1D {

    private static String strFunc = "sin(x+y)";
    private static String pbLabel = "Min2D Problem";
    private static double yMin = 0.0;
    private static double yMax = 1.0;

    public Min2D() {
        super();
        
        this.setLabel(pbLabel);
    }
    
    @Override
    public Population createInitialPopulation() {
        PopulationFunction pop = null;
        Function function = null;
        PopulationFunctionUI fDUI = null;
        int popSize = this.getPopulationSize();

        try {
            function = new Function3D(strFunc, new Point(xMin, xMax, yMin, yMax));
            pop = new PopulationFunction(function);
            fDUI = new PopulationFunction3DUI(strFunc, new PopulationFunctionController(pop));
        } catch (UnknownFunctionException | UnparsableExpressionException ex) {
            Logger.getLogger(Min1D.class.getName()).log(Level.SEVERE, null, ex);
        }
        function.addView(fDUI);
        pop.addView(fDUI);

        for (int i = 0; i < popSize; i++) {
            pop.add(new FunctionIndividual(function, Math.random()*(xMax-xMin)+xMin, Math.random()*(yMax-yMin)+yMin));
        }
        return pop;
    }
}
