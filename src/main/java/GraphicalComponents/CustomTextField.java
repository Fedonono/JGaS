/*
 * Minesweeper Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package GraphicalComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JTextField;

/**
 *
 * @author simonneau
 */
public class CustomTextField extends IdentifiableComponent implements Observable, Observer, ActionListener {

    private LinkedList<Observer> observers = new LinkedList<>();
    
    private JTextField textField;

    public CustomTextField(String text) {
        this.textField = new JTextField(text, 3);
        this.textField.addActionListener(this);
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObserver() {

        for (Observer o : this.observers) {
            o.reactToChanges(new CustomTextFieldEvent(this, this.textField.getText()));
        }
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        CustomTextFieldEvent event = (CustomTextFieldEvent) ev;
        this.textField.setText(event.getValue());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.notifyObserver();
    }

   
}
