/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.WorldMap;

import MvcPattern.RefreshEvent;

/**
 *
 * @author simonneau
 */
public class DestinationPoolRefreshEvent extends RefreshEvent{
    
    /**
     *
     * @param source
     */
    public DestinationPoolRefreshEvent(DestinationPool source){
        super(source);
    }
}
