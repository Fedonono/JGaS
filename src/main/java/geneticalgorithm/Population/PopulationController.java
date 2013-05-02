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
    protected Population target;
    
    /**
     *
     */
    public PopulationController(){
        this(null);
    }
    /**
     *
     * @param target
     */
    public PopulationController(Population target){
        this.target = target;
    }
    /**
     *
     */
    

    /**
     *
     * @param model
     */
    public void setModel(Population model) {
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
