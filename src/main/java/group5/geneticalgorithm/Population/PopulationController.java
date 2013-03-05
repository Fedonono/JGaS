/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.geneticalgorithm.Population;

import group5.MvcPattern.ControlEvent;
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
    public void applyChanges(ControlEvent event) {
        if(event instanceof PopulationControlEvent){
            PopulationControlEvent ev = (PopulationControlEvent)event;
            model.setObservableVolume(ev.getValue());
        }
    }
    
}
