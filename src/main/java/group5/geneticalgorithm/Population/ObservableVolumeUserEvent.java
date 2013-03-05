/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.geneticalgorithm.Population;

import group5.MvcPattern.UserEvent;
import group5.MvcPattern.View;

/**
 *
 * @author simonneau
 */
public class ObservableVolumeUserEvent extends UserEvent{
    
    int value;
    
    public ObservableVolumeUserEvent(View source, int value){
        super(source);
    }

    public int getValue() {
        return value;
    }
    
    
}
