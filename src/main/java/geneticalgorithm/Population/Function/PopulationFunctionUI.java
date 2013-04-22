/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Function;

import GraphicalComponents.CustomTextField;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Population.Individuals.FunctionIndividual.FunctionIndividual;
import geneticalgorithm.Population.PopulationController;
import geneticalgorithm.Population.PopulationUI;

/**
 *
 * @author nono
 */
public abstract class PopulationFunctionUI extends PopulationUI {
    protected CustomTextField functionChange;
    
    public PopulationFunctionUI(int sizeView, int popSize, PopulationController controller) {
        super(sizeView, popSize);
        this.setController(controller);
    }
    
    public abstract void addIndividu(FunctionIndividual ind) throws UnknownFunctionException, UnparsableExpressionException;

}
