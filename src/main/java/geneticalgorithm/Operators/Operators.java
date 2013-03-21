/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators;

import geneticalgorithm.Operators.CrossOver.CrossOverOperator;
import geneticalgorithm.Operators.Evaluation.EvaluationOperator;
import geneticalgorithm.Operators.Mutation.MutationOperator;
import geneticalgorithm.Operators.Selection.SelectionOperator;

/**
 *
 * @author simonneau
 */
public class Operators {
    
    private CrossOverOperator crossoverOperator;
    private EvaluationOperator evaluationOperator;
    private MutationOperator mutationOperator;
    private SelectionOperator selectionOperator;
    

    public CrossOverOperator getCrossoverOperator() {
        return crossoverOperator;
    }

    public EvaluationOperator getEvaluationOperator() {
        return evaluationOperator;
    }

    public MutationOperator getMutationOperator() {
        return mutationOperator;
    }

    public SelectionOperator getSelectionOperator() {
        return selectionOperator;
    }

    public void setCrossoverOperator(CrossOverOperator crossoverOperator) {
        this.crossoverOperator = crossoverOperator;
    }

    public void setEvaluationOperator(EvaluationOperator evaluationOperator) {
        this.evaluationOperator = evaluationOperator;
    }

    public void setMutationOperator(MutationOperator mutationOperator) {
        this.mutationOperator = mutationOperator;
    }

    public void setSelectionOperator(SelectionOperator selectionOperator) {
        this.selectionOperator = selectionOperator;
    }
    
    
    
    
}
