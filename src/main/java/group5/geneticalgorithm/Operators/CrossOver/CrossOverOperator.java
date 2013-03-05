/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.geneticalgorithm.Operators.CrossOver;

import group5.geneticalgorithm.Population.Individuals.Individual;

/**
 *
 * @author simonneau
 */
public interface CrossOverOperator {
    public Individual cross(Individual male, Individual female);
}
