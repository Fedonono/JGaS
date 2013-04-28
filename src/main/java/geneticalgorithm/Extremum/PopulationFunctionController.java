/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Extremum;

import MvcPattern.UserEvent;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Population.PopulationController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nono
 */
public class PopulationFunctionController extends PopulationController {

    /**
     *
     * @param model
     */
    public PopulationFunctionController(PopulationFunction model) {
        this.target = model;
    }

    /**
     *
     * @return 'this target model.
     */
    @Override
    public PopulationFunction getModel() {
        return target;
    }

    /**
     *
     * @param event
     */
    @Override
    public void applyChanges(UserEvent event) {        
        super.applyChanges(event);

        if(event instanceof FunctionEvent){
            FunctionEvent ev = (FunctionEvent)event;
            try {
                target.getFunction().setFunction(ev.getFunction());
            } catch (UnknownFunctionException | UnparsableExpressionException ex) {
                Logger.getLogger(PopulationFunctionController.class.getName()).log(Level.SEVERE, "Incorrect input function.", ex);
            }
        }
        if (event instanceof DomaineEvent){
            DomaineEvent ev = (DomaineEvent)event;
            target.getFunction().setDomaine(ev.getValue());
        }
    }
    
}
