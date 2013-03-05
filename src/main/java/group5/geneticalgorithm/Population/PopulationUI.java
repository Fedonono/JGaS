/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package group5.geneticalgorithm.Population;

import genericGraphicalComponents.ObservationEvent;
import genericGraphicalComponents.Observer;
import genericGraphicalComponents.OptionLine;
import genericGraphicalComponents.OptionLineEvent;
import group5.MvcPattern.RefreshEvent;
import group5.MvcPattern.View;
import group5.geneticalgorithm.Population.Individuals.IndividualUI;
import java.awt.FlowLayout;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author simonneau
 */
public class PopulationUI extends JPanel implements View, Observer {

    private OptionLine volumeOption;
    private JPanel populationSample;
    private PopulationController controller;

    public PopulationUI(int size) {
        
        this.volumeOption = new OptionLine("Sample size", 0, size, 0);
        this.populationSample = new JPanel(new FlowLayout());
        this.volumeOption.addObserver(this);

        this.add(volumeOption);
        this.add(populationSample);
    }

    @Override
    public void refresh(RefreshEvent ev) {
        
        if(ev instanceof PopulationRefreshEvent){
            
            PopulationRefreshEvent event = (PopulationRefreshEvent)ev;            
            LinkedList<IndividualUI> samples = event.getSample();
            
            for(IndividualUI sample : samples){
                
                this.populationSample.add(sample);
            }
        }else if(ev instanceof ObservableVolumeRefreshEvent){
            
            ObservableVolumeRefreshEvent event = (ObservableVolumeRefreshEvent)ev;
            this.volumeOption.setValue(event.getValue());
        }
        
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        
        if (ev instanceof OptionLineEvent) {
            
            OptionLineEvent event = (OptionLineEvent) ev;
            this.controller.applyChanges(new PopulationControlEvent(this, event.getValue()));
        }
    }
}
