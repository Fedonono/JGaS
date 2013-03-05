/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.geneticalgorithm.Population;

import group5.MvcPattern.ControlEvent;
import group5.MvcPattern.View;

/**
 *
 * @author simonneau
 */
public class PopulationControlEvent extends ControlEvent{
    
    int value;
    
    public PopulationControlEvent(View source, int value){
        super(source);
    }

    public int getValue() {
        return value;
    }
    
    
}
