/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.TSP;

import GraphicalComponents.IdentifiableComponent;
import GraphicalComponents.Observable;
import GraphicalComponents.ObservationEvent;
import GraphicalComponents.Observer;
import GraphicalComponents.ValidateButton;
import Util.WorldMap.DestinationPoolUI;
import geneticalgorithm.Population.SpreadResetOrderEvent;
import geneticalgorithm.Problem.ProblemUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JDialog;

/**
 *
 * @author simonneau
 */
public class TSPUI extends ProblemUI {

    private ValidateButton destinationPool;
    private JDialog destinationPoolUI;
    boolean waypointsChanged = false;
    private int dpUIid;

    /**
     *
     * @param tsp
     */
    public TSPUI(TSP tsp) {
        super(tsp);

        DestinationPoolUI dpUI = (DestinationPoolUI) tsp.getDestinationPool().getUI();
        this.dpUIid = dpUI.getId();
        
        this.destinationPoolUI = new JDialog();
        this.destinationPoolUI.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.destinationPoolUI.setLayout(new BorderLayout());
        this.destinationPoolUI.setSize(1000, 800);
        this.destinationPoolUI.add(dpUI, BorderLayout.CENTER);
        
        this.destinationPool = new ValidateButton("set destinations");
        this.add(this.destinationPool, FlowLayout.LEADING);
        
        this.destinationPool.addObserver(this);
        dpUI.addObserver(this);
    }

    private void notifyObserver(ObservationEvent ev) {
        for (Observer observer : this.observers) {
            observer.reactToChanges(ev);
        }
    }

    @Override
    public void reactToChanges(ObservationEvent ev) {
        
        super.reactToChanges(ev);
        Observable source = ev.getSource();

        if (source instanceof IdentifiableComponent) {
            int id = ((IdentifiableComponent) source).getId();

            if (id == this.destinationPool.getId()) {
                this.destinationPoolUI.setVisible(true);

            } else if (id == this.validateButton.getId()) {
                
                if (this.waypointsChanged) {
                    
                    this.notifyObserver(new SpreadResetOrderEvent(this));
                    this.waypointsChanged = false;
                }
            }else if(id == this.dpUIid){
                
                this.waypointsChanged = true;
            }
        }
    }
}
