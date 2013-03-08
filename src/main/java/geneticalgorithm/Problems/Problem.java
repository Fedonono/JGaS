/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems;

import MvcPattern.Model;
import geneticalgorithm.Operators.CrossOver.CrossOverOperator;
import geneticalgorithm.Operators.Evaluation.EvaluationOperator;
import geneticalgorithm.Operators.Mutation.MutationOperator;
import geneticalgorithm.Operators.Selection.SelectionOperator;
import geneticalgorithm.Population.Population;
import java.util.LinkedList;

/**
 *
 * @author simonneau
 */
public abstract class Problem extends Model {

    private LinkedList<MutationOperator> availableMutationOperators;
    
    private LinkedList<CrossOverOperator> availableCrossOverOperators;

    private LinkedList<SelectionOperator> availableSelectionOperators;

    private LinkedList<EvaluationOperator> availableEvaluationOperator;
    
    
    private MutationOperator selectedMutationOperator;
    private CrossOverOperator selectedCrossOverOperation;
    private SelectionOperator selectedSelectionOperator;
    private EvaluationOperator selectedEvaluationOperator;

    public abstract Population createInitialPopulation();
}
