/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents.CustomPlot;

import GraphicalComponents.IdentifiableComponent;
import Mathematics.Function.Model.Function2D;
import Mathematics.Point;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.math.array.DoubleArray.*;
import org.math.plot.Plot2DPanel;

/**
 *
 * @author nono
 */
public class Custom2DPlot extends IdentifiableComponent implements View {
    private int id = 0;
    private Plot2DPanel plot;
    private Plot2DPanel individuPlot;

    public Custom2DPlot(Function2D function) throws UnknownFunctionException, UnparsableExpressionException {
        this.setLayout(new BorderLayout());
        plot = new Plot2DPanel();
        individuPlot = new Plot2DPanel();
        plot.setOpaque(false);
        individuPlot.setOpaque(false);
        this.setPlot(function);
    }
    
    public final void setPlot(Function2D function) throws UnknownFunctionException, UnparsableExpressionException {
        this.id = 0;
        plot.removeAllPlots();
        Point domaine = function.getDomaine();
        double[] x = increment(domaine.get(0), 0.1, domaine.get(1)+0.1); // x = 0.0:0.1:1.0

        // define the legend position
        plot.addLegend("SOUTH");
        plot.addLinePlot(function.getLabel(), x, this.getY(function, x));

        this.setPreferredSize(new Dimension(600, 600));
        this.add(plot, BorderLayout.CENTER);
    }
    
    /**
     *
     * @param function
     * @param x
     */
    public void addIndividu(Function2D function, double x) {
        this.id++;
        double xp[] = {x};
        double yp[] = {function.getY(x)};
        if (this.id == 1) {
            plot.addScatterPlot("Best - Individu "+this.id, Color.RED, xp, yp);
        }
        else {
            plot.addScatterPlot("Individu "+this.id, Color.BLUE, xp, yp);
        }
    }

    public double[] getY(Function2D function, double[] x) throws UnknownFunctionException, UnparsableExpressionException {
        double[] y = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            y[i] = function.getY(x[i]);
        }
        return y;
    }

    @Override
    public void refresh(RefreshEvent ev) {
        Function2D func2D = (Function2D)ev.getSource();
        try {
            setPlot(func2D);
        } catch (UnknownFunctionException | UnparsableExpressionException ex) {
            Logger.getLogger(Custom2DPlot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
