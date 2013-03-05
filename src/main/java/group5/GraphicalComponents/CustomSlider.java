/*
 * Minesweeper Project
 * by Group3 : Arnaud BABOL, Guillaume SIMMONEAU
 */
package genericGraphicalComponents;

import java.util.LinkedList;
import javax.swing.JSlider;

/**
 *
 * @author simonneau
 */
public class CustomSlider extends JSlider implements Observable, Observer {

    private LinkedList<Observer> observers = new LinkedList<>();

    public CustomSlider(int min, int max, int value) {
        super(min, max, value);
    }

    @Override
    public void setValue(int value) {
        this.setValue(value, true);
    }

    private void setValue(int value, boolean haveToNotify) {

        super.setValue(value);
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
            o.reactToChanges(new CustomSliderEvent(this, this.getValue()));
        }
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        CustomSliderEvent event = (CustomSliderEvent)ev;
        this.setValue(event.getValue(), false);
    }
}
