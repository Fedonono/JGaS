/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mathematics.Function.View;

import GraphicalComponents.CustomTextField;
import Mathematics.Function.Controller.PopulationFunctionController;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Population.Individuals.FunctionIndividual.FunctionIndividual;
import geneticalgorithm.Population.PopulationController;
import geneticalgorithm.Population.PopulationUI;

/**
 *
 * @author nono
 */
public abstract class FunctionUI extends PopulationUI {
    protected CustomTextField functionChange;
    
    public FunctionUI(int size, PopulationController controller) {
        super(size);
        this.setController(controller);
    }
    
    public abstract void addIndividu(FunctionIndividual ind) throws UnknownFunctionException, UnparsableExpressionException;

}
