/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.Population;

/**
 *
 * @author simonneau
 */
public class GeneticEngine {

    private Population population;
    private boolean stop = true;
    private int stepCount = 0;
    private int maxStepCount;

    public void start() {

        this.stop = false;
        this.engine();
    }

    public void stop() {

        this.stop = true;
    }

    private void engine() {

        while (!this.stop && this.stepCount <= maxStepCount) {
            
            this.stepCount++;
            this.population.crossOverStep();
            this.population.mutationStep();
            this.population.buildNextGeneration();


        }
    }

    public Individual getBestSolution() {

        return this.population.bestIndividual();
    }
}
