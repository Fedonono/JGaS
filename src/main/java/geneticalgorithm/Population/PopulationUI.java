/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population;

import GraphicalComponents.IdentifiableComponent;
import GraphicalComponents.Observable;
import GraphicalComponents.ObservationEvent;
import GraphicalComponents.Observer;
import GraphicalComponents.OptionLine;
import GraphicalComponents.ValidateButton;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
import com.sun.java.swing.plaf.windows.WindowsBorders;
import geneticalgorithm.Population.Individuals.Individual;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.LinkedList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.metal.MetalBorders;
import org.jdesktop.swingx.border.DropShadowBorder;

/**
 *
 * @author simonneau
 */
public class PopulationUI extends IdentifiableComponent implements View, Observable {

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
        System.out.println("toto");
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

    @Override
    public void addObserver(Observer o) {
        this.header.addObserver(o);
    }

    /**
     *
     * @param ev
     */
    public void notifyObserver(ObservationEvent ev) {
        this.header.notifyObserver(ev);
    }

    @Override
    public void notifyObservers() {
        this.header.notifyObservers();
    }

    protected class Header extends IdentifiableComponent implements Observer, Observable {

        private PopulationUI boss;
        private PopulationController controller;
        private OptionLine volumeOption;
        private ValidateButton refresh;
        private LinkedList<Observer> observers = new LinkedList<>();

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

        public void setValue(int value) {
            this.volumeOption.setValue(value);
            this.volumeOption.repaint();
        }
        
        public void setmaxValue(int maxValue){
            this.volumeOption.setMaxValue(maxValue);
            this.volumeOption.repaint();
        }

        public void setController(PopulationController controller) {
            this.controller = controller;
        }

        @Override
        public void addObserver(Observer o) {
            this.observers.add(o);
        }

        @Override
        public void notifyObservers() {
            this.notifyObserver(new SpreadRefreshOrderEvent(boss, true));
        }

        public void notifyObserver(ObservationEvent ev) {
            for (Observer o : this.observers) {

                o.reactToChanges(ev);
            }
        }
    }
}
