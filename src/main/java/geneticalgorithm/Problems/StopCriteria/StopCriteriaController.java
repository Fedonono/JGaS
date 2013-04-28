/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems.StopCriteria;

import MvcPattern.Controller;
import MvcPattern.UserEvent;

/**
 *
 * @author simonneau
 */
public class StopCriteriaController implements Controller{
    
    private StopCriteria target;

    /**
     *
     * @param target
     */
    public StopCriteriaController(StopCriteria target){
        this.target = target;
    }
    
    @Override
    public void applyChanges(UserEvent event) {
        
        if(event instanceof StopCriteriaUserCtrlEvent){
            StopCriteriaUserCtrlEvent ev = (StopCriteriaUserCtrlEvent)event;
            this.target.setMaxStepCount(ev.getStepCount());
            this.target.setTimeout(ev.getTimeout());
            this.target.setMinEvolutionCriterion(ev.getEvolutionCriterion());
        }
    }
    
}
