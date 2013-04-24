/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population;

import MvcPattern.UserEvent;

/**
 *
 * @author simonneau
 */
public class UsrAskForRefreshEvent extends UserEvent{
    
    public UsrAskForRefreshEvent(PopulationUI source){
        super(source);
    }
}
