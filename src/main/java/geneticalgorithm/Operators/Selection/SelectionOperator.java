/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Operators.Selection;

import geneticalgorithm.Population.Population;

/**
 *
 * @author simonneau
 */
public interface SelectionOperator {
    public Population buildNextGeneration(Population population);
}
