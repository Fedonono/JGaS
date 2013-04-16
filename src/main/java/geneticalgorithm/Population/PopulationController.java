/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population;

import MvcPattern.UserEvent;
import MvcPattern.Controller;

/**
 *
 * @author simonneau
 */
public class PopulationController implements Controller{
    
    Population model;

    public void setModel(Population model) {
        this.model = model;
    }
    
    @Override
    public void applyChanges(UserEvent event) {
        if(event instanceof ObservableVolumeUserEvent){
            ObservableVolumeUserEvent ev = (ObservableVolumeUserEvent)event;
            model.setObservableVolume(ev.getValue());
        }
    }

    public Population getModel() {
        return model;
    }
    
}
