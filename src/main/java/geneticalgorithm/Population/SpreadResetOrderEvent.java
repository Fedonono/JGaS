/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population;

import GraphicalComponents.Observable;
import GraphicalComponents.ObservationEvent;

/**
 *
 * @author simonneau
 */
public class SpreadResetOrderEvent extends ObservationEvent {
    
    public SpreadResetOrderEvent(Observable source){
        super(source);
    }
}
