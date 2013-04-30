/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import GraphicalComponents.IdentifiableComponent;
import GraphicalComponents.Observable;
import GraphicalComponents.ObservationEvent;
import GraphicalComponents.Observer;
import GraphicalComponents.RepaintEvent;
import GraphicalComponents.SelectMenu;
import GraphicalComponents.ValidateButton;
import MvcPattern.Controller;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
import geneticalgorithm.Problem.Problem;
import geneticalgorithm.engine.GeneticEngineUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author simonneau
 */
public class GeneticAlgorithmUI extends IdentifiableComponent implements View, Observer, Observable {

    private Controller controller;
    private GeneticEngineUI geUI;
    private Header header;
    private LinkedList<Observer> observers = new LinkedList<>();

    /**
     *
     * @param ga
     * @param controller
     * @param geUI
     */
    public GeneticAlgorithmUI(GeneticAlgorithm ga, GAUserCtrl controller, GeneticEngineUI geUI) {
        this.controller = controller;
        this.setLayout(new BorderLayout());

        this.header = new Header();
        this.header.addItems(ga.getProblems());
        this.header.setSelectedItem(ga.getSelectedProblem());
        
        this.geUI = geUI;
        this.geUI.addObserver(this);

        this.add(this.header, BorderLayout.NORTH);
        this.add(this.geUI, BorderLayout.CENTER);
        this.header.addObserver(this);
    }
    
    private void reset(){
        this.removeAll();
        this.add(this.header, BorderLayout.NORTH);
        this.add(geUI, BorderLayout.CENTER);
    }

    /**
     *
     * @param controller
     */
    public void setController(GAUserCtrl controller) {
        this.controller = controller;
    }

    @Override
    public void refresh(RefreshEvent ev) {
        if (ev instanceof ReadyToStartEvent) {
            this.geUI = ((ReadyToStartEvent) ev).getEngineUI();
            this.reset();
            this.notifyObservers();
        }
    }

    private void notifyController(NewContextEvent ev) {
        this.controller.applyChanges(new GAContextEvent(this, ev.getSelectedProblem()));
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        
        if (ev instanceof NewContextEvent) {        

            this.notifyController((NewContextEvent) ev);
        }
        else if(ev instanceof RepaintEvent){
            this.repaint();
            this.notifyObservers();
        }
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for(Observer o : this.observers){
            o.reactToChanges(new RepaintEvent(this));
        }
    }

    
    private class Header extends IdentifiableComponent implements Observable, Observer {

        private SelectMenu<Problem> selectMenu;
        private ValidateButton newContext;
        private LinkedList<Observer> observers = new LinkedList<>();

        public Header() {
            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            this.selectMenu = new SelectMenu<>("available problems:");
            this.newContext = new ValidateButton("OK");

            this.add(this.selectMenu);
            this.add(this.newContext);
            this.newContext.addObserver(this);
        }

        public void addItem(Problem item) {
            this.selectMenu.addItem(item);
        }

        public void addItems(Collection<Problem> items) {
            for (Problem item : items) {
                this.addItem(item);
            }
        }

        @Override
        public void addObserver(Observer o) {
            this.observers.add(o);
        }

        @Override
        public void notifyObservers() {
            //empty
        }

        private void notifyObserver(ObservationEvent ev) {
            for (Observer o : this.observers) {
                o.reactToChanges(ev);
            }
        }
        
        public void setSelectedItem(Problem p){
            this.selectMenu.setSelectedItem(p);
        }

        @Override
        public void reactToChanges(ObservationEvent ev) {
            int id = ((IdentifiableComponent) ev.getSource()).getId();

            if (id == this.newContext.getId()) {
                this.notifyObserver(new NewContextEvent(this, this.selectMenu.getSelectedItem()));

            }
        }
    }

    private class NewContextEvent extends ObservationEvent {

        Problem selectedProblem;

        public NewContextEvent(Header source, Problem selectedProblem) {
            super(source);
            this.selectedProblem = selectedProblem;
        }

        public Problem getSelectedProblem() {
            return this.selectedProblem;
        }
    }
}
