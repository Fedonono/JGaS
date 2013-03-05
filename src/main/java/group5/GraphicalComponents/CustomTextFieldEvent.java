/*
 * Minesweeper Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package genericGraphicalComponents;

/**
 *
 * @author simonneau
 */
public class CustomTextFieldEvent extends ObservationEvent{

    private String value;
    
    /**
     *
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
