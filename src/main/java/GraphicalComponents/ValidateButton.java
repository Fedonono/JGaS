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
public class ValidateButton extends IdentifiableComponent implements Observable, ActionListener{
    
    private LinkedList<Observer> observers = new LinkedList<>();
    private JButton button;
    
    
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

    /**this
     *
     */
    @Override
    public void notifyObserver() {
        for(Observer o : observers){
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
