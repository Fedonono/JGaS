/*
 * Genetic Algorithm Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package GraphicalComponents;

import java.awt.FlowLayout;
import java.util.LinkedList;
import javax.swing.JLabel;

/**
 *
 * @author nono
 */
public class OptionLine extends IdentifiableComponent implements Observer, Observable {

    private LinkedList<Observer> observers = new LinkedList<>();
    private int maxValue;
    private int minValue;
    private int value;
    private CustomSlider slider;
    private CustomTextField textField;
    private String label;

    public OptionLine(String text, int min, int max, int value) {

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
        this.setValue(value, true);
    }

    private void setValue(int value, boolean notify) {
        if (value < this.minValue) {
            this.value = minValue;
        } else if (value > maxValue) {
            this.value = maxValue;
        } else {
            this.value = value;
        }
        if (notify) {
            this.textField.reactToChanges(new CustomTextFieldEvent(textField, Integer.toString(value)));
            this.slider.reactToChanges(new CustomSliderEvent(slider, value));
        }
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

        int value = this.value;
        int id = ((IdentifiableComponent) ev.getSource()).getId();

        if (id == this.slider.getId()) {

            CustomSliderEvent event = (CustomSliderEvent) ev;
            value = event.getValue();

            this.setValue(value,false);
            this.textField.reactToChanges(new CustomTextFieldEvent(textField, Integer.toString(value)));

        } else if (id == this.textField.getId()) {

            CustomTextFieldEvent event = (CustomTextFieldEvent) ev;
            try {
                value = Integer.valueOf(event.getValue());
            } catch (RuntimeException e) {
            }
            
            this.setValue(value,false);
            this.slider.reactToChanges(new CustomSliderEvent(slider, value));

        }
        this.notifyObserver();
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObserver() {

        for (Observer o : observers) {
            o.reactToChanges(new OptionLineEvent(this, this.value));
        }

    }

    public String getLabel() {
        return this.label;
    }

    public int getValue() {
        return this.value;
    }
}
