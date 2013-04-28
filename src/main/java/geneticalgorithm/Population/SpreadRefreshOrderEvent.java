/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population;

import GraphicalComponents.Observable;
import GraphicalComponents.ValidateButtonEvent;

/**
 *
 * @author simonneau
 */
public class SpreadRefreshOrderEvent extends ValidateButtonEvent{

    boolean needingRefresh;
    
    /**
     *
     * @param source
     * @param needingRefresh
     */
    public SpreadRefreshOrderEvent(Observable source, boolean needingRefresh) {
        super(source);
        this.needingRefresh = needingRefresh;
    }

    /**
     *
     * @return
     */
    public boolean isNeedingRefresh() {
        return needingRefresh;
    }

    
    
    
    
}
