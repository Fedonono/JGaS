/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems;

import MvcPattern.RefreshEvent;

/**
 *
 * @author simonneau
 */
public class ProblemRefreshEvent extends RefreshEvent {

    /**
     *
     * @param source
     */
    public ProblemRefreshEvent(Problem source) {
        super(source);
    }
    
}
