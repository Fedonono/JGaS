/*
 * Genetic Algorithm Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package GraphicalComponents;

import java.util.LinkedList;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author simonneau
 */
public class CustomSlider extends IdentifiableComponent implements Observable, Observer, ChangeListener {

    private LinkedList<Observer> observers = new LinkedList<>();
    private JSlider slider;
    private boolean notifyingDisabled = false;

    public CustomSlider(int min, int max, int value) {
        slider = new JSlider(min, max, value);
        this.add(slider);
        this.slider.addChangeListener(this);


    }

    public void setValue(int value) {
        this.slider.setValue(value);
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObserver() {
        if (!this.notifyingDisabled) {
            for (Observer o : this.observers) {
                o.reactToChanges(new CustomSliderEvent(this, this.slider.getValue()));
            }
        }
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        CustomSliderEvent event = (CustomSliderEvent) ev;
        this.notifyingDisabled = true;
        this.setValue(event.getValue());
        this.notifyingDisabled = false;
    }

    public void setMinimum(int minimum) {
        this.slider.setMinimum(minimum);
    }

    public void setPaintTicks(boolean bool) {
        this.slider.setPaintTicks(bool);
    }

    public void setPaintLabels(boolean bool) {
        this.slider.setPaintLabels(bool);
    }

    public void setMaximum(int maximum) {
        this.slider.setMaximum(maximum);
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        this.notifyObserver();
    }
}
