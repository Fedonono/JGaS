/*
 * Minesweeper Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package GraphicalComponents;

import java.util.LinkedList;
import javax.swing.JSlider;

/**
 *
 * @author simonneau
 */
public class CustomSlider extends IdentifiableComponent implements Observable, Observer {

    private LinkedList<Observer> observers = new LinkedList<>();
    
    private JSlider slider;

    public CustomSlider(int min, int max, int value) {
        slider = new JSlider(min, max, value);
    }
    
    public void setValue(int value) {
        this.setValue(value, true);
    }

    private void setValue(int value, boolean haveToNotify) {

        this.slider.setValue(value);
        if(haveToNotify){
            this.notifyObserver();
        }

    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObserver() {
        for(Observer o : this.observers){
            o.reactToChanges(new CustomSliderEvent(this, this.slider.getValue()));
        }
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        CustomSliderEvent event = (CustomSliderEvent)ev;
        this.setValue(event.getValue(), false);
    }
    
    public void setMinimum(int minimum){
        this.slider.setMinimum(minimum);
    }
    
    public void setPaintTicks(boolean bool){
        this.setPaintTicks(bool);
    }
    
    public void setPaintLabels(boolean bool){
        this.setPaintLabels(bool);
    }
    
    public void setMaximum(int maximum){
        this.setMaximum(maximum);
    }
}
