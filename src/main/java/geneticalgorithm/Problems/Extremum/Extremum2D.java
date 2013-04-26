/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems.Extremum;

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
import geneticalgorithm.Problems.Extremum.Extremum1D;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nono
 */
public class Extremum2D extends Extremum1D {

    private String strFunc = "x^2+y^2";
    private String pbLabel = "Min2D Problem";
    private double xMin = -1.0;
    private double xMax = 1.0;
    private double yMin = -1.0;
    private double yMax = 1.0;
    private double plotStep = 0.1;

    public Extremum2D() {
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
            fDUI = new PopulationFunction3DUI(strFunc, new PopulationFunctionController(pop), indColor, plotColor, plotStep);
        } catch (UnknownFunctionException | UnparsableExpressionException ex) {
            Logger.getLogger(Extremum2D.class.getName()).log(Level.SEVERE, "Incorrect input function.", ex);
        }
        function.addView(fDUI);
        pop.addView(fDUI);

        for (int i = 0; i < popSize; i++) {
            pop.add(new FunctionIndividual(function, Math.random()*(xMax-xMin)+xMin, Math.random()*(yMax-yMin)+yMin));
        }
        return pop;
    }
}
