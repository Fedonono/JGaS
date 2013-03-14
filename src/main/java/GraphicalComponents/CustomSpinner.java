/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JSpinner;

/**
 *
 * @author simonneau
 */
public class CustomSpinner<E extends Number> extends IdentifiableComponent implements Observable, Observer {

    private LinkedList<Observer> observers = new LinkedList<>();
    private Spinner<E> spinner;

    public CustomSpinner(String label, Number min, Number max) {

        this(label, min, max, min);
    }

    public CustomSpinner(String label, Number min, Number max, Number defaultValue){
        
        this.add(new JLabel(label));
        this.spinner = new Spinner<>((E)min, (E)max, (E)defaultValue);
        this.add(spinner);
        this.spinner.addObserver(this);
    }
    
    

    public void setValue(Number v) {
        this.spinner.setValue((E)v);
    }

    public E getValue() {
        return  (E)this.spinner.getValue();
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

    private class Spinner<E extends Number> extends JSpinner implements Observable {

        Observer observer;
        private E max;
        private E min;

        public Spinner(E min, E max, E defaultValue) {
            this.min = min;
            this.max = max;

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
