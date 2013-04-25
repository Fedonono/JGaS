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
                
                this.target.refreshPopulation();

            }
    }
}
