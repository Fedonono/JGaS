/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mathematics.Function.Controller;

import Mathematics.Function.Model.Function;
import MvcPattern.UserEvent;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import Mathematics.Function.Events.FunctionEvent;
import geneticalgorithm.Population.Function.PopulationFunction;
import geneticalgorithm.Population.PopulationController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nono
 */
public class PopulationFunctionController extends PopulationController {

    PopulationFunction model;

    public PopulationFunctionController(PopulationFunction model) {
        this.model = model;
    }

    public PopulationFunction getModel() {
        return model;
    }

    @Override
    public void applyChanges(UserEvent event) {
        if(event instanceof FunctionEvent){
            FunctionEvent ev = (FunctionEvent)event;
            try {
                model.getFunction().setFunction(ev.getFunction());
            } catch (UnknownFunctionException ex) {
                Logger.getLogger(PopulationFunctionController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnparsableExpressionException ex) {
                Logger.getLogger(PopulationFunctionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
