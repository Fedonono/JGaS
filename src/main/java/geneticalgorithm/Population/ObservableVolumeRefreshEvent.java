/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population;

import MvcPattern.RefreshEvent;

/**
 *
 * @author simonneau
 */
public class ObservableVolumeRefreshEvent extends RefreshEvent{
    
    private int value;
    private int maxValue;
    
    public ObservableVolumeRefreshEvent(Population source, int value, int maxValue){
        super(source);
        this.value = value;
        this.maxValue = maxValue;
    }
    
    public int getValue(){
        return this.value;
    }

    public int getMaxValue() {
        return maxValue;
    }
    
    
}
