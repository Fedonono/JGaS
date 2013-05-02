/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problem.StopCriteria;

import MvcPattern.RefreshEvent;

/**
 *
 * @author simonneau
 */
public class StopCriteriaRefreshEvent extends RefreshEvent {

    /**
     *
     * @param source
     */
    public StopCriteriaRefreshEvent(StopCriteria source) {
        super(source);
    }
}
