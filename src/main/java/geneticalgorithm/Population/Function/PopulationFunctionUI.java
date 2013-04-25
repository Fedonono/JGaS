/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Function;

import Mathematics.Function.View.CustomPlot;
import GraphicalComponents.CustomTextField;
import GraphicalComponents.CustomTextFieldEvent;
import GraphicalComponents.ObservationEvent;
import GraphicalComponents.Observer;
import Mathematics.Function.Model.Function;
import Mathematics.Point;
import MvcPattern.RefreshEvent;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Population.Individuals.FunctionIndividual.FunctionIndividual;
import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.PopulationController;
import geneticalgorithm.Population.PopulationRefreshEvent;
import geneticalgorithm.Population.PopulationUI;
import java.awt.Color;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author nono
 */
public abstract class PopulationFunctionUI extends PopulationUI implements Observer {
    protected CustomPlot plot;
    protected CustomTextField functionChange;
    private boolean plotReset = false;
    
    public PopulationFunctionUI(String strFunc, PopulationController controller) {
        this.setController(controller);
        this.functionChange = new CustomTextField(strFunc);
        this.functionChange.addObserver(this);
    }

    public void addIndividual(FunctionIndividual ind){
        this.plot.addIndividual(ind.getFunction(), ind.getPoint());
    }
    
    public abstract void createPlot(Function func, Color[] indColor, Color plotColor, double plotStep) throws UnknownFunctionException, UnparsableExpressionException;
    public abstract void setFooter(JPanel footer);
    public abstract void domaineFooter(JPanel footer, Point domaine);
    public abstract void changeDomaineValue(Point domaine);
    @Override
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
            this.plotReset = false;
        }
    }
    
    @Override
    public void populationRefreshEventTreatment(PopulationRefreshEvent event){
        LinkedList<Individual> samples = event.getSample();

        for (Individual sample : samples) {
            this.add(sample);
        }
        
    }
    
    private void add(Individual individual){
        
        if(!this.plotReset){
            
            try {   
             this.plot.setPlot(((FunctionIndividual)individual).getFunction());   
            } catch (UnknownFunctionException | UnparsableExpressionException ex) {
                Logger.getLogger(PopulationFunctionUI.class.getName()).log(Level.SEVERE,  "Incorrect input function.", ex);
            }
            
            this.plotReset = true;
        }
        
        individual.notifyViews();
    }
}
