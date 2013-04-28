/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.WorldMap;

import GraphicalComponents.IdentifiableComponent;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import org.jdesktop.swingx.JXMapKit;

/**
 *
 * @author simonneau
 */
public class DestinationPoolUI extends IdentifiableComponent implements View{

    private DestinationPoolController controller;
    private JXMapKit map;
    /**
     *
     * @param controller
     */
    public DestinationPoolUI(DestinationPoolController controller){
        this.controller = controller;
        this.map = new JXMapKit();
        this.setLayout(new BorderLayout());
        this.add(map);  
        this.map.enableInputMethods(true);
        this.map.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
               
    }
    
    /**
     *
     * @param ev
     */
    @Override
    public void refresh(RefreshEvent ev) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setSize(1200, 800);
        frame.add(new DestinationPoolUI(null));
        frame.setVisible(true);
    }
}
