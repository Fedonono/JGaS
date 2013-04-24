/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Function;

import GraphicalComponents.CustomPlot.Custom2DPlot;
import GraphicalComponents.CustomSpinner;
import GraphicalComponents.CustomTextField;
import GraphicalComponents.CustomTextFieldEvent;
import GraphicalComponents.ObservationEvent;
import GraphicalComponents.Observer;
import GraphicalComponents.SpinnerEvent;
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
public class PopulationFunction2DUI extends PopulationFunctionUI implements Observer{
    
    private Custom2DPlot plot2D;
    private CustomSpinner xMin;
    private CustomSpinner xMax;

    public PopulationFunction2DUI(String strFunc, int sizeView, int popSize, PopulationController controller) throws UnknownFunctionException, UnparsableExpressionException {
        super(sizeView, popSize, controller);
        JPanel panel = new JPanel(new BorderLayout());
        this.functionChange = new CustomTextField(strFunc);
        PopulationFunction popC = (PopulationFunction) controller.getModel();
        Function2D func = (Function2D) popC.getFunction();
        this.plot2D = new Custom2DPlot(func);
        Point domaine = func.getDomaine();
        this.xMin = new CustomSpinner("xMin", Integer.MIN_VALUE, Integer.MAX_VALUE, domaine.get(0), 0.1);
        this.xMax = new CustomSpinner("xMax", Integer.MIN_VALUE, Integer.MAX_VALUE, domaine.get(1), 0.1);

        JPanel footer = new JPanel(new GridLayout(2, 1));

        JPanel xPanel = new JPanel();
        JPanel functionPanel = new JPanel();

        xPanel.add(xMin);
        xPanel.add(xMax);

        functionPanel.add(new JLabel("f(x) = "));
        functionPanel.add(functionChange);

        footer.add(functionPanel);
        footer.add(xPanel);

        this.functionChange.addObserver(this);
        this.xMin.addObserver(this);
        this.xMax.addObserver(this);

        panel.add(plot2D, BorderLayout.CENTER);
        panel.add(footer, BorderLayout.SOUTH);

        this.add(panel);
    }

    @Override
    public void addIndividu(FunctionIndividual ind){
        this.plot2D.addIndividu((Function2D) ind.getFunction(), ind.getPoint().get(0));
    }

    @Override
    public void refresh(RefreshEvent ev) {
        super.refresh(ev);
        if (ev instanceof FunctionRefreshEvent) { // A DELETE TO BY ARNAUD ?
            Function func = (Function) ev.getSource();
            Point domaine = func.getDomaine();
            this.xMin.setValue(domaine.get(0));
            this.xMax.setValue(domaine.get(1));
            try {
                this.plot2D.setPlot((Function2D) func);
            } catch (UnknownFunctionException | UnparsableExpressionException ex) {
                Logger.getLogger(PopulationFunction2DUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (ev instanceof PopulationRefreshEvent) {
            
            PopulationRefreshEvent event = (PopulationRefreshEvent) ev;
            Population pop = (Population) event.getSource();
            FunctionIndividual funcInd = (FunctionIndividual) pop.getAlphaIndividual();
            Function func = funcInd.getFunction();
            
            
            try {
                this.plot2D.setPlot((Function2D) func);
                
            } catch (UnknownFunctionException | UnparsableExpressionException ex) {
                Logger.getLogger(PopulationFunction2DUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            LinkedList<Individual> samples = event.getSample();
            
            for (Individual sample : samples) {
                sample.notifyViews();
            }
        }
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {

        if (ev instanceof SpinnerEvent) {
            controller.applyChanges(new DomaineEvent(this, new Point(xMin.getValue().doubleValue(), xMax.getValue().doubleValue())));
        }
        if (ev instanceof CustomTextFieldEvent) {
            CustomTextFieldEvent ctfe = (CustomTextFieldEvent) ev;
            String newFunc = ctfe.getValue();
            controller.applyChanges(new FunctionEvent(this, newFunc));
        }
    }
}
