/*
 * Genetic Algorithm Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package GraphicalComponents;

/**
 *
 * @author simonneau
 */
public interface Observable {
    
    /**
     * add an Observer to this.
     * @param o
     */
    public  void addObserver(Observer o);
    
    /**
     * notify 'this' Observers
     */
    public void notifyObservers();
}
