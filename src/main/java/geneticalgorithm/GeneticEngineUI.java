/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import GraphicalComponents.IdentifiableComponent;
import GraphicalComponents.Observable;
import GraphicalComponents.ObservationEvent;
import GraphicalComponents.Observer;
import GraphicalComponents.PauseEvent;
import GraphicalComponents.PauseStepPanel;
import GraphicalComponents.ValidateButton;
import GraphicalComponents.ValidateButtonEvent;
import MvcPattern.Controller;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
import geneticalgorithm.Population.PopulationUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.LinkedList;
import javax.swing.JLabel;

/**
 *
 * @author simonneau
 */
public class GeneticEngineUI extends IdentifiableComponent implements View, Observer {

    private PauseStepPanel header;
    private Footer footer;
    private Controller controller;

    public GeneticEngineUI(GeneticEngine ge, PopulationUI pUI) {

        this.controller = new GeneticEngineUserCtrl(ge);

        this.setLayout(new BorderLayout());
        this.header = new PauseStepPanel(false);
        this.footer = new Footer();

        this.add(this.header, BorderLayout.NORTH);
        this.add(pUI, BorderLayout.CENTER);
        this.add(this.footer, BorderLayout.SOUTH);

        this.header.addObserver(this);
        this.footer.addObserver(this);
    }

    @Override
    public void refresh(RefreshEvent ev) {
        // recuperer le step courant et le mettre à jour.
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {

        Observable s = ev.getSource();
        if (s instanceof IdentifiableComponent) {
            IdentifiableComponent source = (IdentifiableComponent) s;
            int id = source.getId();

            if (id == this.header.getId()) {

                if (ev instanceof PauseEvent) {
                    this.controller.applyChanges(new PauseEngineEvent(this, ((PauseEvent)ev).isPaused()));
                } else {
                    this.controller.applyChanges(new StepEngineEvent(this));
                }

            } else if (id == this.footer.getId()) {
                this.header.setVisible(false);
                this.controller.applyChanges(new StopEngineEvent(this));
            }
        }
    }

    private class Footer extends IdentifiableComponent implements Observable, Observer {

        private String stepLabel = "current step:";
        private JLabel label;
        private ValidateButton stop;
        private LinkedList<Observer> observers = new LinkedList<>();

        public Footer() {

            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            this.label.setText(this.stepLabel + 0);
            this.stop = new ValidateButton("stop");
            this.add(this.label);
            this.add(this.stop);
            this.stop.addObserver(this);

        }

        @Override
        public void addObserver(Observer o) {
            this.observers.add(o);
        }

        @Override
        public void notifyObserver() {
            for (Observer o : this.observers) {
                o.reactToChanges(new ValidateButtonEvent(this));
            }
        }

        @Override
        public void reactToChanges(ObservationEvent ev) {
            this.stop.setVisible(false);
            this.notifyObserver();
        }

        public void setStepCount(int stepCount) {
            this.label.setText(stepLabel + stepCount);
        }
    }
}
