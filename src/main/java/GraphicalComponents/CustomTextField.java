/*
 * Genetic Algorithm Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package GraphicalComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 *
 * @author simonneau
 */
public class CustomTextField extends IdentifiableObservableComponent implements Observer, ActionListener {

    /**
     *
     */
    protected JTextField textField;
    private boolean notifyingDisabled = false;

    /**
     *
     * @param text
     */
    public CustomTextField(String text) {
        this(text, 8);
    }

    /**
     *
     * @param text
     * @param columns
     */
    public CustomTextField(String text, int columns) {
        this.textField = new JTextField(text, columns);
        this.add(textField);
        this.textField.addActionListener(this);
    }

    @Override
    public void notifyObservers() {
        if (!this.notifyingDisabled) {
            for (Observer o : this.observers) {
                o.reactToChanges(new CustomTextFieldEvent(this, this.textField.getText()));
            }
        }
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        CustomTextFieldEvent event = (CustomTextFieldEvent) ev;
        this.notifyingDisabled = true;
        this.textField.setText(event.getValue());
        this.notifyingDisabled = false;
    }

    /**
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        this.notifyObservers();
    }
    
    /**
     *
     * @return
     */
    public String getText(){
        return this.textField.getText();
    }
}
