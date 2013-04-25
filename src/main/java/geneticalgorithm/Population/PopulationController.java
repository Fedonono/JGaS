/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population;

import MvcPattern.Controller;
import MvcPattern.UserEvent;
import geneticalgorithm.Population.Function.PopulationFunction;

/**
 *
 * @author simonneau
 */
public class PopulationController implements Controller{
    
    protected PopulationFunction target;

    public void setModel(PopulationFunction model) {
        this.target = model;
    }
    
    @Override
    public void applyChanges(UserEvent event) {
        
        if(event instanceof ObservableVolumeUserEvent){
            ObservableVolumeUserEvent ev = (ObservableVolumeUserEvent)event;
            target.setObservableVolume(ev.getValue());
            
        }
    }

    public Population getModel() {
        return target;
    }
    
}
