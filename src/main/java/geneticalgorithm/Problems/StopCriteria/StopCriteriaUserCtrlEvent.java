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
    
    /**
     *
     * @param source
     * @param stepCount
     * @param timeout
     * @param evolutionCriterion
     */
    public StopCriteriaUserCtrlEvent(StopCriteriaUI source, int stepCount, int timeout, double evolutionCriterion){
        super(source);
        this.stepCount = stepCount;
        this.timeout = timeout;
        this.evolutionCriterion = evolutionCriterion;
    }

    /**
     *
     * @return
     */
    public int getStepCount() {
        return stepCount;
    }

    /**
     *
     * @return
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     *
     * @return
     */
    public double getEvolutionCriterion() {
        return evolutionCriterion;
    }
    
    
}
