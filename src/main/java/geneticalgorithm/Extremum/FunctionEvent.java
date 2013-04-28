/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Extremum;

import MvcPattern.UserEvent;
import MvcPattern.View;

/**
 *
 * @author nono
 */
public class FunctionEvent extends UserEvent {
    
    String function;
    
    /**
     *
     * @param source
     * @param function
     */
    public FunctionEvent(View source, String function){
        super(source);
        this.function = function;
    }

    /**
     *
     * @return
     */
    public String getFunction() {
        return function;
    }
}
