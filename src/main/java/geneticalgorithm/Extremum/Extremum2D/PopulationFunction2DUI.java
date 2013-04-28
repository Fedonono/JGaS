/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Extremum.Extremum2D;

import geneticalgorithm.Extremum.Extremum1D.PopulationFunction1DUI;
import geneticalgorithm.Extremum.DomaineEvent;
import Mathematics.Function.Custom3DPlot;
import GraphicalComponents.CustomSpinner;
import GraphicalComponents.ObservationEvent;
import GraphicalComponents.SpinnerEvent;
import Mathematics.Function.Function;
import Mathematics.Point;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Population.PopulationController;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author nono
 */
public class PopulationFunction2DUI extends PopulationFunction1DUI {
    /**
     *
     */
    protected CustomSpinner yMin;
    /**
     *
     */
    protected CustomSpinner yMax;

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
    public PopulationFunction2DUI(String strFunc, PopulationController controller, Color[] indColor, Color plotColor, double plotStep) throws UnknownFunctionException, UnparsableExpressionException {
        super(strFunc, controller, indColor, plotColor, plotStep);
    }

    /**
     *
     * @param footer
     */
    @Override
    public void setFooter(JPanel footer) {
        footer.setLayout(new GridLayout(3, 1));
        JPanel functionPanel = new JPanel();
        functionPanel.add(new JLabel("f(x,y) = "));
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
        this.plot = new Custom3DPlot(func, indColor, plotColor, plotStep);
    }

    /**
     *
     * @param domaine
     */
    @Override
    public void changeDomaineValue(Point domaine) {
        super.changeDomaineValue(domaine);
        this.yMin.setValue(domaine.get(2));
        this.yMax.setValue(domaine.get(3));
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        reactEvent(ev);
        if (ev instanceof SpinnerEvent) {
            controller.applyChanges(new DomaineEvent(this, new Point(xMin.getValue().doubleValue(), xMax.getValue().doubleValue(), yMin.getValue().doubleValue(), yMax.getValue().doubleValue())));
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
        super.domaineFooter(footer, domaine, plotStep);
        this.yMin = new CustomSpinner("yMin", Integer.MIN_VALUE, Integer.MAX_VALUE, domaine.get(2), plotStep);
        this.yMax = new CustomSpinner("yMax", Integer.MIN_VALUE, Integer.MAX_VALUE, domaine.get(3), plotStep);
        this.yMin.addObserver(this);
        this.yMax.addObserver(this);
        JPanel yPanel = new JPanel();
        yPanel.add(yMin);
        yPanel.add(yMax);
        footer.add(yPanel);
    }
}
