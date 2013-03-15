/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

import java.awt.Dimension;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author simonneau
 */
//TODO ==> use JSpinner NumberEditor to allow floating spinner
public class CustomSpinner extends IdentifiableComponent implements Observable, Observer {

    private LinkedList<Observer> observers = new LinkedList<>();
    private Spinner spinner;

    public CustomSpinner(String label, Number min, Number max) {
        this(label, min, max, min, 1);
    }

    public CustomSpinner(String label, Number min, Number max, Number defaultValue, Number step) {
        this.add(new JLabel(label));
        this.spinner = new Spinner(min, max, defaultValue, step);
        this.spinner.setPreferredSize(new Dimension(50, spinner.getPreferredSize().height));
        this.add(spinner);
        this.spinner.addObserver(this);
    }

    public void setValue(Number v) {
        this.spinner.setValue(v);
    }

    public Number getValue() {
        return (Number)this.spinner.getValue();
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObserver() {
        for (Observer o : observers) {
            o.reactToChanges(new SpinnerEvent(spinner, this.getValue()));
        }
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        this.notifyObserver();
    }

    private class Spinner extends JSpinner implements Observable {

        Observer observer;
        private Number  max;
        private Number min;

        public Spinner(Number min, Number max, Number defaultValue, Number step) {
            this.min = min;
            this.max = max;
            this.setModel(new SpinnerNumberModel(defaultValue.doubleValue(), min.doubleValue(), max.doubleValue(), step.doubleValue()));
            if (defaultValue.doubleValue() <= max.doubleValue() && defaultValue.doubleValue() >= min.doubleValue()) {
                super.setValue(defaultValue);

            } else {
                throw new MinMaxValueException(min, max);
            }



        }

        @Override
        public void setValue(Object value) {
            if (value instanceof Number) {
                Number v = (Number) value;
                if (v.doubleValue() <= max.doubleValue() && v.doubleValue() >= min.doubleValue()) {
                    super.setValue(value);
                }
            } else {
                throw new NumberFormatException();
            }
        }

        @Override
        public void addObserver(Observer o) {
            this.observer = o;
        }

        @Override
        public void notifyObserver() {
            this.observer.reactToChanges(new ObservationEvent(this));
        }
    }
}
