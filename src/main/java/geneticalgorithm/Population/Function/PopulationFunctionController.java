/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Function;

import Mathematics.Function.Model.Function;
import MvcPattern.UserEvent;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Population.Function.PopulationFunction;
import geneticalgorithm.Population.ObservableVolumeUserEvent;
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
        System.out.println("controller appycahnges");
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
        if(event instanceof ObservableVolumeUserEvent){
            System.out.println("oui");
            ObservableVolumeUserEvent ev = (ObservableVolumeUserEvent)event;
            model.setObservableVolume(ev.getValue());
        }
    }
    
}
