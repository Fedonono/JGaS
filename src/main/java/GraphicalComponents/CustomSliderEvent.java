/*
 * Genetic Algorithm Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package GraphicalComponents;

/**
 *
 * @author simonneau
 */
public class CustomSliderEvent extends ObservationEvent {
    
    private int value;
    
    /**
     * Fired by a CustomSlider.
     * @param source
     * @param value
     */
    public CustomSliderEvent(CustomSlider source, int value){
        super(source);
        this.value = value;
    }
    
    /**
     *
     * @return
     */
    public int getValue(){
        return this.value;
    }
    
}
