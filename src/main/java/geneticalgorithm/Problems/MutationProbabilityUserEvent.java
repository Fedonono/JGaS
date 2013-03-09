/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems;

import MvcPattern.UserEvent;
import MvcPattern.View;

/**
 *
 * @author simonneau
 */
public class MutationProbabilityUserEvent extends UserEvent{
    
    private int value;

    public MutationProbabilityUserEvent(View source, int value) {
        super(source);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
