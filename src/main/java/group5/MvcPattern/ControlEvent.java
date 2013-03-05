/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.geneticalgorithm.MvcPattern;

/**
 *
 * @author simonneau
 */
public class ControlEvent {

    private View source;
    
    public ControlEvent(View source){
        this.source = source;
    }

    public View getSource(){
        return this.source;
    }
}
