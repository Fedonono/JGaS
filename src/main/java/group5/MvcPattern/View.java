/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.geneticalgorithm.MvcPattern;

/**
 *
 * @author simonneau
 */
public abstract class View {
    
    private Controller controller;

    public abstract void refresh(RefreshEvent ev);
}
