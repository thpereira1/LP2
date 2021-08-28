import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Figures.*;

class PackApp {
    public static void main (String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame {
    Rect r1;
    Ellipse e1;
    Triangulo t1;

    PackFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Pacotes Java");
        this.setSize(350, 350);
        this.r1 = new Rect(50, 50, 100, 30);
        this.e1 = new Ellipse(50, 100, 100, 30);
        this.t1 = new Triangulo(150, 110, 70, 180, 140, 110, Color.green);
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g);
        this.e1.paint(g);
        this.t1.paint(g);
    }
}
