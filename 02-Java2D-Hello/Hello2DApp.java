import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;

public class Hello2DApp {
    public static void main (String[] args) {
        Hello2DFrame frame = new Hello2DFrame();
        frame.setVisible(true);
    }
}

class Hello2DFrame extends JFrame {
    public Hello2DFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.getContentPane().setBackground( Color.black ); 
        this.setTitle("Java2D - Hello World!");
        this.setSize(600, 600);
        
    }

    public void paint (Graphics g) {
        
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setBackground(Color.BLACK);

        int[] xPoints = {50,100,150,200,250,300,350,400,450,500};
        int[] yPoints = {460,300,370,280,310,200,256,200,220,150};
        int nPoints = xPoints.length;

        g2d.setColor(Color.green);
        
        g2d.setStroke(new BasicStroke(1));
        g2d.drawPolygon(xPoints, yPoints, nPoints);

        g2d.setFont(new Font("Comic Sans", Font.ITALIC,25));
        g2d.setColor(Color.yellow);
        
        
        g2d.drawString("Bitcoin",125,160);


        g2d.draw(new Ellipse2D.Double(50, 80,235,164));

	g2d.draw(new Ellipse2D.Double(300, 400,235,164));
	g2d.drawString("STONKS",370,485);
    }

}
