/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mathematics.Function;

import Mathematics.Point;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author nono
 */
public abstract class CustomPlot extends JPanel implements View {
    /**
     *
     */
    protected int id = 0;
    /**
     *
     */
    protected Color plotColor;
    /**
     *
     */
    protected Color[] indColor;
    /**
     *
     */
    protected double plotStep;
    
    /**
     *
     * @param indColor
     * @param plotColor
     * @param plotStep
     */
    protected CustomPlot(Color[] indColor, Color plotColor, double plotStep) {
        this.setLayout(new BorderLayout());
        this.plotColor = plotColor;
        this.indColor = indColor;
        this.plotStep = plotStep;
    }

    /**
     *
     * @param function
     * @param point
     */
    public abstract void addIndividual(Function function, Point point);
    /**
     *
     * @param function
     * @throws UnknownFunctionException
     * @throws UnparsableExpressionException
     */
    public abstract void setPlot(Function function) throws UnknownFunctionException, UnparsableExpressionException;
    /**
     *
     * @param ev
     */
    @Override
    public void refresh(RefreshEvent ev) {
        Function func = (Function)ev.getSource();
        try {
            setPlot(func);
        } catch (UnknownFunctionException | UnparsableExpressionException ex) {
            Logger.getLogger(CustomPlot.class.getName()).log(Level.SEVERE, "Incorrect input function.", ex);
        }
    }

    /**
     *
     * @return
     */
    public int getColorSize() {
        return indColor.length;
    }
}
