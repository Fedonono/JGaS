/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.WorldMap;

import GraphicalComponents.IdentifiableComponent;
import GraphicalComponents.IdentifiableObservableComponent;
import GraphicalComponents.NumberTextField;
import GraphicalComponents.ObservationEvent;
import GraphicalComponents.Observer;
import GraphicalComponents.ValidateButton;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.mapviewer.GeoPosition;

/**
 *
 * @author simonneau
 */
public class DestinationPoolUI extends IdentifiableComponent implements View, MouseListener {

    private DestinationPoolController controller;
    private JXMapKit map;
    private Footer footer;

    /**
     *
     * @param controller
     */
    public DestinationPoolUI(DestinationPoolController controller) {
        
        this.controller = controller;
        this.map = new JXMapKit();
        this.map.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
        this.map.getMainMap().addMouseListener(this);
        this.footer = new Footer();
        
        this.setLayout(new BorderLayout());
        this.add(map, BorderLayout.CENTER);
        this.add(this.footer, BorderLayout.SOUTH);
        

    }

    /**
     *
     * @param ev
     */
    @Override
    public void refresh(RefreshEvent ev) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

    

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1200, 800);
        frame.add(new DestinationPoolUI(null));
        frame.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
         Point p = me.getPoint();
         
        GeoPosition gp = this.map.getMainMap().convertPointToGeoPosition(p);
        this.footer.setGeoPosition(gp);
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

   

   

    private class Footer extends IdentifiableObservableComponent implements Observer {

        private ValidateButton addPoint;
        private ValidateButton clear;
        private NumberTextField latitude;
        private NumberTextField longitude;

        public Footer() {
            
            this.addPoint = new ValidateButton("add way point");
            this.clear = new ValidateButton("clear");
            this.latitude = new NumberTextField("latitude", 0, -90, 90, 20);
            this.longitude = new NumberTextField("longitude", 0, -180, 180, 20);
            
            this.setLayout(new FlowLayout());
            this.add(this.addPoint);
            this.add(this.latitude);
            this.add(this.longitude);
            this.add(clear, FlowLayout.LEFT);
            
            this.addPoint.addObserver(this);

        }
        
        public void setGeoPosition(GeoPosition position){
            
            this.latitude.setValue(position.getLatitude());
            this.longitude.setValue(position.getLongitude());
        }

        @Override
        public void notifyObservers() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void reactToChanges(ObservationEvent ev) {
        }
    }
}
