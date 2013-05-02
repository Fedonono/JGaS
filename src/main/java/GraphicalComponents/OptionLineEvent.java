/*
 * Genetic Algorithm Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package GraphicalComponents;

/**
 *
 * @author simonneau
 */
public class OptionLineEvent extends ObservationEvent{
    
    int value;
    
    /**
     * Fired by an OptionLine.
     * @param source
     * @param value
     */
    public OptionLineEvent(OptionLine source, int value){
        super(source);
        this.value = value;
    }
    
    /**
     *
     * @return the source value.
     */
    public int getValue(){
        return this.value;
    }
}
