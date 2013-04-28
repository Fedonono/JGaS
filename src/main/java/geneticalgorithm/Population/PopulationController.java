/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population;

import MvcPattern.Controller;
import MvcPattern.UserEvent;
import geneticalgorithm.Extremum.PopulationFunction;

/**
 *
 * @author simonneau
 */
public class PopulationController implements Controller{
    
    /**
     *
     */
    protected PopulationFunction target;

    /**
     *
     * @param model
     */
    public void setModel(PopulationFunction model) {
        this.target = model;
    }
    
    /**
     *
     * @param event
     */
    @Override
    public void applyChanges(UserEvent event) {
        
        if(event instanceof ObservableVolumeUserEvent){
            ObservableVolumeUserEvent ev = (ObservableVolumeUserEvent)event;
            target.setObservableVolume(ev.getValue());
            
        }
    }

    /**
     *
     * @return
     */
    public Population getModel() {
        return target;
    }
    
}
