import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

import figures.*;

class app {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Figure focus = null;
    Random rand = new Random();
	Point mp;

    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
	
	this.addMouseListener(
	    new MouseAdapter() {
		public void mousePressed (MouseEvent evt) {  
        int x = evt.getX();
        int y = evt.getY();
		    focus = null;
		    for (Figure fig: figs) {
		        if (fig.clicked(x,y)) {
			    focus = fig;
			    repaint();
			    break;
            }
			else {
			    focus = null;
			    repaint();
			}
		}
	}
});
	

	this.addMouseMotionListener(
	    new MouseMotionAdapter() {
	        public void mouseDragged (MouseEvent evt) { 
		    for (Figure fig: figs) {
		        if (focus == fig) {
			    fig.drag(evt.getX()-focus.x, evt.getY()-focus.y);
			    repaint();
			}
		    }
		}
	    }
	);

        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
					mp = getMousePosition();
				
					int x = mp.x;
					int y = mp.y;
					int w = rand.nextInt(50);
					int h = rand.nextInt(50);

					int x1 = rand.nextInt(350);
					int x2 = rand.nextInt(350);
					int x3 = rand.nextInt(350);
					int y1 = rand.nextInt(350);
					int y2 = rand.nextInt(350);
					int y3 = rand.nextInt(350);


				Color contorno = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
				Color fundo = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));

		    
                    if (evt.getKeyChar() == 'r') {
                        figs.add(new Rect(x, y, w, h, fundo, contorno));
                    }
		    else if (evt.getKeyChar() == 'e') {
                        figs.add(new Ellipse(x, y, w, h, fundo, contorno));
		    }
		    else if (evt.getKeyChar() == 'p') {
		        figs.add(new Texto("alo, teste", "Italic", x, y, w, h, fundo, contorno));
		    }
			else if (evt.getKeyChar() == 't'){
				figs.add(new Triangl(x1, x2, x3, y1, y2, y3, x, y, w, h, fundo, contorno));
			}
	            for (Figure fig: figs) {
		        if (focus == fig) {
					repaint();
			    if (evt.getKeyCode() == KeyEvent.VK_DELETE) { 
			        figs.remove(fig);
				focus = null;
				repaint();
				break;
			    }
			    else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {  
				fig.drag(-1,0);
			    }
			    else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {  
				fig.drag(1,0);
			    }
			    else if (evt.getKeyCode() == KeyEvent.VK_UP) {  
				fig.drag(0,-1);
			    }
			    else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {  
				fig.drag(0,1);
			    }
			    else if (evt.getKeyChar() == '+') {  
				fig.tamanho(1,1);
			    }
			    else if (evt.getKeyChar() == '-') {  
				fig.tamanho(-1,-1);
			    }
				else if(evt.getKeyChar() == 'l'){ 
					figs.clear();
				}
			}
		    }
		    repaint();
                }
            }
        );
        this.setTitle("Projeto LP2 - Editor - 1/2");
        this.setSize(600, 600);
    }

    public void paint (Graphics g) {
        super.paint(g);
	Graphics2D g2d = (Graphics2D) g;
        for (Figure fig: this.figs) {
            fig.paint(g);
        }
	
	if (focus != null) {  
	    g2d.setColor(Color.RED);
	    g2d.drawRect(focus.x-3, focus.y-3, focus.w+6, focus.h+6);
	    focus.paint(g);  
	}
    }
}
