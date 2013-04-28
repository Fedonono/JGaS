/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.WorldMap;

import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.Waypoint;

/**
 *
 * @author simonneau
 */
public class Destination extends Waypoint{
    
    private static int availableId = 0;
    
    private String Label;
    
    private int id = availableId++;
    
    /**
     *
     * @param label
     * @param x
     * @param y
     */
    public Destination(String label,GeoPosition position){
        super(position);
        this.Label = label;
    }
    
    public Destination(String label, double latitude, double longitude){
        this(label, new GeoPosition(latitude, longitude));
    }

    /**
     *
     * @return 'this' label.
     */
    public String getLabel() {
        return Label;
    }

    /**
     *
     * @return 'this' id.
     */
    public int getId() {
        return id;
    }
    /**
     * set 'this' label.
     * @param Label
     */
    public void setLabel(String Label) {
        this.Label = Label;
    }
    
    
}
