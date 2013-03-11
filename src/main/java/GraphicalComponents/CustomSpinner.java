/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

import java.util.LinkedList;
import javax.swing.JSpinner;

/**
 *
 * @author simonneau
 */
public class CustomSpinner extends IdentifiableComponent implements Observable, Observer {

    private LinkedList<Observer> observers = new LinkedList<>();
    private Spinner spinner;

    
    public CustomSpinner(){
        this(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public CustomSpinner(int min, int max){
        this(min, max, 0);
    }
    
    public CustomSpinner(int min, int max, int defaultValue) {
        this.spinner = new Spinner(min,max,defaultValue);
        this.spinner.addObserver(this);
    }

    public void setValue(int v) {
        this.spinner.setValue(v);
    }
    
    public int getValue(){
        return (int)this.spinner.getValue();
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObserver() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
    private class Spinner extends JSpinner implements Observable{
        
        
        Observer observer;
        private int max;
        private int min;

        public Spinner(int min, int max, int defaultValue) {
            this.min = min;
            this.max = max;
            
            if (defaultValue <= max && defaultValue >= min) {
                super.setValue(defaultValue);
                
            } else {
                throw new MinMaxValueException(min, max);
            }

        }

        @Override
        public void setValue(Object value) {
            int v = (int) value;
            if (v <= max && v >= min) {
                super.setValue(value);
            } else {
                throw new MinMaxValueException(min, max);
            }
        }

        @Override
        public void addObserver(Observer o) {
            this.observer = o;
        }

        @Override
        public void notifyObserver() {
            this.observer.reactToChanges(null);
        }
    }
}
