/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Individuals.FunctionIndividual;

import MvcPattern.RefreshEvent;
import geneticalgorithm.Population.Function.PopulationFunction2DUI;
import geneticalgorithm.Population.Individuals.IndividualUI;

/**
 *
 * @author nono
 */
public class FunctionIndividualUI extends IndividualUI {
    
    @Override
    public void refresh(RefreshEvent ev) {
        
        FunctionIndividual ind = (FunctionIndividual) ev.getSource();
        PopulationFunction2DUI fUI = (PopulationFunction2DUI) ind.getFunction().getUI();
        fUI.addIndividu(ind);
    }
}
