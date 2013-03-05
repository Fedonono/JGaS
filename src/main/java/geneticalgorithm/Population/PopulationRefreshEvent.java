/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population;

import MvcPattern.RefreshEvent;
import geneticalgorithm.Population.Individuals.IndividualUI;
import java.util.LinkedList;

/**
 *
 * @author simonneau
 */
public class PopulationRefreshEvent extends RefreshEvent {

    LinkedList<IndividualUI> sample;
    
    public PopulationRefreshEvent(Population source, LinkedList<IndividualUI> sample) {
        super(source);
        this.sample = sample;
    }

    public LinkedList<IndividualUI> getSample() {
        return sample;
    }
    
}
