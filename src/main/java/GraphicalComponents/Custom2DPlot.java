/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

import Mathematics.Function;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import static org.math.array.DoubleArray.increment;
import org.math.plot.Plot2DPanel;

/**
 *
 * @author nono
 */
public class Custom2DPlot extends IdentifiableComponent implements Observer  {
    
    Plot2DPanel plot;

    public Custom2DPlot() throws UnknownFunctionException, UnparsableExpressionException {
        super(new BorderLayout());
        // define your data - TODO by ARNAUD - à définir à l'exterieur via les lsiteners des textfield.
        double[] x = increment(0.0, 0.1, 1.0); // x = 0.0:0.1:1.0
        // end define extorioris !
        
        plot = new Plot2DPanel();
        // define the legend position
        plot.addLegend("SOUTH");
        plot.addLinePlot(Function.getInstance().getLabel(), x, getY(x));
        plot.addScatterPlot("individu", x, getY(x));
        
        this.setPreferredSize(new Dimension(600,600));
        this.add(plot, BorderLayout.CENTER);
    }

    public static double[] getY(double[] x) throws UnknownFunctionException, UnparsableExpressionException {
        double[] y = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            y[i] = Function.getInstance().getY(x[i]);
        }
        return y;
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
