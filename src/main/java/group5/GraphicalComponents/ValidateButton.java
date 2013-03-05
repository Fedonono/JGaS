/*
 * Minesweeper Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package genericGraphicalComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;

/**
 *
 * @author simonneau
 */
public class ValidateButton extends JButton implements Observable, ActionListener{
    
    LinkedList<Observer> observers = new LinkedList<>();
    
    /**
     *
     * @param label
     */
    public ValidateButton(String label) {
        super(label);
        this.addActionListener(this);
    }

    /**
     *
     * @param o
     */
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    /**
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
