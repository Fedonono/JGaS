/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MvcPattern;

/**
 *
 * @author simonneau
 */
public interface Controller {

    /**
     *
     * @param event
     */
    public void applyChanges(UserEvent event);
}
