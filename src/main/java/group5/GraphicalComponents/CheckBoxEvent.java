/*
 * Minesweeper Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package genericGraphicalComponents;

/**
 *
 * @author simonneau
 */
public class CheckBoxEvent extends ObservationEvent {

    private boolean checked;
    
    /**
     *
     * @param source
     * @param checked
     */
    public CheckBoxEvent(CustomCheckBox source, boolean checked) {
        super(source);
        this.checked = checked;
    }
    
    /**
     *
     * @return
     */
    public boolean isChecked(){
        return this.checked;
    }
    
}
