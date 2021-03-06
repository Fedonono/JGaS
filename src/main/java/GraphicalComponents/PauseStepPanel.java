/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

import java.util.LinkedList;

/**
 *
 * @author simonneau
 */
public class PauseStepPanel extends IdentifiableObservableComponent implements Observer {

    private PauseButton pauseButton;
    private ValidateButton stepButton;

    /**
     *
     */
    public PauseStepPanel() {
        this(false);
    }

    /**
     *
     * @param isPaused
     */
    public PauseStepPanel(boolean isPaused) {

        this.observers = new LinkedList<>();
        
        this.pauseButton = new PauseButton(isPaused);
        this.stepButton = new ValidateButton("step");
        this.stepButton.setVisible(isPaused);

        this.add(pauseButton);
        this.add(stepButton);

        this.pauseButton.addObserver(this);
        this.stepButton.addObserver(this);

    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        IdentifiableComponent source = (IdentifiableComponent) (ev.getSource());
        int id = source.getId();

        if (id == this.pauseButton.getId()) {

            PauseEvent pev = (PauseEvent) ev;
            boolean paused = pev.isPaused();
            
            this.stepButton.setVisible(paused);
            this.notifyObserver(new PauseEvent(this, paused));

        } else if (id == this.stepButton.getId()) {

            this.notifyObserver(new ValidateButtonEvent(this));
        }
    }

    @Override
    public void notifyObservers() {
        //empty ==> have to be more specific
    }

    private void notifyObserver(ValidateButtonEvent ev) {
        for (Observer o : observers) {
            o.reactToChanges(ev);
        }
    }
    
    /**
     *
     * @param pause
     */
    public void setPause(boolean pause){
        this.pauseButton.setState(pause);
        this.stepButton.setVisible(pause);     
    }
}
