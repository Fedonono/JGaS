/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

/**
 *
 * @author simonneau
 */
public class RadioButtonEvent extends ObservationEvent{
    
    private boolean selected;
    
    /**
     *
     * @param source
     * @param selected
     */
    public RadioButtonEvent(CustomRadioButton source, boolean selected){
        super(source);
        this.selected = selected;
    }

    /**
     *
     * @return
     */
    public boolean isSelected() {
        return selected;
    }
    
    
}
