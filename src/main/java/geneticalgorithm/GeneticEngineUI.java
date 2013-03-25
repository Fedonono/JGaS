/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import GraphicalComponents.IdentifiableComponent;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
import geneticalgorithm.Population.PopulationUI;
import java.awt.BorderLayout;
import javax.swing.JLabel;

/**
 *
 * @author simonneau
 */
public class GeneticEngineUI extends IdentifiableComponent implements View {

    private static String stepLabel = "current step: ";
    private JLabel footer;

    public GeneticEngineUI(PopulationUI pUI) {

        this.setLayout(new BorderLayout());
        this.footer = new JLabel(stepLabel + 0);
        this.add(this.footer, BorderLayout.SOUTH);
        this.add(pUI, BorderLayout.CENTER);

    }

    public void setCenter(PopulationUI pUI) {
        this.add(pUI, BorderLayout.CENTER);
    }

    @Override
    public void refresh(RefreshEvent ev) {
        // recuperer le step courant et le mettre à jour.
    }
}
