/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.engine;

import MvcPattern.UserEvent;
import MvcPattern.View;

/**
 *
 * @author simonneau
 */
public class UsrAskForRefreshEvent extends UserEvent{
    
    public UsrAskForRefreshEvent(View source){
        super(source);
    }
}
