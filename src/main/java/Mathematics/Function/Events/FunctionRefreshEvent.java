/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mathematics.Function.Events;

import Mathematics.Function.Model.Function;
import MvcPattern.RefreshEvent;

/**
 *
 * @author nono
 */
public class FunctionRefreshEvent extends RefreshEvent {

    public FunctionRefreshEvent(Function source) {
        super(source);
    }
    
}
