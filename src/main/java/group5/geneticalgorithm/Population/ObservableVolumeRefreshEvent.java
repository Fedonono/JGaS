/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.geneticalgorithm.Population;

import group5.MvcPattern.Model;
import group5.MvcPattern.RefreshEvent;

/**
 *
 * @author simonneau
 */
public class ObservableVolumeRefreshEvent extends RefreshEvent{
    
    private int value;
    
    public ObservableVolumeRefreshEvent(Population source, int value){
        super(source);
        this.value = value;
    }
    
    public int getValue(){
        return this.value;
    }
}
