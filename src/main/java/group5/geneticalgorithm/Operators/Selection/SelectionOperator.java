/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.geneticalgorithm.Operators.Selection;

import group5.geneticalgorithm.Population.Population;

/**
 *
 * @author simonneau
 */
public interface SelectionOperator {
    public Population buildNextGeneration(Population population);
}
