/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

/**
 *
 * @author simonneau
 */
public class SpinnerEvent extends ObservationEvent {

    Number value;
    
    public SpinnerEvent(Observable source, Number value) {
        super(source);
        this.value = value;
    }
    
    public Number getValue(){
        return this.value;
    }
    
}
