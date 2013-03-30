import Mathematics.Function.Model.Function;
import Mathematics.Points;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import java.awt.Color;
import static java.lang.Math.*;
import javax.swing.*;
import org.math.plot.*;
import static org.math.array.DoubleArray.*;
 
public class GridPlotsExample {
	public static void main(String[] args) throws UnknownFunctionException, UnparsableExpressionException {
 
		// define your data
                // x increment(popMin, popMax-popMin/nbrePop, popMax)
                // y pareil
                double[] test = increment(0.0, 0.5, 1.0);
		double[] x = increment(0.0, 0.1, 1.0); // x = 0.0:0.1:1.0
		double[] y = increment(0.0, 0.05, 1.0);// y = 0.0:0.05:1.0
		double[][] z1 = f1(x, y);
                double[] t = {0.0};
                double[][] z = {{0.5}};
 
		// create your PlotPanel (you can use it as a JPanel) with a legend at SOUTH
		Plot3DPanel plot = new Plot3DPanel("SOUTH");
 
		// add grid plot to the PlotPanel
		plot.addGridPlot("z=cos(PI*x)*sin(PI*y)", x, y, z1);
                //Plot2DPanel p2D = new Plot2DPanel();
                //p2D.
                //plot.addPlot();//"point 1","point 1l", test);
                plot.addGridPlot("z=0.5", test, test, f2(x,y));//
 
		// put the PlotPanel in a JFrame like a JPanel
		JFrame frame = new JFrame("a plot panel");
		frame.setSize(600, 600);
		frame.setContentPane(plot);
		frame.setVisible(true);
 
	}
 
	// function definition: z=cos(PI*x)*sin(PI*y)
	public static double f1(double x, double y) throws UnknownFunctionException, UnparsableExpressionException {
	//	double z = Function.getInstance().getResult(new Points(x, y));
	//	return z;
            return 1.2;
	}
 
	// grid version of the function
	public static double[][] f1(double[] x, double[] y) throws UnknownFunctionException, UnparsableExpressionException {
		double[][] z = new double[y.length][x.length];
		for (int i = 0; i < x.length; i++)
			for (int j = 0; j < y.length; j++)
				z[j][i] = f1(x[i], y[j]);
		return z;
	}
        
        
	public static double[][] f2(double[] x, double[] y) throws UnknownFunctionException, UnparsableExpressionException {
		double[][] z = new double[y.length][x.length];
		for (int i = 0; i < x.length; i++)
			for (int j = 0; j < y.length; j++)
				z[j][i] = 0.5;
		return z;
	}
}