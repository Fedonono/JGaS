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
import geneticalgorithm.Population.Individuals.IndividualUI;
import java.awt.FlowLayout;
import java.util.LinkedList;
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

        if (ev instanceof PopulationRefreshEvent) {
            PopulationRefreshEvent event = (PopulationRefreshEvent) ev;
            LinkedList<IndividualUI> samples = event.getSample();

            for (IndividualUI sample : samples) {

                this.populationSample.add(sample);
            }
        } else if (ev instanceof ObservableVolumeRefreshEvent) {
            ObservableVolumeRefreshEvent event = (ObservableVolumeRefreshEvent) ev;
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
