/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population.Function;

import GraphicalComponents.CustomPlot.Custom3DPlot;
import GraphicalComponents.CustomSpinner;
import GraphicalComponents.ObservationEvent;
import GraphicalComponents.SpinnerEvent;
import Mathematics.Function.Model.Function;
import Mathematics.Point;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import geneticalgorithm.Population.PopulationController;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author nono
 */
public class PopulationFunction3DUI extends PopulationFunction2DUI {
    protected CustomSpinner yMin;
    protected CustomSpinner yMax;

    public PopulationFunction3DUI(String strFunc, PopulationController controller) throws UnknownFunctionException, UnparsableExpressionException {
        super(strFunc, controller);
    }

    @Override
    public void setFooter(JPanel footer) {
        footer.setLayout(new GridLayout(3, 1));
        JPanel functionPanel = new JPanel();
        functionPanel.add(new JLabel("f(x,y) = "));
        functionPanel.add(functionChange);
        footer.add(functionPanel);
    }
    
    @Override
    public void createPlot(Function func) throws UnknownFunctionException, UnparsableExpressionException {
        this.plot = new Custom3DPlot(func);
    }

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
    
    @Override
    public void domaineFooter(JPanel footer, Point domaine) {
        super.domaineFooter(footer, domaine);
        this.yMin = new CustomSpinner("yMin", Integer.MIN_VALUE, Integer.MAX_VALUE, domaine.get(2), 0.1);
        this.yMax = new CustomSpinner("yMax", Integer.MIN_VALUE, Integer.MAX_VALUE, domaine.get(3), 0.1);
        this.yMin.addObserver(this);
        this.yMax.addObserver(this);
        JPanel yPanel = new JPanel();
        yPanel.add(yMin);
        yPanel.add(yMax);
        footer.add(yPanel);
    }
}
