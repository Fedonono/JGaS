/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Function;

import GraphicalComponents.CustomPlot.Custom2DPlot;
import GraphicalComponents.CustomPlot.CustomPlot;
import GraphicalComponents.CustomSpinner;
import GraphicalComponents.CustomTextField;
import GraphicalComponents.CustomTextFieldEvent;
import GraphicalComponents.ObservationEvent;
import GraphicalComponents.Observer;
import Mathematics.Function.Model.Function;
import Mathematics.Function.Model.Function2D;
import Mathematics.Point;
import MvcPattern.RefreshEvent;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Population.Individuals.FunctionIndividual.FunctionIndividual;
import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.Population;
import geneticalgorithm.Population.PopulationController;
import geneticalgorithm.Population.PopulationRefreshEvent;
import geneticalgorithm.Population.PopulationUI;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author nono
 */
public abstract class PopulationFunctionUI extends PopulationUI implements Observer {
    protected CustomPlot plot;
    protected CustomTextField functionChange;
    protected CustomSpinner xMin;
    protected CustomSpinner xMax;
    
    public PopulationFunctionUI(String strFunc, PopulationController controller) {
        this.setController(controller);
        this.functionChange = new CustomTextField(strFunc);
        this.functionChange.addObserver(this);
    }

    public void addIndividu(FunctionIndividual ind){
        this.plot.addIndividu(ind.getFunction(), ind.getPoint());
    }
    
    public abstract void createPlot(Function func) throws UnknownFunctionException, UnparsableExpressionException;
    public abstract void setFooter(JPanel footer);
    public abstract void domaineFooter(JPanel footer, Point domaine);
    public abstract void changeDomaineValue(Point domaine);
    public abstract void reactToChanges(ObservationEvent ev);

    public void reactEvent(ObservationEvent ev) {
        if (ev instanceof CustomTextFieldEvent) {
            CustomTextFieldEvent ctfe = (CustomTextFieldEvent) ev;
            String newFunc = ctfe.getValue();
            controller.applyChanges(new FunctionEvent(this, newFunc));
        }
    }
    
    @Override
    public void refresh(RefreshEvent ev) {
        super.refresh(ev);
        if (ev instanceof PopulationRefreshEvent) {
            
            PopulationRefreshEvent event = (PopulationRefreshEvent) ev;
            Population pop = (Population) event.getSource();
            FunctionIndividual funcInd = (FunctionIndividual) pop.getAlphaIndividual();
            Function func = funcInd.getFunction();
            
            
            try {
                this.plot.setPlot(func);
                
            } catch (UnknownFunctionException | UnparsableExpressionException ex) {
                Logger.getLogger(PopulationFunctionUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            LinkedList<Individual> samples = event.getSample();
            
            for (Individual sample : samples) {
                sample.notifyViews();
            }
        }
    }
}
