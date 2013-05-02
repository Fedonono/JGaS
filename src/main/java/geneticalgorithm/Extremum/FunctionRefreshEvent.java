/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Extremum;

import Mathematics.Function.Function;
import MvcPattern.RefreshEvent;

/**
 *
 * @author nono
 */
public class FunctionRefreshEvent extends RefreshEvent {

    /**
     *
     * @param source
     */
    public FunctionRefreshEvent(Function source) {
        super(source);
    }
    
}
