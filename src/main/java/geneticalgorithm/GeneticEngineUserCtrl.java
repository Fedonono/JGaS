/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import MvcPattern.Controller;
import MvcPattern.UserEvent;

/**
 *
 * @author simonneau
 */
public class GeneticEngineUserCtrl implements Controller{

    private GeneticEngine target;
    
    public GeneticEngineUserCtrl(GeneticEngine target){
        this.target = target;
    }
    
    @Override
    public void applyChanges(UserEvent event) {
        if(event instanceof PauseEngineEvent){
            
            if(((PauseEngineEvent)event).isPaused()){
                this.target.stop();
            }else{
                this.target.start();
            }
            
        }else if(event instanceof StepEngineEvent){
            this.target.evolve();
        }else if(event instanceof StopEngineEvent){
            this.target.stop();
        }
    }
    
}
