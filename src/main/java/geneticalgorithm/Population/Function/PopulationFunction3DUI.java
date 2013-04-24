/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Function;

import MvcPattern.RefreshEvent;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Population.Individuals.FunctionIndividual.FunctionIndividual;
import geneticalgorithm.Population.PopulationController;

/**
 *
 * @author nono
 */
public class PopulationFunction3DUI extends PopulationFunctionUI {

    public PopulationFunction3DUI(String strFunc, int sizeView, int popSize, PopulationController controller) {
        super(sizeView, popSize, controller);
    }

    @Override
    public void addIndividu(FunctionIndividual ind) throws UnknownFunctionException, UnparsableExpressionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void refresh(RefreshEvent ev) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
