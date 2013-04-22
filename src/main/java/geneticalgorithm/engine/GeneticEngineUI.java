/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.engine;

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
import geneticalgorithm.Problems.ProblemUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.LinkedList;
import javax.swing.JLabel;

/**
 *
 * @author simonneau
 */
public class GeneticEngineUI extends IdentifiableComponent implements View, Observer {

    private Header header;
    private Footer footer;
    private Controller controller;
    private ProblemUI problemUI;

    public GeneticEngineUI(GeneticEngine ge, PopulationUI populationUI) {

        this.controller = new GeneticEngineUserCtrl(ge);
        this.problemUI = (ProblemUI) ge.getProblem().getUI();

        this.header = new Header(ge.isPaused());
        this.footer = new Footer();
        this.header.addObserver(this);
        this.footer.addObserver(this);

        this.init(populationUI);
    }

    private void init(PopulationUI populationUI) {

        this.setLayout(new BorderLayout());

        this.add(this.header, BorderLayout.NORTH);
        this.add(populationUI, BorderLayout.CENTER);
        this.add(this.footer, BorderLayout.SOUTH);
    }

    @Override
    public void refresh(RefreshEvent ev) {

        if (ev instanceof EngineRefreshEvent) {

            EngineRefreshEvent event = (EngineRefreshEvent) ev;
            this.footer.setStepCount(event.getStepCount());
            this.footer.setTimeout(event.getTimeout());
            this.footer.setEvolutionCriterion(event.getEvolutionCriterion());

        } else if (ev instanceof EnginePopulationRefreshEvent) {

            PopulationUI populationUI = ((EnginePopulationRefreshEvent) ev).getPopulationUI();
            this.init(populationUI);

        } else if (ev instanceof EngineProblemRefreshEvent) {

            this.problemUI = ((EngineProblemRefreshEvent) ev).getProblemUI();

        }
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {

        Observable s = ev.getSource();
        if (s instanceof IdentifiableComponent) {
            IdentifiableComponent source = (IdentifiableComponent) s;
            int id = source.getId();

            //header
            if (id == this.header.getId()) {

                //pause / resume
                if (ev instanceof PauseEvent) {
                    this.controller.applyChanges(new PauseEngineEvent(this, ((PauseEvent) ev).isPaused()));

                } 
                // step
                else {
                    this.controller.applyChanges(new StepEngineEvent(this));
                }
            }
            //footer => configure
            else if (id == this.footer.getId()) {
                this.problemUI.setVisible(true);
            }
        }
    }

    private class Footer extends IdentifiableComponent implements Observable, Observer {

        private String stepLabel = "current step:";
        private String timeLabel = "time(ms):";
        private String evolutionLabel = "evolution:";
        private long timeout = 0;
        private int stepCount = 0;
        private double evolutionCriterion = 1;
        private JLabel label = new JLabel();
        private ValidateButton configure;
        private LinkedList<Observer> observers = new LinkedList<>();

        public Footer() {

            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            this.refreshLabel();
            this.configure = new ValidateButton("configure");

            this.add(this.label);
            this.add(this.configure);
            this.configure.addObserver(this);
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
            this.notifyObserver();
        }

        public void setStepCount(int stepCount) {
            this.stepCount = stepCount;
            this.refreshLabel();
        }

        public void setButtonVisible(boolean visible) {
            this.configure.setVisible(visible);
        }

        public void setTimeout(long timeout) {
            this.timeout = timeout;
            this.refreshLabel();
        }
        
        public void setEvolutionCriterion(double evolutionCriterion){
            this.evolutionCriterion = evolutionCriterion;
        }

        private void refreshLabel() {
            this.label.setText(this.stepLabel + this.stepCount + " / " + timeLabel + this.timeout + "ms"+" / "+this.evolutionLabel+this.evolutionCriterion*100+"%");
        }
    }

    private class Header extends IdentifiableComponent implements Observable, Observer {

        private PauseStepPanel pauseStep;
        private LinkedList<Observer> observers = new LinkedList<>();

        public Header(boolean pause) {
            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            this.pauseStep = new PauseStepPanel(pause);

            this.add(this.pauseStep);

            this.pauseStep.addObserver(this);
        }

        public void pause() {
            this.pauseStep.setPause(true);
        }

        public void setPause(boolean pause) {
            this.pauseStep.setPause(pause);
        }

        @Override
        public void addObserver(Observer o) {
            this.observers.add(o);
        }

        @Override
        public void notifyObserver() {
            //empty
        }

        public void notifyObserver(ObservationEvent ev) {
            for (Observer o : this.observers) {
                o.reactToChanges(ev);
            }
        }

        @Override
        public void reactToChanges(ObservationEvent ev) {
            int id = ((IdentifiableComponent) ev.getSource()).getId();

            if (id == this.pauseStep.getId()) {
                if (ev instanceof PauseEvent) {
                    this.notifyObserver(new PauseEvent(this, ((PauseEvent) ev).isPaused()));

                } else {
                    this.notifyObserver(new ValidateButtonEvent(this));
                }
            }
        }
    }
}
