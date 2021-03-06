/*
 * Genetic Algorithm Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package GraphicalComponents;

/**
 *
 * @author simonneau
 */
public class ObservationEvent {
    private Observable source;

    /**
     * Fired by an Observable.
     * @param  source
     */
    public ObservationEvent(Observable source) {
        this.source = source;
    }    
    
    /**
     *
     * @return
     */
    public Observable getSource(){
        return this.source;
    }
    
    
}
