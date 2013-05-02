/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.WorldMap;

import MvcPattern.Model;
import java.util.Collection;
import java.util.LinkedList;
import org.jdesktop.swingx.mapviewer.GeoPosition;

/**
 *
 * @author simonneau
 */
public class DestinationPool extends Model {

    private LinkedList<Destination> destinations;
    
    
    /**
     *
     */
    public DestinationPool(){
        
        this.destinations = new LinkedList<>();
        this.addView(new DestinationPoolUI(new DestinationPoolController(this)));
        this.notifyViews();
    }

    /**
     * removes all destination from this.
     */
    public void clear() {
        this.destinations.clear();
        this.notifyViews();
    }
    
    /**
     *
     * @param label
     * @param gp
     */
    public void add(String label, GeoPosition gp){
        this.destinations.add(new Destination(label, gp));
        this.notifyViews();
    }

    /** add all destinations from destinations to this.
     *
     * @param destinations
     */
    public void addAll(Collection<Destination> destinations) {
        this.destinations.addAll(destinations);
        this.notifyViews();
    }

    /**
     *
     * @return 'this' destinations.
     */
    public LinkedList<Destination> getDestinations() {
        return destinations;
    }

    /**
     * reset 'this' destinations with destinations.
     * @param destinations
     */
    public void setDestinations(LinkedList<Destination> destinations) {
        this.destinations = destinations;
        this.notifyViews();
    }
    
    @Override
    public final void notifyViews(){
        super.notifyViews(new DestinationPoolRefreshEvent(this));
    }
    
    /**
     *
     * @return
     */
    @Override
    public DestinationPool clone(){
        
        DestinationPool clone = new DestinationPool();
        clone.addAll(this.destinations);
        clone.notifyViews();
        
        return clone;
    }
}
