import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class RectPaint {
    public static void main (String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}

class PaintFrame extends JFrame {
    Rect r1;
    Rect r2;
    Rect r3;

    PaintFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        this.setTitle("Retângulos Coloridos");
        this.setSize(350, 350);
        this.r1 = new Rect(50,50, 100, 30, Color.red, Color.blue);
        this.r2 = new Rect(50, 200, 200, 40, Color.magenta, Color.red);
        this.r3 = new Rect(50, 100, 130, 70, Color.red, Color.green);

    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g);
        this.r2.paint(g);
        this.r3.paint(g);
    }
}

class Rect {
    int x, y;
    int w, h;
    Color sides;
    Color background;

    Rect (int x, int y, int w, int h, Color sides, Color background) { ;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.sides = sides;
        this.background = background;
    }


    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0,255,0));
        g2d.drawRect(this.x,this.y, this.w,this.h);
        g2d.setColor(background);

        g2d.fillRect(x+2, y+2, w-2, h-2);
    }
}
