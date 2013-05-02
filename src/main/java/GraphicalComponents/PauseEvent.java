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

    /**
     * Fired by a PauseButton.
     * @param source
     * @param isPaused
     */
    public PauseEvent(Observable source, boolean isPaused) {
        super(source);
        this.isPaused = isPaused;
    }

    /**
     *
     * @return the source state.
     */
    public boolean isPaused() {
        return this.isPaused;
    }
}
