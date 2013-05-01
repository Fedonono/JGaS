/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.WorldMap;

import MvcPattern.UserEvent;

/**
 *
 * @author simonneau
 */
public class ClearDestinationsUsrEvent extends UserEvent {
    /**
     *
     * @param source
     */
    public ClearDestinationsUsrEvent(DestinationPoolUI source){
        super(source);
    }
}
