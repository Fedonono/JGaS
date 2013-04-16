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
import geneticalgorithm.Problems.ProblemUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author simonneau
 */
public class GeneticEngineUI extends IdentifiableComponent implements View, Observer {

    private Header header;
    private Footer footer;
    private Controller controller;
    private ProblemUI problem;

    public GeneticEngineUI(GeneticEngine ge, PopulationUI populationUI) {

        this.controller = new GeneticEngineUserCtrl(ge);
        this.problem = (ProblemUI) ge.getProblem().getUI();

        this.setLayout(new BorderLayout());
        this.header = new Header();
        this.footer = new Footer();

        this.add(this.header, BorderLayout.NORTH);
        
        /*JFrame frame = new JFrame();
        frame.setBounds(0, 0, 1280, 800);
        frame.add(this);
        frame.setVisible(true);*/
        this.add(populationUI, BorderLayout.CENTER);
        this.add(this.footer, BorderLayout.SOUTH);

        this.header.addObserver(this);
        this.footer.addObserver(this);
    }

    @Override
    public void refresh(RefreshEvent ev) {
        
        if(ev instanceof EngineRefreshEvent){
            
            EngineRefreshEvent event = (EngineRefreshEvent)ev;
            this.footer.setStepCount(event.getStepCount());
            this.footer.setTimeout(event.getTimeout());
        }
        else if(ev instanceof EngineStopedRefreshEvent){
            this.header.pause();
        }
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {

        Observable s = ev.getSource();
        if (s instanceof IdentifiableComponent) {
            IdentifiableComponent source = (IdentifiableComponent) s;
            int id = source.getId();

            if (id == this.header.getId()) {

                if (ev instanceof PauseEvent) {
                    this.controller.applyChanges(new PauseEngineEvent(this, ((PauseEvent) ev).isPaused()));
                    
                } else if(ev instanceof StopEvent){
                    this.controller.applyChanges(new StopEngineEvent(this));
                    this.header.setVisible(false);
                    this.footer.setButtonVisible(false);
                    
                    
                }else{
                    this.controller.applyChanges(new StepEngineEvent(this));
                }

            } else if (id == this.footer.getId()) {
                this.problem.setVisible(true);
            }
        }
    }

    private class Footer extends IdentifiableComponent implements Observable, Observer {

        private String stepLabel = "current step:";
        private String timeLabel = "time(ms):";
        private long timeout = 0;
        private int stepCount = 0;
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
        
        public void setButtonVisible(boolean visible){
            this.configure.setVisible(visible);
        }

        public void setTimeout(long timeout) {
            this.timeout = timeout;
            this.refreshLabel();
        }
        
        private void refreshLabel(){
            this.label.setText(this.stepLabel + this.stepCount + " " + timeLabel + this.timeout + "ms");
        }
        
        
    }

    //TODO
    private class Header extends IdentifiableComponent implements Observable, Observer {

        private PauseStepPanel pauseStep;
        private ValidateButton stop;
        private LinkedList<Observer> observers = new LinkedList<>();

        public Header() {
            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            this.pauseStep = new PauseStepPanel(true);
            this.stop = new ValidateButton("stop");

            this.add(this.pauseStep);
            this.add(this.stop, FlowLayout.LEFT);

            this.pauseStep.addObserver(this);
            this.stop.addObserver(this);
        }
        
        public void pause(){
            this.pauseStep.setPause(true);
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
            //TODO
        }

        @Override
        public void reactToChanges(ObservationEvent ev) {
            int id = ((IdentifiableComponent) ev.getSource()).getId();

            if (id == this.stop.getId()) {
                this.notifyObserver(new StopEvent(this));
                
            } else if (id == this.pauseStep.getId()) {
                if (ev instanceof PauseEvent) {
                    this.notifyObserver(new PauseEvent(this, ((PauseEvent) ev).isPaused()));
                    
                } else {
                    this.notifyObserver(new ValidateButtonEvent(this));
                }
            }
        }
    }

    private class StopEvent extends ObservationEvent {

        public StopEvent(Observable source) {
            super(source);
        }
    }
}
