/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mathematics.Function.Controller;

import Mathematics.Function.Model.Function;
import Mathematics.Function.Model.Function2D;
import MvcPattern.Controller;
import MvcPattern.UserEvent;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Problems.Min1D.Min1D;
import Mathematics.Function.Events.FunctionEvent;
import geneticalgorithm.Population.PopulationController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nono
 */
public class FunctionController extends PopulationController {

    Function model;

    public FunctionController(Function model) {
        this.model = model;
    }

    public Function getModel() {
        return model;
    }

    @Override
    public void applyChanges(UserEvent event) {
        if(event instanceof FunctionEvent){
            FunctionEvent ev = (FunctionEvent)event;
            try {
                model.setFunction(ev.getFunction());
            } catch (UnknownFunctionException ex) {
                Logger.getLogger(FunctionController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnparsableExpressionException ex) {
                Logger.getLogger(FunctionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
