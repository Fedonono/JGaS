/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems.StopCriteria;

import MvcPattern.UserEvent;

/**
 *
 * @author simonneau
 */
public class StopCriteriaUserCtrlEvent extends UserEvent{
    
    private int stepCount;
    private int timeout;
    private double evolutionCriterion;
    
    public StopCriteriaUserCtrlEvent(StopCriteriaUI source, int stepCount, int timeout, double evolutionCriterion){
        super(source);
    }

    public int getStepCount() {
        return stepCount;
    }

    public int getTimeout() {
        return timeout;
    }

    public double getEvolutionCriterion() {
        return evolutionCriterion;
    }
    
    
}
