package geneticalgorithm.Population.Min1D;

import GraphicalComponents.Custom2DPanel;
import Mathematics.Function;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import javax.swing.JFrame;

public class Min1DUI {
	public static void main(String[] args) throws UnknownFunctionException, UnparsableExpressionException {
		// put the PlotPanel in a JFrame like a JPanel
		JFrame frame = new JFrame(Function.getInstance().getLabel());
		frame.setSize(600, 600);
		frame.add(new Custom2DPanel());
		frame.setVisible(true);
 
	}

}