/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Individuals.FunctionIndividual;

import Mathematics.Function.Model.Function2D;
import MvcPattern.RefreshEvent;
import geneticalgorithm.Population.Function.PopulationFunctionUI;
import geneticalgorithm.Population.Individuals.IndividualUI;

/**
 *
 * @author nono
 */
public class FunctionIndividualUI extends IndividualUI {
    
    @Override
    public void refresh(RefreshEvent ev) {
        
        FunctionIndividual ind = (FunctionIndividual) ev.getSource();
        PopulationFunctionUI fUI = (PopulationFunctionUI) ind.getFunction().getUI();
        fUI.addIndividual(ind);
    }
}
