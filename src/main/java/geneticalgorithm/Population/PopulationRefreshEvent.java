/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population;

import MvcPattern.RefreshEvent;
import geneticalgorithm.Population.Individuals.Individual;
import java.util.LinkedList;

/**
 *
 * @author simonneau
 */
public class PopulationRefreshEvent extends RefreshEvent {

    LinkedList<Individual> sample;
    
    /**
     *
     * @param source
     * @param sample
     */
    public PopulationRefreshEvent(Population source, LinkedList<Individual> sample) {
        super(source);
        this.sample = sample;
    }

    /**
     *
     * @return
     */
    public LinkedList<Individual> getSample() {
        return sample;
    }
    
}
