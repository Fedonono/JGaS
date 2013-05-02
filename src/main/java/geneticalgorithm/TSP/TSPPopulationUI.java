/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.TSP;

import GraphicalComponents.IdentifiableComponent;
import GraphicalComponents.Observable;
import GraphicalComponents.ObservationEvent;
import GraphicalComponents.ValidateButton;
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
    private ValidateButton refresh;

    /**
     *
     * @param source
     * @param controller
     */
    public TSPPopulationUI(TSPPopulation source, PopulationController controller) {
        this.refresh = new ValidateButton("refresh");
        this.refresh.addObserver(this);

        this.setController(controller);
        this.center = (DestinationPoolUI) source.getDestinationPool().getUI();

        this.removeAll();
        this.add(this.refresh, BorderLayout.NORTH);
        this.add(this.center, BorderLayout.CENTER);

    }

    @Override
    public void populationRefreshEventTreatment(PopulationRefreshEvent event) {

        LinkedList<Individual> samples = event.getSample();
        samples.peekFirst().notifyViews();
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        Observable source = ev.getSource();
        if (source instanceof IdentifiableComponent) {
            this.notifyObservers();
        }
    }
}
