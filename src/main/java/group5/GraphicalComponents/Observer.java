/*
 * Minesweeper Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package genericGraphicalComponents;

/**
 *
 * @author simonneau
 */
public interface Observer {
    
    /**
     *
     * @param ev
     */
    public void reactToChanges(ObservationEvent ev);
}
