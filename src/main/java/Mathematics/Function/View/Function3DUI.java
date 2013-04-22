/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mathematics.Function.View;

import GraphicalComponents.ObservationEvent;
import MvcPattern.RefreshEvent;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Population.Individuals.FunctionIndividual.FunctionIndividual;
import geneticalgorithm.Population.PopulationController;

/**
 *
 * @author nono
 */
public class Function3DUI extends FunctionUI {

    public Function3DUI(int sizeView, int popSize, PopulationController controller) {
        super(sizeView, popSize, controller);
    }

    @Override
    public void addIndividu(FunctionIndividual ind) throws UnknownFunctionException, UnparsableExpressionException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void refresh(RefreshEvent ev) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
