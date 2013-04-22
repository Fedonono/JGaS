/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Function;

import MvcPattern.UserEvent;
import MvcPattern.View;

/**
 *
 * @author nono
 */
public class FunctionEvent extends UserEvent {
    
    String function;
    
    public FunctionEvent(View source, String function){
        super(source);
        this.function = function;
    }

    public String getFunction() {
        return function;
    }
}
