/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Individuals.FunctionIndividual;

import Mathematics.Function.View.Function2DUI;
import MvcPattern.RefreshEvent;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Population.Individuals.IndividualUI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nono
 */
public class FunctionIndividualUI extends IndividualUI {

    @Override
    public void refresh(RefreshEvent ev) {
        FunctionIndividual ind = (FunctionIndividual) ev.getSource();
        Function2DUI fUI = (Function2DUI) ind.getFunction().getUI();
        try {
            fUI.addIndividu(ind);
        } catch (UnknownFunctionException ex) {
            Logger.getLogger(FunctionIndividualUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnparsableExpressionException ex) {
            Logger.getLogger(FunctionIndividualUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
