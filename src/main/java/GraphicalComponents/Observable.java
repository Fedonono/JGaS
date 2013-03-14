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
     *
     * @param o
     */
    public  void addObserver(Observer o);
    /**
     *
     */
    public void notifyObserver();
}
