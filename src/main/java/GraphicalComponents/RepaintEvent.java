/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

/**
 *
 * @author simonneau
 */
public class RepaintEvent extends ObservationEvent{
    
    /**
     *
     * @param source
     */
    public RepaintEvent(Observable source){
        super(source);
    }
}
