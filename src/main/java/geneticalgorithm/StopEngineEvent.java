/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import MvcPattern.UserEvent;

/**
 *
 * @author simonneau
 */
public class StopEngineEvent extends UserEvent {
    
    public StopEngineEvent(GeneticEngineUI source){
        super(source);
    }
}
