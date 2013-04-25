/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.engine;

import MvcPattern.Controller;
import MvcPattern.UserEvent;

/**
 *
 * @author simonneau
 */
public class GeneticEngineUserCtrl implements Controller {

    private GeneticEngine target;
    private boolean previousEnginePauseState;

    public GeneticEngineUserCtrl(GeneticEngine target) {
        this.target = target;
    }

    @Override
    public void applyChanges(UserEvent event) {

        if (event instanceof PauseEngineEvent) {

            if (((PauseEngineEvent) event).isPaused()) {
                this.target.pause();
            } else {
                this.target.resume();
            }

        } else if (event instanceof StepEngineEvent) {
            this.target.step();

        } else if (event instanceof UsrAskForRefreshEvent) {
            
            UsrAskForRefreshEvent ev = (UsrAskForRefreshEvent)event;
            
            if(ev.isNeedingRefresh()){
                this.previousEnginePauseState = this.target.isPaused();
                
                if(this.previousEnginePauseState){
                    this.target.refreshPopulation();
                }else{
                    this.target.pause();
                }
                
            }else{
                if(!this.previousEnginePauseState){
                    this.target.resume();
                }
            }
            
            
        }
    }
}
