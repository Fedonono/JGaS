/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mathematics.Function;

import Mathematics.Function.Function;
import Mathematics.Function.Function2D;
import Mathematics.Point;
import Util.Tools.DoubleArray;
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

    /**
     *
     * @param function
     * @param indColor
     * @param plotColor
     * @param plotStep
     * @throws UnknownFunctionException
     * @throws UnparsableExpressionException
     */
    public Custom2DPlot(Function function, Color[] indColor, Color plotColor, double plotStep) throws UnknownFunctionException, UnparsableExpressionException {
        super(indColor, plotColor, plotStep);
        plot = new Plot2DPanel();
        this.setPlot(function);
    }
    
    /**
     *
     * @param function
     * @throws UnknownFunctionException
     * @throws UnparsableExpressionException
     */
    @Override
    public void setPlot(Function function) throws UnknownFunctionException, UnparsableExpressionException {
        this.id = 0;
        plot.removeAllPlots();
        Point domaine = function.getDomaine();
        double xMin, xMax;
        xMin = domaine.get(0);
        xMax = domaine.get(1);
        double[] x = DoubleArray.increment(xMin, plotStep, xMax);

        // define the legend position
        plot.addLegend("SOUTH");
        plot.addLinePlot(function.getLabel(), plotColor, x, this.getY((Function2D)function, x));

        this.setPreferredSize(new Dimension(600, 600));
        this.add(plot, BorderLayout.CENTER);
    }
    
    /**
     * 
     * @param function
     * @param point 
     */
    @Override
    public void addIndividual(Function function, Point point) {
        this.id++;
        double x = point.get(0);
        double y = function.getResult(point);
        double xp[] = {x};
        double yp[] = {y};
        int size = getColorSize();
        if (this.id < size) {
            plot.addScatterPlot("x="+x+", y="+y, indColor[this.id-1], xp, yp);
        }
        else {
            plot.addScatterPlot("Individu "+this.id, indColor[size-1], xp, yp);
        }
    }

    /**
     *
     * @param function
     * @param x
     * @return
     * @throws UnknownFunctionException
     * @throws UnparsableExpressionException
     */
    public double[] getY(Function2D function, double[] x) throws UnknownFunctionException, UnparsableExpressionException {
        double[] y = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            y[i] = function.getY(x[i]);
        }
        return y;
    }
}
