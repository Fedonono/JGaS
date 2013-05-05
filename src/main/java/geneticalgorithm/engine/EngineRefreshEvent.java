/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.engine;

import MvcPattern.RefreshEvent;

/**
 *
 * @author simonneau
 */
public class EngineRefreshEvent extends RefreshEvent {

    long timeout;
    int currentStepCount;
    double evolutionCriterion;
    boolean paused;

    /**
     *
     * @param source
     * @param timeout
     * @param stepCount
     * @param evolutionCriterion
     * @param paused  
     */
    public EngineRefreshEvent(GeneticEngine source, long timeout, int stepCount, double evolutionCriterion, boolean paused) {
        super(source);
        this.timeout = timeout;
        this.currentStepCount = stepCount;
        this.evolutionCriterion = evolutionCriterion;
        this.paused = paused;
    }

    /**
     *
     * @return
     */
    public boolean isPaused() {
        return paused;
    }
    
    

    /**
     *
     * @return
     */
    public long getTimeout() {
        return this.timeout;
    }

    /**
     *
     * @return
     */
    public int getStepCount() {
        return this.currentStepCount;
    }

    /**
     *
     * @return
     */
    public double getEvolutionCriterion() {
        return this.evolutionCriterion;
    }
}
