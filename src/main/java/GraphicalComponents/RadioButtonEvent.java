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
    
    public RadioButtonEvent(CustomRadioButton source, boolean selected){
        super(source);
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
    
    
}
