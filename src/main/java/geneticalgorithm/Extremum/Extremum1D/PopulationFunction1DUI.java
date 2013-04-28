/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Extremum.Extremum1D;

import geneticalgorithm.Extremum.DomaineEvent;
import geneticalgorithm.Extremum.PopulationFunctionUI;
import geneticalgorithm.Extremum.PopulationFunction;
import geneticalgorithm.Extremum.FunctionRefreshEvent;
import Mathematics.Function.Custom2DPlot;
import GraphicalComponents.CustomSpinner;
import GraphicalComponents.ObservationEvent;
import GraphicalComponents.SpinnerEvent;
import Mathematics.Function.Function;
import Mathematics.Point;
import MvcPattern.RefreshEvent;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Population.PopulationController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author nono
 */
public class PopulationFunction1DUI extends PopulationFunctionUI {
    /**
     *
     */
    protected CustomSpinner xMin;
    /**
     *
     */
    protected CustomSpinner xMax;
    
    /**
     *
     * @param strFunc
     * @param controller
     * @param indColor
     * @param plotColor
     * @param plotStep
     * @throws UnknownFunctionException
     * @throws UnparsableExpressionException
     */
    public PopulationFunction1DUI(String strFunc, PopulationController controller, Color[] indColor, Color plotColor, double plotStep) throws UnknownFunctionException, UnparsableExpressionException {
        super(strFunc, controller);
        JPanel panel = new JPanel(new BorderLayout());
        JPanel footer = new JPanel();
        PopulationFunction popC = (PopulationFunction) controller.getModel();
        Function func = (Function) popC.getFunction();
        Point domaine = func.getDomaine();
        
        createPlot(func, indColor, plotColor, plotStep);
        setFooter(footer);
        domaineFooter(footer, domaine, plotStep);

        panel.add(plot, BorderLayout.CENTER);
        panel.add(footer, BorderLayout.SOUTH);

        this.add(panel);
    }
    
    /**
     *
     * @param footer
     */
    @Override
    public void setFooter(JPanel footer) {
        footer.setLayout(new GridLayout(2, 1));
        JPanel functionPanel = new JPanel();
        functionPanel.add(new JLabel("f(x) = "));
        functionPanel.add(functionChange);
        footer.add(functionPanel);
    }
    
    /**
     *
     * @param func
     * @param indColor
     * @param plotColor
     * @param plotStep
     * @throws UnknownFunctionException
     * @throws UnparsableExpressionException
     */
    @Override
    public void createPlot(Function func, Color[] indColor, Color plotColor, double plotStep) throws UnknownFunctionException, UnparsableExpressionException {
        this.plot = new Custom2DPlot(func, indColor, plotColor, plotStep);
    }

    /**
     *
     * @param ev
     */
    @Override
    public void refresh(RefreshEvent ev) {
        super.refresh(ev);
        
        if (ev instanceof FunctionRefreshEvent) {
            Function func = (Function) ev.getSource();
            Point domaine = func.getDomaine();
            changeDomaineValue(domaine);
            try {
                this.plot.setPlot(func);
            } catch (UnknownFunctionException | UnparsableExpressionException ex) {
                Logger.getLogger(PopulationFunction1DUI.class.getName()).log(Level.SEVERE,  "Incorrect input function.", ex);
            }
        }
    }
    
    /**
     *
     * @param domaine
     */
    @Override
    public void changeDomaineValue(Point domaine) {
        this.xMin.setValue(domaine.get(0));
        this.xMax.setValue(domaine.get(1));
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        reactEvent(ev);
        if (ev instanceof SpinnerEvent) {
            controller.applyChanges(new DomaineEvent(this, new Point(xMin.getValue().doubleValue(), xMax.getValue().doubleValue())));
        }
    }

    /**
     *
     * @param footer
     * @param domaine
     * @param plotStep
     */
    @Override
    public void domaineFooter(JPanel footer, Point domaine, double plotStep) {
        this.xMin = new CustomSpinner("xMin", Integer.MIN_VALUE, Integer.MAX_VALUE, domaine.get(0), plotStep);
        this.xMax = new CustomSpinner("xMax", Integer.MIN_VALUE, Integer.MAX_VALUE, domaine.get(1), plotStep);
        this.xMin.addObserver(this);
        this.xMax.addObserver(this);
        JPanel xPanel = new JPanel();
        xPanel.add(xMin);
        xPanel.add(xMax);
        footer.add(xPanel);
    }
}
