/*
 * Minesweeper Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package genericGraphicalComponents;

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
