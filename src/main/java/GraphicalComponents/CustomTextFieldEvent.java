/*
 * Genetic Algorithm Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package GraphicalComponents;

/**
 *
 * @author simonneau
 */
public class CustomTextFieldEvent extends ObservationEvent{

    private String value;
    
    /**
     * Fired by CustomTextField.
     * @param source
     * @param value
     */
    public CustomTextFieldEvent(CustomTextField source, String value) {
        super(source);
        this.value = value;
    }
    
    /**
     *
     * @return
     */
    public String getValue(){
        return this.value;
    }
    
}
