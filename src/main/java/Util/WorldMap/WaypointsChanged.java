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
public class WaypointsChanged extends ObservationEvent{
    public WaypointsChanged(DestinationPoolUI source){
        super(source);
    }
}
