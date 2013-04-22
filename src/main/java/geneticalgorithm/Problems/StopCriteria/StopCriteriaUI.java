/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Problems.StopCriteria;

import GraphicalComponents.CustomSpinner;
import GraphicalComponents.IdentifiableComponent;
import GraphicalComponents.ObservationEvent;
import GraphicalComponents.Observer;
import GraphicalComponents.OptionLine;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
import java.awt.FlowLayout;

/**
 *
 * @author simonneau
 */
public class StopCriteriaUI extends IdentifiableComponent implements View, Observer {

    private static String maxStepCountLabel = "steps";
    private static String timeoutLabel = "timeout(ms)";
    private static String evolutionCriterionLabel = "evolution criterion";
    private OptionLine maxStepCount;
    private OptionLine timeout;
    private CustomSpinner evolutionCriterion;
    
    private StopCriteriaController controller;

    public StopCriteriaUI(StopCriteriaController controller) {
        
        this.controller = controller;

        this.maxStepCount = new OptionLine(maxStepCountLabel, 0, Integer.MAX_VALUE, 0);
        this.timeout = new OptionLine(timeoutLabel, 0, Integer.MAX_VALUE, 0);
        this.evolutionCriterion = new CustomSpinner(evolutionCriterionLabel, 0, 1, 0, 0.01);

        this.setLayout(new FlowLayout());
        this.add(this.maxStepCount);
        this.add(this.timeout);
        this.add(this.evolutionCriterion);

        this.maxStepCount.addObserver(this);
        this.timeout.addObserver(this);
        this.evolutionCriterion.addObserver(this);
    }

    @Override
    public void refresh(RefreshEvent ev) {
        
        if(ev instanceof StopCriteriaRefreshEvent){
            
            StopCriteria source = (StopCriteria)ev.getSource();
            this.maxStepCount.setValue(source.getMaxStepCount());
            this.timeout.setValue(source.getTimeout());
            this.evolutionCriterion.setValue(source.getEvolutionCriterion());
        }
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        this.controller.applyChanges(new StopCriteriaUserCtrlEvent(this, this.maxStepCount.getValue(), this.timeout.getValue(), this.evolutionCriterion.getValue().doubleValue()));
    }
}
