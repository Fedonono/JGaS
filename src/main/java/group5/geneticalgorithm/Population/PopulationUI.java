/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.geneticalgorithm.Population;

import genericGraphicalComponents.ObservationEvent;
import genericGraphicalComponents.Observer;
import genericGraphicalComponents.OptionLine;
import genericGraphicalComponents.OptionLineEvent;
import group5.MvcPattern.RefreshEvent;
import group5.MvcPattern.View;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author simonneau
 */
public class PopulationUI extends JPanel implements View, Observer {

    private OptionLine optionLine;
    private JPanel populationSample;
    private PopulationController controller;

    public PopulationUI(int size) {
        this.optionLine = new OptionLine("Sample size", 1, size, 1);
        this.populationSample = new JPanel(new FlowLayout());
        this.optionLine.addObserver(this);

        this.add(optionLine);
        this.add(populationSample);
    }

    @Override
    public void refresh(RefreshEvent ev) {
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        
        if (ev instanceof OptionLineEvent) {
            OptionLineEvent event = (OptionLineEvent) ev;
            this.controller.applyChanges(new PopulationControlEvent(this, event.getValue()));
        }
    }
}
