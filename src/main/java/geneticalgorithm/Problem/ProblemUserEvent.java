/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problem;

import MvcPattern.UserEvent;

/**
 *
 * @author simonneau
 */
public class ProblemUserEvent extends UserEvent{

    /**
     *
     * @param source
     */
    public ProblemUserEvent(ProblemUI source) {
        super(source);
    }
    
}
