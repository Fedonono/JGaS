/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population;

import GraphicalComponents.ObservationEvent;
import GraphicalComponents.Observer;
import GraphicalComponents.OptionLine;
import GraphicalComponents.OptionLineEvent;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author simonneau
 */
public class PopulationUI extends JPanel implements View, Observer {

    protected OptionLine volumeOption;
    protected JPanel populationSample;
    protected PopulationController controller;

    public PopulationUI(int sizeView, int popSize) {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.volumeOption = new OptionLine("Sample size", 1, popSize, sizeView);
        this.populationSample = new JPanel(new FlowLayout());
        this.volumeOption.addObserver(this);

        this.add(volumeOption);
        this.add(populationSample);
    }

    @Override
    public void refresh(RefreshEvent ev) {

        /*if (ev instanceof PopulationRefreshEvent) {
            PopulationRefreshEvent event = (PopulationRefreshEvent) ev;
            LinkedList<Individual> samples = event.getSample();

            for (Individual sample : samples) {
                sample.notifyViews();
                //this.populationSample.add(sample.getUI()); POUR TSP TODO BY ARNAUD DELETE ?
            }
        } else */
        if (ev instanceof ObservableVolumeRefreshEvent) {
            ObservableVolumeRefreshEvent event = (ObservableVolumeRefreshEvent) ev;
            this.volumeOption.setMaxValue(event.getMaxValue());
            this.volumeOption.setValue(event.getValue());
        }

    }

    public PopulationController getController() {
        return controller;
    }

    public void setController(PopulationController controller) {
        this.controller = controller;
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        
        if (ev instanceof OptionLineEvent) {

            OptionLineEvent event = (OptionLineEvent) ev;
            this.controller.applyChanges(new ObservableVolumeUserEvent(this, event.getValue()));
        }
    }
}
