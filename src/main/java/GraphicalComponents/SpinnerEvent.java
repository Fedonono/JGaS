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

    int value;
    
    public SpinnerEvent(Observable source, int value) {
        super(source);
        this.value = value;
    }
    
    public int getValue(){
        return this.value;
    }
    
}
