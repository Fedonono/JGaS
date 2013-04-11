/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import MvcPattern.UserEvent;
import geneticalgorithm.Problems.Problem;

/**
 *
 * @author simonneau
 */
public class GAContextEvent extends UserEvent{
    
    Problem selectedProblem;
    
    public GAContextEvent(MainUI source, Problem problem){
        super(source);
        this.selectedProblem = problem;
    }
    
    public Problem getSelectedProblem(){
        return this.selectedProblem;
    }
}
