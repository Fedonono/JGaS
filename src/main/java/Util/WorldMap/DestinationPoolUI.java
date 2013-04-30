/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.WorldMap;

import GraphicalComponents.CustomTextField;
import GraphicalComponents.IdentifiableComponent;
import GraphicalComponents.IdentifiableObservableComponent;
import GraphicalComponents.NumberTextField;
import GraphicalComponents.Observable;
import GraphicalComponents.ObservationEvent;
import GraphicalComponents.Observer;
import GraphicalComponents.ValidateButton;
import MvcPattern.RefreshEvent;
import MvcPattern.View;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.swing.JLabel;
import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointPainter;
import org.jdesktop.swingx.painter.Painter;

/**
 *
 * @author simonneau
 */
public class DestinationPoolUI extends IdentifiableObservableComponent implements View, MouseListener, Observer {

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

        this.footer.addObserver(this);
    }

    /**
     *
     * @param ev
     */
    @Override
    public void refresh(RefreshEvent ev) {

        if (ev instanceof DestinationPoolRefreshEvent) {
            
            DestinationPool source = (DestinationPool) ev.getSource();

            Painter painter = this.buildWaypointPainter(source.getDestinations());

            CustomPainter overlay = new CustomPainter();
            overlay.addOverlay(painter);
            this.map.getMainMap().setOverlayPainter(overlay);
            
        }
    }
    
    

    private Painter buildWaypointPainter(List<Destination> waypoints) {

        Set<Waypoint> destinations = new HashSet<>();
        destinations.addAll(waypoints);
        WaypointPainter wp = new WaypointPainter();
        wp.setWaypoints(destinations);

        return wp;

    }

    public void drawLoopPath(ArrayList<Destination> waypoints) {

        int size = waypoints.size();
        CustomPainter overlay = new CustomPainter();
        overlay.addOverlay(this.buildWaypointPainter(waypoints));
        JXMapViewer mainMap = this.map.getMainMap();
        Painter painter;

        for (int i = 0; i < size - 1; i++) {

            GeoPosition gp1 = waypoints.get(i).getPosition();
            GeoPosition gp2 = waypoints.get(i + 1).getPosition();

            painter = new PathPainter(mainMap, gp1, gp2);
            overlay.addOverlay(painter);
        }

        this.map.getMainMap().setOverlayPainter(overlay);
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

    public void setFooterVisible(boolean visible){
        this.footer.setVisible(visible);
    }
    
    @Override
    public void reactToChanges(ObservationEvent ev) {

        if (ev instanceof AddPointEvent) {
            AddPointEvent event = (AddPointEvent) ev;
            this.controller.applyChanges(new AddDestinationUsrEvent(this, event.getPosition(), event.getLabel()));
            this.notifyObservers();

        } else if (ev instanceof ClearEvent) {

            this.controller.applyChanges(new ClearDestinationsUsrEvent(this));
            this.notifyObservers();
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.reactToChanges(new WayPointChangedEvent(this));
        }
    }

    private class Footer extends IdentifiableObservableComponent implements Observer {

        private ValidateButton addPoint;
        private ValidateButton clear;
        private NumberTextField latitude;
        private NumberTextField longitude;
        private CustomTextField label;

        public Footer() {

            this.addPoint = new ValidateButton("add way point");
            this.clear = new ValidateButton("clear");
            this.latitude = new NumberTextField("latitude", 0, -90, 90, 20);
            this.longitude = new NumberTextField("longitude", 0, -180, 180, 20);
            this.label = new CustomTextField("");

            this.setLayout(new FlowLayout());
            this.add(this.addPoint);
            this.add(this.latitude);
            this.add(this.longitude);
            this.add(new JLabel("label:"));
            this.add(this.label);
            this.add(clear, FlowLayout.LEFT);


            this.clear.addObserver(this);
            this.addPoint.addObserver(this);

        }

        public void setGeoPosition(GeoPosition position) {

            this.latitude.setValue(position.getLatitude());
            this.longitude.setValue(position.getLongitude());
        }

        private void notifyObserver(ObservationEvent ev) {
            for (Observer o : this.observers) {
                o.reactToChanges(ev);
            }
        }

        @Override
        public void notifyObservers() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void reactToChanges(ObservationEvent ev) {

            Observable source = ev.getSource();
            if (source instanceof IdentifiableComponent) {

                int id = ((IdentifiableComponent) source).getId();

                if (id == this.addPoint.getId()) {
                    
                    this.notifyObserver(new AddPointEvent(this, new GeoPosition(this.latitude.getValue(), this.longitude.getValue()), this.label.getText()));
                    
                } else if (id == this.clear.getId()) {
                    
                    this.notifyObserver(new ClearEvent(this));
                }
            }
        }
    }

    private class AddPointEvent extends ObservationEvent {

        private GeoPosition position;
        private String label;

        public AddPointEvent(Footer source, GeoPosition position, String label) {
            super(source);
            this.position = position;
            this.label = label;

        }

        public GeoPosition getPosition() {
            return this.position;
        }

        public String getLabel() {
            return label;
        }
    }

    private class ClearEvent extends ObservationEvent {

        public ClearEvent(Footer source) {
            super(source);
        }
    }

    private class CustomPainter implements Painter {

        private LinkedList<Painter> overlays = new LinkedList<>();

        public void addOverlay(Painter p) {
            this.overlays.add(p);
        }

        @Override
        public void paint(Graphics2D g, Object object, int width, int height) {

            for (Painter painter : this.overlays) {
                painter.paint(g, object, width, height);
            }
        }
    }

    private class PathPainter implements Painter {

        private GeoPosition gp1;
        private GeoPosition gp2;
        private JXMapViewer map;

        public PathPainter(JXMapViewer map, GeoPosition gp1, GeoPosition gp2) {
            this.gp1 = gp1;
            this.gp2 = gp2;
            this.map = map;
        }

        @Override
        public void paint(Graphics2D g, Object object, int width, int height) {
            Line2D.Double line = new Line2D.Double(this.map.convertGeoPositionToPoint(gp1), this.map.convertGeoPositionToPoint(gp2));
            Graphics2D g2D = (Graphics2D) g;
            g2D.setStroke(new BasicStroke(3));
            g2D.draw(line);
        }
    }
    
    /*public static void main(String[] args){
        DestinationPool dp = new DestinationPool();
        DestinationPoolUI dpUI = (DestinationPoolUI)dp.getUI();
        
        JFrame frame = new JFrame();
        frame.setSize(1200,800);
        frame.add(dpUI);
        frame.setVisible(true);
    }*/
}
