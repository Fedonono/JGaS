/*
 * Minesweeper Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package genericGraphicalComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JCheckBox;

/**
 *
 * @author simonneau
 */
public class CustomCheckBox extends JCheckBox implements Observable, ActionListener{
    
    private LinkedList<Observer> observers = new LinkedList<>();
    
    public CustomCheckBox(String text, boolean checked) {
        super(text, checked);
        this.addActionListener(this);
    }

    
    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObserver() {
        for(Observer o : observers){
            o.reactToChanges(new CheckBoxEvent(this, this.isSelected()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.notifyObserver();
    }
    
}

