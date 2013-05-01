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
    
    
    /**
     *
     * @param source
     * @param position
     * @param label
     */
    public AddDestinationUsrEvent(DestinationPoolUI source, GeoPosition position, String label){
        super(source);
        this.position = position;
        this.label = label;
    }

    /**
     *
     * @return
     */
    public GeoPosition getPosition() {
        return position;
    }

    /**
     *
     * @return
     */
    public String getLabel() {
        return label;
    }
    
    
}
