/*
 * Genetic Algorithm Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package GraphicalComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;

/**
 *
 * @author simonneau
 */
public class ValidateButton extends IdentifiableComponent implements Observable, ActionListener {

    protected LinkedList<Observer> observers = new LinkedList<>();
    protected JButton button;

    /**
     *
     * @param label
     */
    public ValidateButton(String label) {
        this.button = new JButton(label);
        this.add(button);
        this.button.addActionListener(this);
    }

    /**
     *
     * @param o
     */
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void setText(String label) {
        this.button.setText(label);
    }

    /**
     * this
     *
     */
    @Override
    public void notifyObserver() {
        for (Observer o : observers) {
            o.reactToChanges(new ValidateButtonEvent(this));
        }
    }

    /**
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        this.notifyObserver();
    }
}
