/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.WorldMap;

import MvcPattern.Model;
import java.util.Collection;
import java.util.LinkedList;

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
    }

    /**
     * removes all destination from this.
     */
    public void clear() {
        this.destinations.clear();
    }

    /**
     * add a destination to this.
     * @param d
     */
    public void add(Destination d) {
        this.destinations.add(d);
    }

    /** add all destinations from destinations to this.
     *
     * @param destinations
     */
    public void addAll(Collection<Destination> destinations) {
        this.destinations.addAll(destinations);
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
    }

    /**
     * add a destination to 'this'.
     * @param label
     * @param x
     * @param y
     */
    public void addDestination(String label, int x, int y) {
        
        this.add(new Destination(label, x, y));
    }
}
