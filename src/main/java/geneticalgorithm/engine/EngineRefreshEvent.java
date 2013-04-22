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

    public EngineRefreshEvent(GeneticEngine source, long timeout, int stepCount, double evolutionCriterion) {
        super(source);
        this.timeout = timeout;
        this.currentStepCount = stepCount;
        this.evolutionCriterion = evolutionCriterion;
    }

    public long getTimeout() {
        return this.timeout;
    }

    public int getStepCount() {
        return this.currentStepCount;
    }

    public double getEvolutionCriterion() {
        return this.evolutionCriterion;
    }
}
