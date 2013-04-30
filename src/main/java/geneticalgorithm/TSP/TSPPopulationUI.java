/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.TSP;

import GraphicalComponents.IdentifiableComponent;
import Util.WorldMap.DestinationPoolUI;
import geneticalgorithm.Population.IndividualRadioButton;
import geneticalgorithm.Population.Individuals.Individual;
import geneticalgorithm.Population.PopulationController;
import geneticalgorithm.Population.PopulationRefreshEvent;
import geneticalgorithm.Population.PopulationUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.LinkedList;

/**
 *
 * @author simonneau
 */
public class TSPPopulationUI extends PopulationUI {
    
    private Footer footer;
    private DestinationPoolUI center;
    
    
    public TSPPopulationUI(TSPPopulation source, PopulationController controller){
        
        this.controller = controller;
        this.header.setController(controller);
        this.footer = new Footer();
        this.center = (DestinationPoolUI)source.getDestinationPool().getUI();
        
        this.add(this.header, BorderLayout.NORTH);
        this.add(this.center, BorderLayout.CENTER);
        this.add(this.footer, BorderLayout.SOUTH);
    }
    
    
    
    
    @Override
    public void populationRefreshEventTreatment(PopulationRefreshEvent event){
        
        LinkedList<Individual> samples = event.getSample();
        this.footer.refresh(samples);
    }
    
    private class Footer extends IdentifiableComponent{
        
        public void refresh(LinkedList<Individual> sample){
            
            this.removeAll();
            this.setLayout(new FlowLayout());
            int rank = 1;
            
            for (Individual individual : sample) {
                
                this.add(new IndividualRadioButton(Integer.toString(rank), individual));
                rank++;
            }
        }
        
    }
}
