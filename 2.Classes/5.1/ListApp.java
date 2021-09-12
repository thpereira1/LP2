import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class ListApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    ArrayList<Rect> rs = new ArrayList<Rect>();
    ArrayList<Ellipse> es = new ArrayList<Ellipse>();
    Random rand = new Random();

    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    if (evt.getKeyChar() == 'r') {
                        int x = rand.nextInt(600);
                        int y = rand.nextInt(600);
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);
                        rs.add(new Rect(x,y, w,h));
                        repaint();  
                    }else {
                        if(evt.getKeyChar() == 'e'){
                            int x = rand.nextInt(600);
                            int y = rand.nextInt(600);
                            int w = rand.nextInt(85);
                            int h = rand.nextInt(85);
                            Color contorno = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                            Color fundo = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                            es.add(new Ellipse(x, y, w, h, contorno, fundo));
                            repaint();
                        }
                    }
                }
            }
        );

        this.setTitle("Lista de Retângulos");
        this.setSize(600, 600);
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Rect r: this.rs) {
            r.paint(g);
        }
        for (Ellipse e: this.es){
            e.paint(g);
        }
    }
}

class Rect {
    int x, y;
    int w, h;

    Rect (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRect(this.x, this.y, this.w, this.h);
    }
}

class Ellipse {
    
    int x, y;
    int w, h;
    Color contorno;
    Color fundo;

    Ellipse (int x, int y, int w, int h, Color contorno, Color fundo) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.contorno = contorno;
        this.fundo = fundo;
    }

    void print () {
        System.out.format("O retângulo possui o tamanho de (%d,%d) na posição (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }
    
    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawOval(this.x, this.y, this.w, this.h);
    }
}
