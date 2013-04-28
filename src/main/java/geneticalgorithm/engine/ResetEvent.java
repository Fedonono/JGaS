/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.engine;

import MvcPattern.UserEvent;

/**
 *
 * @author simonneau
 */
public class ResetEvent extends UserEvent {
    public ResetEvent(GeneticEngineUI source){
        super(source);
    }
}
