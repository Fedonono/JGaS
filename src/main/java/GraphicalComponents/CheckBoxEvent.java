/*
 * Genetic Algorithm Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package GraphicalComponents;

/**
 *
 * @author simonneau
 */
public class CheckBoxEvent extends ObservationEvent {

    private boolean checked;
    
    /**
     * Fired by a CustomCheckBox
     * @param source
     * @param checked
     */
    public CheckBoxEvent(CustomCheckBox source, boolean checked) {
        super(source);
        this.checked = checked;
    }
    
    /**
     *
     * @return true if the CustomCheckBox is checked. False otherwise.
     */
    public boolean isChecked(){
        return this.checked;
    }
    
}
