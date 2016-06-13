package client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SierpinskiTriangle{

  private int order;

  public SierpinskiTriangle(int order) {
      this.order = order;
  }

    
    protected void draw(Graphics g) {

      // Select three points in proportion to the panel size
      Point p1 = new Point(312, 450);
      Point p2 = new Point(156, 680);
      Point p3 = new Point(468, 680);
      
      displayTriangles(g, order, p1, p2, p3);
    }
    
    private static void displayTriangles(Graphics g, int order,
        Point p1, Point p2, Point p3) {
      g.setColor(Color.WHITE);
      if (order >= 0) {
        // Draw a triangle to connect three points
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
        g.drawLine(p1.x, p1.y, p3.x, p3.y);
        g.drawLine(p2.x, p2.y, p3.x, p3.y);
        
        // Get the midpoint on each edge in the triangle
        Point p12 = midpoint(p1, p2);
        Point p23 = midpoint(p2, p3);
        Point p31 = midpoint(p3, p1);

        // Recursively display three triangles
        displayTriangles(g, order - 1, p1, p12, p31);
        displayTriangles(g, order - 1, 
          p12, p2, p23);
        displayTriangles(g, order - 1, 
          p31, p23, p3);
      }
    }
    
    private static Point midpoint(Point p1, Point p2) {
      return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }
}
