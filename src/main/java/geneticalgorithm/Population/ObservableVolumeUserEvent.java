/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population;

import MvcPattern.UserEvent;
import MvcPattern.View;

/**
 *
 * @author simonneau
 */
public class ObservableVolumeUserEvent extends UserEvent{
    
    int value;
    
    /**
     *
     * @param source
     * @param value
     */
    public ObservableVolumeUserEvent(View source, int value){
        super(source);
        this.value = value;
    }

    /**
     *
     * @return
     */
    public int getValue() {
        return value;
    }
    
    
}
