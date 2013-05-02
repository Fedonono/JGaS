/*
 * Genetic Algorithm Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package GraphicalComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author simonneau
 */
public class ValidateButton extends IdentifiableObservableComponent implements ActionListener {

    /**
     *
     */
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
     * set 'this' label.
     * @param label
     */
    public void setText(String label) {
        this.button.setText(label);
    }

    /**
     * this
     *
     */
    @Override
    public void notifyObservers() {
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
        this.notifyObservers();
    }
}
