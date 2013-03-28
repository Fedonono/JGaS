/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.Selection;

import geneticalgorithm.Operators.Operator;
import geneticalgorithm.Population.Population;

/**
 *
 * @author simonneau
 */
public abstract class SelectionOperator extends Operator {

    public abstract Population buildNextGeneration(Population population, int size);
}
