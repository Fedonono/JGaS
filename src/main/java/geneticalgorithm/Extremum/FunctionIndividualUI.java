/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Extremum;

import MvcPattern.RefreshEvent;
import geneticalgorithm.Extremum.PopulationFunctionUI;
import geneticalgorithm.Population.Individuals.IndividualUI;

/**
 *
 * @author nono
 */
public class FunctionIndividualUI extends IndividualUI {
    
    /**
     *
     * @param ev
     */
    @Override
    public void refresh(RefreshEvent ev) {
        
        FunctionIndividual ind = (FunctionIndividual) ev.getSource();
        PopulationFunctionUI fUI = (PopulationFunctionUI) ind.getFunction().getUI();
        fUI.addIndividual(ind);
    }
}
