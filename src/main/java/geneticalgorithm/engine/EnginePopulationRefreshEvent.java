/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.engine;

import MvcPattern.RefreshEvent;
import geneticalgorithm.Population.PopulationUI;

/**
 *
 * @author simonneau
 */
public class EnginePopulationRefreshEvent extends RefreshEvent {
    
    private PopulationUI populationUI;
    
    public EnginePopulationRefreshEvent(GeneticEngine source, PopulationUI populationUI){
        super(source);
        this.populationUI = populationUI;
    }

    public PopulationUI getPopulationUI() {
        return populationUI;
    }
    
}
