/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalComponents;

/**
 *
 * @author simonneau
 */
public class PauseEvent extends ValidateButtonEvent {

    private boolean isPaused;

    public PauseEvent(Observable source, boolean isPaused) {
        super(source);
        this.isPaused = isPaused;
    }

    public boolean isPaused() {
        return this.isPaused;
    }
}
