/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.engine;

import MvcPattern.UserEvent;

/**
 *
 * @author simonneau
 */
public class PauseEngineEvent extends UserEvent{
    
    private boolean pause;
    
    /**
     *
     * @param source
     * @param pause
     */
    public PauseEngineEvent(GeneticEngineUI source, boolean pause){
        super(source);
        this.pause = pause;
    }
    
    /**
     *
     * @return
     */
    public boolean isPaused(){
        return this.pause;
    }
}
