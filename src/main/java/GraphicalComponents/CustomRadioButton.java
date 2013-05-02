/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author simonneau
 */
public class CustomRadioButton extends IdentifiableObservableComponent implements ChangeListener{

    private String label;
    private JRadioButton radioButton;
    
    
    /**
     *
     * @param label
     */
    public CustomRadioButton(String label){
        this(label, false);
    }
    
    /**
     *
     * @param label
     * @param selected
     */
    public CustomRadioButton(String label, boolean selected){
        this.label = label;
        this.radioButton = new JRadioButton();
        this.radioButton.setSelected(selected);
        
        this.setLayout(new FlowLayout());
        this.add(new JLabel(label));
        this.add(this.radioButton);
        
        
        this.radioButton.addChangeListener(this);
    }
    
    @Override
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.reactToChanges(new RadioButtonEvent(this, this.radioButton.isSelected()));
        }
    }

    /**
     *
     * @param ce
     */
    @Override
    public void stateChanged(ChangeEvent ce) {
        this.notifyObservers();
    }

    /**
     *
     * @param selected
     */
    public void setSelected(boolean selected){
        this.radioButton.setSelected(selected);
    }
    
    /**
     *
     * @return
     */
    public boolean isSelected(){
        return this.radioButton.isSelected();
    }
    
}
