/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.WorldMap;

import GraphicalComponents.ObservationEvent;

/**
 *
 * @author simonneau
 */
public class WayPointChangedEvent extends ObservationEvent{
    
    /**
     *
     * @param source
     */
    public WayPointChangedEvent(DestinationPoolUI source){
        super(source);
    }
}
