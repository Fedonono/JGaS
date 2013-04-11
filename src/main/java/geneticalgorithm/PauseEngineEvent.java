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
public class PauseEngineEvent extends UserEvent{
    
    private boolean pause;
    
    public PauseEngineEvent(GeneticEngineUI source, boolean pause){
        super(source);
        this.pause = pause;
    }
    
    public boolean isPaused(){
        return this.pause;
    }
}
