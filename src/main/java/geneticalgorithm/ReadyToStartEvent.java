/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import MvcPattern.RefreshEvent;
import geneticalgorithm.engine.GeneticEngineUI;

/**
 *
 * @author simonneau
 */
public class ReadyToStartEvent extends RefreshEvent {
    
    private GeneticEngineUI engineUI;
    
    public ReadyToStartEvent(GeneticAlgorithm source, GeneticEngineUI engineUI){
        super(source);
        this.engineUI = engineUI;
    }   
    
    public GeneticEngineUI getEngineUI(){
        return this.engineUI;
    }
}
