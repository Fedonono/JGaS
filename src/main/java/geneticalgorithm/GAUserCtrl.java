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
public class GAUserCtrl implements Controller {

    GeneticAlgorithm ag;
    
    /**
     * 
     * @param model
     */
    public GAUserCtrl(GeneticAlgorithm model){
        this.ag = model;
    }
    
    /**
     * set the controller target with ag.
     * @param ag
     */
    public void set(GeneticAlgorithm ag){
        this.ag = ag;
    }
    
    @Override
    public void applyChanges(UserEvent event) {
        
        if(event instanceof GAContextEvent){
            
            GAContextEvent ev = (GAContextEvent)event;
            this.ag.setSelectedProblem(ev.getSelectedProblem());
        }
    }
    
}
