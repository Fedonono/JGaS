/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mathematics.Function.View;

import GraphicalComponents.CustomPlot.Custom2DPlot;
import GraphicalComponents.CustomSpinner;
import GraphicalComponents.CustomTextField;
import GraphicalComponents.CustomTextFieldEvent;
import GraphicalComponents.ObservationEvent;
import GraphicalComponents.SpinnerEvent;
import Mathematics.Points;
import MvcPattern.RefreshEvent;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import Mathematics.Function.Model.Function;
import Mathematics.Function.Model.Function2D;
import Mathematics.Function.Events.DomaineEvent;
import Mathematics.Function.Events.FunctionEvent;
import Mathematics.Function.Events.FunctionRefreshEvent;
import geneticalgorithm.Population.Function.PopulationFunction;
import geneticalgorithm.Population.Individuals.FunctionIndividual.FunctionIndividual;
import geneticalgorithm.Population.Population;
import geneticalgorithm.Population.PopulationController;
import geneticalgorithm.Population.PopulationRefreshEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author nono
 */
public class Function2DUI extends FunctionUI {

    private Custom2DPlot plot2D;
    private CustomSpinner xMin;
    private CustomSpinner xMax;

    public Function2DUI(int size, PopulationController controller) throws UnknownFunctionException, UnparsableExpressionException {
        super(size, controller);
        this.setLayout(new BorderLayout());

        this.functionChange = new CustomTextField("sin(x)");
        PopulationFunction popC = (PopulationFunction) controller.getModel();
        this.plot2D = new Custom2DPlot((Function2D)popC.getFunction());
        this.xMin = new CustomSpinner("xMin", Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0.01);
        this.xMax = new CustomSpinner("xMax", Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0.01);

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

        this.add(plot2D, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);
    }
    
    @Override
    public void addIndividu(FunctionIndividual ind) throws UnknownFunctionException, UnparsableExpressionException {
        this.plot2D.addIndividu((Function2D)ind.getFunction(), ind.getPoints().get(0));
    }

    @Override
    public void refresh(RefreshEvent ev) {
        if (ev instanceof FunctionRefreshEvent) {
            Function func = (Function)ev.getSource();
            try {
                this.plot2D.setPlot((Function2D)func);
            } catch (UnknownFunctionException ex) {
                Logger.getLogger(Function2DUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnparsableExpressionException ex) {
                Logger.getLogger(Function2DUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (ev instanceof PopulationRefreshEvent) {
            Population pop = (Population)ev.getSource();
            FunctionIndividual funcInd = (FunctionIndividual) pop.getFirstIndividual();
            Function func = funcInd.getFunction();
            try {
                this.plot2D.setPlot((Function2D)func);
            } catch (UnknownFunctionException ex) {
                Logger.getLogger(Function2DUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnparsableExpressionException ex) {
                Logger.getLogger(Function2DUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        if (ev instanceof SpinnerEvent) {
            controller.applyChanges(new DomaineEvent(this, new Points(xMin.getValue().doubleValue(), xMax.getValue().doubleValue())));
        }
        if (ev instanceof CustomTextFieldEvent) {
            CustomTextFieldEvent ctfe = (CustomTextFieldEvent) ev;
            String newFunc = ctfe.getValue();
            controller.applyChanges(new FunctionEvent(this, newFunc));
        }
    }
}
