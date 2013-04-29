/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.TSP;

import MvcPattern.Model;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
import Util.WorldMap.Destination;
import Util.WorldMap.DestinationPoolUI;
import java.util.ArrayList;

/**
 *
 * @author simonneau
 */
public class TSPIndividualUI implements View{

    @Override
    public void refresh(RefreshEvent ev) {
        Model source = ev.getSource();
        
        if(source instanceof TSPIndividual){
            TSPIndividual individual = (TSPIndividual)source;
            
            ArrayList<Destination> destinations = individual.getPath();
            DestinationPoolUI dpUI = (DestinationPoolUI)individual.getDestinations().getUI();
            dpUI.drawLoopPath(destinations);            
        }
    }
    
}
