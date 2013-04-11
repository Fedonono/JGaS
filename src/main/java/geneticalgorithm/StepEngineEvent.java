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
public class StepEngineEvent extends UserEvent{
    
    
    public StepEngineEvent(GeneticEngineUI source){
        super(source);
    }
}
