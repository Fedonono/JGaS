/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Extremum.Extremum2D;

import Mathematics.Function.Function;
import Mathematics.Function.Function3D;
import Mathematics.Point;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Extremum.PopulationFunction;
import geneticalgorithm.Extremum.PopulationFunctionController;
import geneticalgorithm.Extremum.PopulationFunctionUI;
import geneticalgorithm.Extremum.FunctionIndividual;
import geneticalgorithm.Population.Population;
import geneticalgorithm.Extremum.Extremum1D.Extremum1D;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nono
 */
public class Extremum2D extends Extremum1D {

    private String strFunc = "x^2+y^2";
    private String pbLabel = "Extremum2D Problem";
    private double xMin = -1.0;
    private double xMax = 1.0;
    private double yMin = -1.0;
    private double yMax = 1.0;
    private double plotStep = 0.1;

    /**
     *
     */
    public Extremum2D() {
        super();
        
        this.setLabel(pbLabel);
    }
    
    /**
     * return the initial population.
     * @return
     */
    @Override
    public Population createInitialPopulation() {
        PopulationFunction pop = null;
        Function function = null;
        PopulationFunctionUI fDUI = null;
        int popSize = this.getPopulationSize();

        try {
            function = new Function3D(strFunc, new Point(xMin, xMax, yMin, yMax));
            pop = new PopulationFunction(function);
            fDUI = new PopulationFunction2DUI(strFunc, new PopulationFunctionController(pop), indColor, plotColor, plotStep);
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
