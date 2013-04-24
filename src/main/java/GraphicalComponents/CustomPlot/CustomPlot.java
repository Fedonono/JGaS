/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents.CustomPlot;

import GraphicalComponents.IdentifiableComponent;
import Mathematics.Function.Model.Function;
import Mathematics.Point;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nono
 */
public abstract class CustomPlot extends IdentifiableComponent implements View {
    protected int id = 0;
    
    protected CustomPlot() {
        this.setLayout(new BorderLayout());
    }

    public abstract void addIndividu(Function function, Point point);
    public abstract void setPlot(Function function) throws UnknownFunctionException, UnparsableExpressionException;
    @Override
    public void refresh(RefreshEvent ev) {
        Function func = (Function)ev.getSource();
        try {
            setPlot(func);
        } catch (UnknownFunctionException | UnparsableExpressionException ex) {
            Logger.getLogger(Custom2DPlot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
