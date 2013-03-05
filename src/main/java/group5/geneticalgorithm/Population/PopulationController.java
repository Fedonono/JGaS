/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.geneticalgorithm.Population;

import group5.MvcPattern.UserEvent;
import group5.MvcPattern.Controller;

/**
 *
 * @author simonneau
 */
public class PopulationController extends Controller{
    
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
    
}
