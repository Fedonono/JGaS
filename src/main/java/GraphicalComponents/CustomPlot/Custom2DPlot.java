/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents.CustomPlot;

import GraphicalComponents.IdentifiableComponent;
import Mathematics.Function.Model.Function2D;
import Mathematics.Points;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLayeredPane;
import javax.swing.OverlayLayout;
import org.math.plot.Plot2DPanel;
import static org.math.array.DoubleArray.*;
import org.math.plot.plots.ScatterPlot;

/**
 *
 * @author nono
 */
public class Custom2DPlot extends IdentifiableComponent implements View {
    Plot2DPanel plot;
    Plot2DPanel individuPlot;

    public Custom2DPlot(Function2D function) throws UnknownFunctionException, UnparsableExpressionException {
        this.setLayout(new BorderLayout());
        plot = new Plot2DPanel();
        individuPlot = new Plot2DPanel();
        plot.setOpaque(false);
        individuPlot.setOpaque(false);
        this.setPlot(function);
    }
    
    public void setPlot(Function2D function) throws UnknownFunctionException, UnparsableExpressionException {
        plot.removeAllPlots();
        Points domaine = function.getDomaine();
        double[] x = increment(domaine.get(0), 0.1, domaine.get(1)); // x = 0.0:0.1:1.0

        // define the legend position
        plot.addLegend("SOUTH");
        plot.addLinePlot(function.getLabel(), x, this.getY(function, x));

        this.setPreferredSize(new Dimension(600, 600));
        this.add(plot, BorderLayout.CENTER);
    }
    
    public void addIndividu(Function2D function, double x) throws UnknownFunctionException, UnparsableExpressionException {
        double xp[] = {x};
        double yp[] = {function.getY(x)};
        plot.addScatterPlot("individu", xp, yp);
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
        } catch (UnknownFunctionException ex) {
            Logger.getLogger(Custom2DPlot.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnparsableExpressionException ex) {
            Logger.getLogger(Custom2DPlot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
