/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.WorldMap;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.painter.Painter;

/**
 *
 * @author simonneau
 */
public class PathPainter implements Painter {
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
