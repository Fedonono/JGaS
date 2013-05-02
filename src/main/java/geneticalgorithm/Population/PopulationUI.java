/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population;

import GraphicalComponents.IdentifiableComponent;
import GraphicalComponents.IdentifiableObservableComponent;
import GraphicalComponents.Observable;
import GraphicalComponents.ObservationEvent;
import GraphicalComponents.Observer;
import GraphicalComponents.OptionLine;
import GraphicalComponents.ValidateButton;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
import geneticalgorithm.Population.Individuals.Individual;
import java.awt.FlowLayout;
import java.util.LinkedList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author simonneau
 */
public class PopulationUI extends IdentifiableObservableComponent implements View, Observable, Observer {

    /**
     *
     */
    protected Header header;
    /**
     *
     */
    protected JPanel populationSample;
    /**
     *
     */
    protected PopulationController controller;

    /**
     *
     */
    public PopulationUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.header = new Header(this);
        this.populationSample = new JPanel(new FlowLayout());

        this.add(this.header);
        this.add(populationSample);
    }

    /**
     *
     * @param ev
     */
    @Override
    public void refresh(RefreshEvent ev) {

        if (ev instanceof PopulationRefreshEvent) {
            this.populationRefreshEventTreatment((PopulationRefreshEvent) ev);
            this.notifyObserver(new SpreadRefreshOrderEvent(this, false));


        } else if (ev instanceof ObservableVolumeRefreshEvent) {

            ObservableVolumeRefreshEvent event = (ObservableVolumeRefreshEvent) ev;
            this.header.setValue(event.getValue());
            this.header.setmaxValue(event.getMaxValue());
        }

    }

    /**
     *
     * @param event
     */
    public void populationRefreshEventTreatment(PopulationRefreshEvent event) {
        
        LinkedList<Individual> samples = event.getSample();

        this.populationSample.setLayout(new FlowLayout());
        for (Individual sample : samples) {
            this.populationSample.add(sample.getUI());
        }
    }

    /**
     *
     * @return
     */
    public PopulationController getController() {
        return controller;
    }

    /**
     *
     * @param controller
     */
    public void setController(PopulationController controller) {
        this.controller = controller;
        this.header.setController(controller);
    }

    /**
     *
     * @param ev
     */
    public void notifyObserver(ObservationEvent ev) {
        for (Observer o : this.observers) {
                o.reactToChanges(ev);
            }
    }

    @Override
    public void notifyObservers() {
        this.notifyObserver(new SpreadRefreshOrderEvent(this, true));
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        Observable source = ev.getSource();
        if(source instanceof IdentifiableComponent){
            int id = ((IdentifiableComponent)source).getId();
            
            if(id == this.header.getId()){
                this.notifyObservers();
            }
        }
    }

    /**
     *
     */
    protected class Header extends IdentifiableObservableComponent implements Observer, Observable {

        private PopulationUI boss;
        private PopulationController controller;
        private OptionLine volumeOption;
        private ValidateButton refresh;

        /**
         *
         * @param boss
         */
        public Header(PopulationUI boss) {

            this.boss = boss;

            this.volumeOption = new OptionLine("Sample size", 1, 20, 1);
            this.refresh = new ValidateButton("refresh");

            this.setLayout(new FlowLayout());
            this.add(this.volumeOption);
            this.add(this.refresh);

            this.volumeOption.addObserver(this);
            this.refresh.addObserver(this);
        }

        @Override
        public void reactToChanges(ObservationEvent ev) {

            int id = ((IdentifiableComponent) ev.getSource()).getId();

            if (id == this.refresh.getId()) {
                this.notifyObservers();

            } else if (id == this.volumeOption.getId()) {
                this.controller.applyChanges(new ObservableVolumeUserEvent(boss, this.volumeOption.getValue()));
            }

        }

        /**
         *
         * @param value
         */
        public void setValue(int value) {
            this.volumeOption.setValue(value);
            this.volumeOption.repaint();
        }

        /**
         *
         * @param maxValue
         */
        public void setmaxValue(int maxValue) {
            this.volumeOption.setMaxValue(maxValue);
            this.volumeOption.repaint();
        }

        /**
         *
         * @param controller
         */
        public void setController(PopulationController controller) {
            this.controller = controller;
        }

        @Override
        public void notifyObservers() {
            this.notifyObserver(new SpreadRefreshOrderEvent(this, true));
        }

        /**
         *
         * @param ev
         */
        public void notifyObserver(ObservationEvent ev) {
            for (Observer o : this.observers) {

                o.reactToChanges(ev);
            }
        }
    }
}
