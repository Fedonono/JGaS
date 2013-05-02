/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.TSP;

import Util.WorldMap.DestinationPoolUI;
import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.PopulationController;
import geneticalgorithm.Population.PopulationRefreshEvent;
import geneticalgorithm.Population.PopulationUI;
import java.awt.BorderLayout;
import java.util.LinkedList;

/**
 *
 * @author simonneau
 */
public class TSPPopulationUI extends PopulationUI {
    private DestinationPoolUI center;
    
    
    /**
     *
     * @param source
     * @param controller
     */
    public TSPPopulationUI(TSPPopulation source, PopulationController controller){
        
        this.controller = controller;
        this.header.setController(controller);
        this.header.setVolumeVisible(false);
        this.center = (DestinationPoolUI)source.getDestinationPool().getUI();
        
        this.removeAll();
        this.add(this.header);
        this.add(this.center, BorderLayout.CENTER);
        
    }
    
    @Override
    public void populationRefreshEventTreatment(PopulationRefreshEvent event){
        
        LinkedList<Individual> samples = event.getSample();
        samples.peekFirst().notifyViews();
    }
}
