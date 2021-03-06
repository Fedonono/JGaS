/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mathematics.Function;

import Mathematics.Point;
import Util.Tools.DoubleArray;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import org.math.plot.Plot3DPanel;

/**
 *
 * @author nono
 */
public class Custom3DPlot extends CustomPlot {
    
    private Plot3DPanel plot;
    
    /**
     *
     * @param function
     * @param indColor
     * @param plotColor
     * @param plotStep
     * @throws UnknownFunctionException
     * @throws UnparsableExpressionException
     */
    public Custom3DPlot(Function function, Color[] indColor, Color plotColor, double plotStep) throws UnknownFunctionException, UnparsableExpressionException {
        super(indColor, plotColor, plotStep);
        plot = new Plot3DPanel();
        this.setPlot(function);
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
        double y = point.get(1);
        double z = function.getResult(point);
        double xp[] = {x};
        double yp[] = {y};
        double zp[] = {z};
        int size = getColorSize();
        if (this.id < size) {
            plot.addScatterPlot("x="+x+", y="+y+", z="+z, indColor[this.id-1], xp, yp, zp);
        }
        else {
            plot.addScatterPlot("Individu "+this.id, indColor[size-1], xp, yp, zp);
        }
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
        double xMin, xMax, yMin, yMax;
        xMin = domaine.get(0);
        xMax = domaine.get(1);
        yMin = domaine.get(2);
        yMax = domaine.get(3);
        double[] x = DoubleArray.increment(xMin, plotStep, xMax);
        double[] y = DoubleArray.increment(yMin, plotStep, yMax);

        // define the legend position
        plot.addLegend("SOUTH");
        plot.addGridPlot(function.getLabel(), plotColor, x, y, f(function,x,y));

        this.setPreferredSize(new Dimension(600, 600));
        this.add(plot, BorderLayout.CENTER);
    }

    // grid version of the function
    /**
     *
     * @param function
     * @param x
     * @param y
     * @return
     * @throws UnknownFunctionException
     * @throws UnparsableExpressionException
     */
    public double[][] f(Function function, double[] x, double[] y) throws UnknownFunctionException, UnparsableExpressionException {
        double[][] z = new double[y.length][x.length];
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < y.length; j++) {
                z[j][i] = function.getResult(new Point(x[i], y[j]));
            }
        }
        return z;
    }
}
