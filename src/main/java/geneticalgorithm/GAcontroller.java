/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import MvcPattern.Controller;
import MvcPattern.UserEvent;

/**
 *
 * @author simonneau
 */
public class GAcontroller implements Controller {

    GeneticAlgorithm ag;
    
    public GAcontroller(GeneticAlgorithm model){
        this.ag = model;
    }
    
    public void set(GeneticAlgorithm ag){
        this.ag = ag;
    }
    
    @Override
    public void applyChanges(UserEvent event) {
        //TODO
    }
    
}
