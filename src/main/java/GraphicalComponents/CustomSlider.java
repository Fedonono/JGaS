/*
 * Genetic Algorithm Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package GraphicalComponents;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author simonneau
 */
public class CustomSlider extends IdentifiableObservableComponent implements Observer, ChangeListener {

    private JSlider slider;
    private boolean notifyingDisabled = false;

    /**
     *
     * @param min
     * @param max
     * @param value
     */
    public CustomSlider(int min, int max, int value) {
        slider = new JSlider(min, max, value);
        this.add(slider);
        this.slider.addChangeListener(this);


    }

    /**
     * Set 'this' current value
     * @param value
     */
    public void setValue(int value) {
        this.slider.setValue(value);
    }

    @Override
    public void notifyObservers() {
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

    /**
     * set the maximum value
     * @param minimum
     */
    public void setMinimum(int minimum) {
        this.slider.setMinimum(minimum);
    }

    /**
     * draws ticks on the slider
     * @param bool
     */
    public void setPaintTicks(boolean bool) {
        this.slider.setPaintTicks(bool);
    }

    /**
     * set 'this' label.
     * @param bool
     */
    public void setPaintLabels(boolean bool) {
        this.slider.setPaintLabels(bool);
    }

    /**
     * set the maximum value.
     * @param maximum
     */
    public void setMaximum(int maximum) {
        this.slider.setMaximum(maximum);
    }

    /**
     *
     * @param ce
     */
    @Override
    public void stateChanged(ChangeEvent ce) {
        this.notifyObservers();
    }
}
