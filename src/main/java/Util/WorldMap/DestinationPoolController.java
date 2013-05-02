/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.WorldMap;

import MvcPattern.Controller;
import MvcPattern.UserEvent;

/**
 *
 * @author simonneau
 */
public class DestinationPoolController implements Controller{
    
    private DestinationPool target;
    
    /**
     *
     * @param target
     */
    public DestinationPoolController(DestinationPool target){
        this.target = target;
    }

    /**
     *
     * @param event
     */
    @Override
    public void applyChanges(UserEvent event) {
        if(event instanceof AddDestinationUsrEvent){
            
            AddDestinationUsrEvent ev = (AddDestinationUsrEvent)event;
            
            this.target.add(ev.getLabel(), ev.getPosition());
            
        }else if(event instanceof ClearDestinationsUsrEvent){
            
            this.target.clear();
        }
    }
    
}
