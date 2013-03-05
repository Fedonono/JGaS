/*
 * Minesweeper Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package genericGraphicalComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JTextField;

/**
 *
 * @author simonneau
 */
public class CustomTextField extends JTextField implements Observable, Observer, ActionListener {

    private LinkedList<Observer> observers = new LinkedList<>();

    public CustomTextField(String text) {
        super(text, 3);
        this.addActionListener(this);
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObserver() {

        for (Observer o : this.observers) {
            o.reactToChanges(new CustomTextFieldEvent(this, this.getText()));
        }
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        CustomTextFieldEvent event = (CustomTextFieldEvent) ev;
        this.setText(event.getValue());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.notifyObserver();
    }

   
}
