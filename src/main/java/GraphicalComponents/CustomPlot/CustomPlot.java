/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents.CustomPlot;

import GraphicalComponents.IdentifiableComponent;
import Mathematics.Function.Model.Function;
import MvcPattern.View;
import org.math.plot.PlotPanel;

/**
 *
 * @author nono
 */
public abstract class CustomPlot extends IdentifiableComponent implements View {    
    public abstract void setPlot(Function function);
}
