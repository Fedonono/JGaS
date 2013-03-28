/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

import Mathematics.Function;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author nono
 */
public class Custom2DPanel extends IdentifiableComponent implements Observable {
    private Custom2DPlot plot2D;
    private CustomTextField functionChange;
    private CustomSpinner xMin;
    private CustomSpinner xMax;

    public Custom2DPanel() throws UnknownFunctionException, UnparsableExpressionException {
        super(new BorderLayout());

        this.plot2D = new Custom2DPlot();
        this.functionChange = new CustomTextField(Function.getInstance().getLabel());
        this.xMin = new CustomSpinner("xMin", 0, 1, 0, 0.01);
        this.xMax = new CustomSpinner("xMax", 0, 1, 0, 0.01);
        
        this.add(plot2D, BorderLayout.CENTER);
        
        JPanel footer = new JPanel(new GridLayout(2,1));
        
        JPanel xPanel = new JPanel();
        JPanel functionPanel = new JPanel();
        
        xPanel.add(xMin);
        xPanel.add(xMax);
        
        functionPanel.add(new JLabel("f(x) = "));
        functionPanel.add(functionChange);

        footer.add(functionPanel);
        footer.add(xPanel);
        
        this.add(footer, BorderLayout.SOUTH);
    }

    @Override
    public void addObserver(Observer o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void notifyObserver() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
