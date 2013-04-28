/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.WorldMap;

import GraphicalComponents.IdentifiableComponent;
import MvcPattern.RefreshEvent;
import MvcPattern.View;

/**
 *
 * @author simonneau
 */
public class DestinationPoolUI extends IdentifiableComponent implements View{

    private DestinationPoolController controller;
    
    /**
     *
     * @param controller
     */
    public DestinationPoolUI(DestinationPoolController controller){
        this.controller = controller;
    }
    
    /**
     *
     * @param ev
     */
    @Override
    public void refresh(RefreshEvent ev) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
