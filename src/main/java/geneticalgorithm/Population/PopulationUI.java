/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.Population;

import GraphicalComponents.IdentifiableComponent;
import GraphicalComponents.ObservationEvent;
import GraphicalComponents.Observer;
import GraphicalComponents.OptionLine;
import GraphicalComponents.ValidateButton;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author simonneau
 */
public class PopulationUI extends JPanel implements View {

    private Header header;
    protected JPanel populationSample;
    protected PopulationController controller;

    public PopulationUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.header = new Header(this);
        this.populationSample = new JPanel(new FlowLayout());

        this.add(this.header);
        this.add(populationSample);
    }

    @Override
    public void refresh(RefreshEvent ev) {

        /*if (ev instanceof PopulationRefreshEvent) {
         PopulationRefreshEvent event = (PopulationRefreshEvent) ev;
         LinkedList<Individual> samples = event.getSample();

         for (Individual sample : samples) {
         sample.notifyViews();
         //this.populationSample.add(sample.getUI()); POUR TSP TODO BY ARNAUD DELETE ?
         }
         } else */
        if (ev instanceof ObservableVolumeRefreshEvent) {
            ObservableVolumeRefreshEvent event = (ObservableVolumeRefreshEvent) ev;
            this.header.setValue(event.getValue());
        }

    }

    public PopulationController getController() {
        return controller;
    }

    public void setController(PopulationController controller) {
        this.controller = controller;
        this.header.setController(controller);
    }
    

    private class Header extends IdentifiableComponent implements Observer {

        private PopulationUI boss;
        private PopulationController controller;
        private OptionLine volumeOption;
        private ValidateButton refresh;

        public Header(PopulationUI boss) {
            
            this.boss = boss;

            this.volumeOption = new OptionLine("Sample size", 1, 1, 20);
            this.refresh = new ValidateButton("refresh");
            
            this.setLayout(new FlowLayout());
            this.add(this.volumeOption);
            this.add(this.refresh);

            this.volumeOption.addObserver(this);
            this.refresh.addObserver(this);
        }

        @Override
        public void reactToChanges(ObservationEvent ev) {

            int id = ((IdentifiableComponent) ev.getSource()).getId();
            
            if(id == this.refresh.getId()){
                
                this.controller.applyChanges(new UsrAskForRefreshEvent(boss));
                
            }else if( id == this.volumeOption.getId()){
                this.controller.applyChanges(new ObservableVolumeUserEvent(boss, this.volumeOption.getValue()));
            }

        }
        
        public void setValue(int value){
            this.volumeOption.setValue(value);
        }
        
        public void setController(PopulationController controller){
            this.controller = controller;
        }
    }
}
