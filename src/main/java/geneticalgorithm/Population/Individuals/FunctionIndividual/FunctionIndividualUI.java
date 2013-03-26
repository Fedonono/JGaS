/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Individuals.FunctionIndividual;

import MvcPattern.RefreshEvent;
import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.Individuals.IndividualUI;

/**
 *
 * @author nono
 */
public class FunctionIndividualUI extends IndividualUI {

    @Override
    public void refresh(RefreshEvent ev) {
        Individual ind = (Individual) ev.getSource();
    }
    
}
