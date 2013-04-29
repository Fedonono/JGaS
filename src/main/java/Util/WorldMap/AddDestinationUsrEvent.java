/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.WorldMap;

import MvcPattern.UserEvent;
import org.jdesktop.swingx.mapviewer.GeoPosition;

/**
 *
 * @author simonneau
 */
public class AddDestinationUsrEvent extends UserEvent{
    
    private GeoPosition position;
    private String label;
    
    
    public AddDestinationUsrEvent(DestinationPoolUI source, GeoPosition position, String label){
        super(source);
        this.position = position;
        this.label = label;
    }

    public GeoPosition getPosition() {
        return position;
    }

    public String getLabel() {
        return label;
    }
    
    
}
