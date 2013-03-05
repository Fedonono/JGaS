/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.geneticalgorithm.Problems;

import group5.geneticalgorithm.MvcPattern.Model;
import group5.geneticalgorithm.Population.Population;

/**
 *
 * @author simonneau
 */
public abstract class Problem extends Model {

    public enum availableMutationOperators {
    }

    public enum availableCrossOverOperators {
    }

    public enum availableSelectionOperators {
    }

    public enum availableEvaluationOperator {
    }
    private Class selectedMutationOperator;
    private Class selectedCrossOverOperation;
    private Class selectedSelectionOperator;
    private Class selectedEvaluationOperator;

    public abstract Population createInitialPopulation();
}
