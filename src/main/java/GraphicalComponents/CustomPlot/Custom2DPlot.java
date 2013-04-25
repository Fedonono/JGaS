/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents.CustomPlot;

import Mathematics.Function.Model.Function;
import Mathematics.Function.Model.Function2D;
import Mathematics.Point;
import Tools.DoubleArray;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import org.math.plot.Plot2DPanel;

/**
 *
 * @author nono
 */
public class Custom2DPlot extends CustomPlot {
    private Plot2DPanel plot;

    public Custom2DPlot(Function function) throws UnknownFunctionException, UnparsableExpressionException {
        super();
        plot = new Plot2DPanel();
        this.setPlot(function);
    }
    
    @Override
    public void setPlot(Function function) throws UnknownFunctionException, UnparsableExpressionException {
        this.id = 0;
        plot.removeAllPlots();
        Point domaine = function.getDomaine();
        double xMin, xMax;
        xMin = domaine.get(0);
        xMax = domaine.get(1);
        double[] x = DoubleArray.increment(xMin, 0.1, xMax);

        // define the legend position
        plot.addLegend("SOUTH");
        plot.addLinePlot(function.getLabel(), x, this.getY((Function2D)function, x));

        this.setPreferredSize(new Dimension(600, 600));
        this.add(plot, BorderLayout.CENTER);
    }
    
    /**
     *
     * @param function
     * @param x
     */
    @Override
    public void addIndividual(Function function, Point point) {
        this.id++;
        double x = point.get(0);
        double y = function.getResult(point);
        double xp[] = {x};
        double yp[] = {y};
        int size = getColorSize();
        if (this.id <= size) {
            plot.addScatterPlot("x="+x+", y="+y, colorInd[this.id-1], xp, yp);
        }
        else {
            plot.addScatterPlot("Individu "+this.id, Color.GREEN, xp, yp);
        }
    }

    public double[] getY(Function2D function, double[] x) throws UnknownFunctionException, UnparsableExpressionException {
        double[] y = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            y[i] = function.getY(x[i]);
        }
        return y;
    }
}
