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
    
    public SpreadRefreshOrderEvent(Observable source, boolean needingRefresh) {
        super(source);
        this.needingRefresh = needingRefresh;
    }

    public boolean isNeedingRefresh() {
        return needingRefresh;
    }

    
    
    
    
}