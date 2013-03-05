/*
 * Minesweeper Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package genericGraphicalComponents;

import java.awt.FlowLayout;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author nono
 */
public class CustomOptionLine extends JPanel implements Observer, Observable {

    private LinkedList<Observer> observers = new LinkedList<>();
    
    private int maxValue;
    private int minValue;
    private int value;
    private CustomSlider slider;
    private CustomTextField textField;
    private String label;

    public CustomOptionLine(String text, int min, int max, int value) {

        if (min > max) {
            throw new MinMaxValueException(max, min);
        }
        this.maxValue = max;
        this.minValue = min;
        this.value = value;
        this.label = text;

        this.setLayout(new FlowLayout());

        this.add(new JLabel(text), FlowLayout.LEFT);

        this.slider = new CustomSlider(min, max, value);
        
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        this.add(slider, FlowLayout.CENTER);
        slider.addObserver(this);
        
        this.textField = new CustomTextField(Integer.toString(value));
        this.add(textField, FlowLayout.RIGHT);
        textField.addObserver(this);
    }

    public void setValue(int value) {
        if (value < this.minValue) {
            this.value = minValue;
        } else if (value > maxValue) {
            this.value = maxValue;
        } else {
            this.value = value;
        }
        this.slider.reactToChanges(new CustomSliderEvent(slider, this.value));
        this.textField.reactToChanges(new CustomTextFieldEvent(textField, Integer.toString(this.value)));
    }

    public void setMaxValue(int maxV) {
        this.maxValue = maxV;
        this.slider.setMaximum(maxV);
    }

    public void setMinValue(int minV) {
        this.minValue = minV;
        this.slider.setMinimum(minV);
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        Class evClass = ev.getClass();
        int value = this.value;
        if (evClass == CustomSliderEvent.class) {
            
            CustomSliderEvent event = (CustomSliderEvent) ev;
            value = event.getValue();

        } else if (evClass == CustomTextFieldEvent.class) {
            
            CustomTextFieldEvent event = (CustomTextFieldEvent) ev;
            try {
                value = Integer.valueOf(event.getValue());
            } catch (RuntimeException e) {}
        }
        this.setValue(value);
        this.notifyObserver();
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObserver() {
        
        for(Observer o : observers){
            o.reactToChanges(new OptionLineEvent(this, this.value));
        }
        
    }
    
    public String getLabel(){
        return this.label;
    }
}
