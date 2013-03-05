/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.geneticalgorithm.Population;

import group5.geneticalgorithm.MvcPattern.Model;
import group5.geneticalgorithm.Operators.Selection.SelectionOperator;
import group5.geneticalgorithm.Population.Individuals.Individual;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author simonneau
 */
public class Population extends Model {

    private ArrayList<Individual> individuals;
    private SelectionOperator selectionOperator;

    public Population(SelectionOperator sop) {
        this.selectionOperator = sop;
        this.individuals = new ArrayList<>();
    }

    /**
     * Adds an individual to the 'this' Population.
     * @param s 
     */
    public void add(Individual s) {
        this.individuals.add(s);
        
    }

    /**
     * Evaluate all the individuals using their evaluation method.
     */
    private void evaluationStep() {
        for (Individual individual : individuals) {
            individual.evaluate();
        }
    }

    
    /**
     * Selects the survivals of the current generation using the selected selection operator.
     */
    public void buildNextGeneration() {
        this.evaluationStep();
        Population population = this.selectionOperator.buildNextGeneration(this);
        this.setSolutions(population);
    }

    protected void setSolutions(Population population) {
        this.individuals = new ArrayList<>(population.individuals.size());
        this.individuals.addAll(population.individuals);
    }

    
    /**
     * Randomly crosses Individuals between them using the selected cross over operator.
     */
    public void crossOverStep() {
        LinkedList<Individual> crossQueue = new LinkedList<>();
        for (Individual individual : individuals) {
            if (Math.random() < individual.getCrossOverProbability()) {
                crossQueue.add(individual);
            }
        }

        int queueSize = crossQueue.size();
        Individual male;
        Individual female = null;
        int nbCandidates;
        double sexAppeal;

        while (queueSize > 1) {

            male = crossQueue.remove(0);
            queueSize--;

            nbCandidates = queueSize;
            boolean done = false;

            while (nbCandidates > 0 && !done) {
                Iterator<Individual> solutionIterator = crossQueue.iterator();
                female = solutionIterator.next();
                sexAppeal = 1 / nbCandidates;

                if (Math.random() < sexAppeal) {
                    solutionIterator.remove();
                    queueSize--;
                    done = true;

                } else {
                    nbCandidates--;
                }
            }
            male.cross(female);
        }
    }

    
    /**
     * Randomly makes some individual victim of mutations using the selected mutation operator.
     */
    public void mutationStep() {
        for (Individual individual : individuals) {
            if (Math.random() < individual.getMutationProbability()) {
                individual.mutate();
            }
        }
    }
    
    
    @Override
    public void notifyViews(){
        //TODO
    }
}
