/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import MvcPattern.RefreshEvent;

/**
 *
 * @author simonneau
 */
public class EngineStopedRefreshEvent extends RefreshEvent {

    public EngineStopedRefreshEvent(GeneticEngine source) {
        super(source);
    }
}
