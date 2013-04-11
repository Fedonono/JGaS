/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems;

import MvcPattern.Controller;
import MvcPattern.UserEvent;

/**
 *
 * @author simonneau
 */
public class ProblemController implements Controller {

    private Problem problem;

    public ProblemController(Problem problem){
        this.problem = problem;
    }
    
    @Override
    public void applyChanges(UserEvent event) {
        if (event instanceof ProblemUserEvent) {
            
            ProblemUI source = (ProblemUI) event.getSource();

            problem.setCrossProbability(source.getCrossProbability());
            problem.setMaxStepCount(source.getMaxStepCount());
            problem.setTimeout(source.getTimeout());
            problem.setMutationProbability(source.getMutationProbability());
            problem.setPopulationSize(source.getPopulationSize());
            problem.setSelectedCrossOverOperation(source.getSelectedCrossOverOperator());
            problem.setSelectedEvaluationOperator(source.getSelectedEvaluationOperator());
            problem.setSelectedMutationOperator(source.getSelectedMutationOperator());
            problem.setSelectedSelectionOperator(source.getSelectedSelectionOperator());
        }
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }
    
    
}
