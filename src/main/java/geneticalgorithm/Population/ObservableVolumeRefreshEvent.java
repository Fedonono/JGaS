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
    
    /**
     *
     * @param source
     * @param value
     * @param maxValue
     */
    public ObservableVolumeRefreshEvent(Population source, int value, int maxValue){
        super(source);
        this.value = value;
        this.maxValue = maxValue;
    }
    
    /**
     *
     * @return
     */
    public int getValue(){
        return this.value;
    }

    /**
     *
     * @return
     */
    public int getMaxValue() {
        return maxValue;
    }
    
    
}
