import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;

import figures.*;

class ListApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {

    ArrayList<Figure> figs = new ArrayList<Figure>();
    ArrayList<Rect> cores = new ArrayList<Rect>();
    Random rand = new Random();
    Point posMouse = null;
    Figure focus = null;
    Rect rectFocus = null;
    Ellipse ellipseFocus = new Ellipse(0, 0, 12, 12, Color.red, Color.white);
    boolean bolinhaFocus = false;
    boolean coresFocus = false;
    int distanciaX, distanciaY;


    ListFrame () {

        this.setTitle("Projeto 1/2");
        this.setSize(800, 800);
        setLocationRelativeTo(null);
        
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        
        

        




        this.addMouseListener(new MouseAdapter() {
        	public void mousePressed (MouseEvent evt) {
        		posMouse = getMousePosition();
        		


        		if (focus != null && (cores.get(0).x <= posMouse.x && posMouse.x <= (cores.get(21).x + cores.get(21).w)) &&
        				(cores.get(0).y <= posMouse.y && posMouse.y <= (cores.get(21).y + cores.get(21).h))) {
        			int i = 0;
        			for (Rect cor: cores) {
        		        if ((cor.x <= posMouse.x && posMouse.x <= (cor.x + cor.w)) &&
        		        		(cor.y <= posMouse.y && posMouse.y <= (cor.y + cor.h))) {
        		        	coresFocus = true;
        		        	bolinhaFocus = false;
        		        	figs.remove(focus);
        		        	

         		        	if (i <= 10) {                      
                        		focus.contorno = cor.fundo;
                        		figs.add(focus);	
                        	} else {   
                        		if (focus.getClass().getSimpleName().equals("Line")) {
                        			focus.contorno = cor.fundo;                        			
                        		} else if (focus.getClass().getSimpleName().equals("Rect")) {
                        			Rect r  = new Rect(focus.x, focus.y, focus.w, focus.h, focus.contorno, cor.fundo);
                        			focus = r;                        			
                        		} else if (focus.getClass().getSimpleName().equals("Ellipse")) {
                        			Ellipse e = new Ellipse(focus.x, focus.y, focus.w, focus.h, focus.contorno, cor.fundo);
                        			focus = e;                        			
                        		} else {
                        			Pentagon p = new Pentagon(focus.x, focus.y, focus.w, focus.h, focus.contorno, cor.fundo);
                        			focus = p;                       			
                        		}                        		
                        		figs.add(focus);
                        	}
         		        	repaint();
                        	break;
                        }
                        i++;
                    }



        		} else if ((ellipseFocus.x <= posMouse.x && posMouse.x <= (ellipseFocus.x + ellipseFocus.w)) &&
                		(ellipseFocus.y <= posMouse.y && posMouse.y <= (ellipseFocus.y + ellipseFocus.h))) {
        			bolinhaFocus = true;
        			coresFocus = false;
        			distanciaX = ellipseFocus.x - posMouse.x;
            		distanciaY = ellipseFocus.y - posMouse.y;
        		




                } else {
                	bolinhaFocus = false;
                	coresFocus = false;
                	focus = null;
                	for (Figure fig: figs) {
                        if ( ((fig.x <= posMouse.x && posMouse.x <= (fig.x + fig.w)) && (fig.y <= posMouse.y && posMouse.y <= (fig.y + fig.h))) || 
                        		((fig.x >= posMouse.x && posMouse.x >= (fig.x + fig.w)) && (fig.y >= posMouse.y && posMouse.y >= (fig.y + fig.h)))) {
                        	focus = fig;                              	
                        }
                    }
                	


            		if (focus != null) {
            			figs.remove(focus);       	
                    	figs.add(focus);
                    	distanciaX = focus.x - posMouse.x;
                		distanciaY = focus.y - posMouse.y;
            		}
            		repaint();
                }
        	}
		});      
        

        this.addMouseMotionListener(new MouseMotionAdapter() {
        	public void mouseDragged(MouseEvent evt) {
        		posMouse = getMousePosition();
        		

        		if (bolinhaFocus) {     			
        			int novaDistanciaX = ellipseFocus.x - posMouse.x;
        			int novaDistanciaY = ellipseFocus.y - posMouse.y;
        			int arrastoX = distanciaX - novaDistanciaX;
        			int arrastoY = distanciaY - novaDistanciaY;
        			figs.remove(focus);
        			focus.w += arrastoX;
        			focus.h += arrastoY;
        			if (focus.getClass().getSimpleName().equals("Pentagon")) {
            			Pentagon p = (Pentagon) focus;
            			focus = new Pentagon(focus.x, focus.y, focus.w, focus.h, focus.contorno, p.fundo);
            		}
        			figs.add(focus);
        			repaint();  
        			

        		} else  {

        			if (!coresFocus) {


        				if (focus != null) {             			        			
                    		figs.remove(focus);
                    		focus.x = posMouse.x + distanciaX;
                    		focus.y = posMouse.y + distanciaY;
                    		if (focus.getClass().getSimpleName().equals("Pentagon")) {
                    			Pentagon p = (Pentagon) focus;
                    			focus = new Pentagon(focus.x, focus.y, focus.w, focus.h, focus.contorno, p.fundo);
                    		}
                        	figs.add(focus); 
                    		repaint();	
                		}
        			}
        			
        		}	
        	}
		});
        

        this.addKeyListener(new KeyAdapter() {
        	public void keyPressed (KeyEvent evt) {
        		

        		posMouse = getMousePosition();
        		int x = posMouse.x;
                int y = posMouse.y;
                int w = 70;
                int h = 70;
                Color contorno = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                Color fundo = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                
              
                if (evt.getKeyChar() == 'l') {
                    figs.add(new Line(x, y, w, 0, contorno));
                    focus = figs.get(figs.size()-1);
                } else if (evt.getKeyChar() == 'r') {
                	figs.add(new Rect(x, y, w, h, contorno, fundo));
                	focus = figs.get(figs.size()-1);                	
                } else if (evt.getKeyChar() == 'e') {
                	figs.add(new Ellipse(x, y, w, h, contorno, fundo));
                	focus = figs.get(figs.size()-1);               	
                } else if (evt.getKeyChar() == 'p') {
                	figs.add(new Pentagon(x, y, w, h, contorno, fundo));
                	focus = figs.get(figs.size()-1);               	
                } else if (evt.getKeyCode() == 127) {
                	figs.remove(focus);
                	focus = null;
                	rectFocus = null;
                }
                repaint();
        	}
		});

    }

   
    public void paint (Graphics g) {
        super.paint(g);
        
        
        for (Figure fig: this.figs) {
            fig.paint(g);
        }
        
       
        if (focus != null) {
        	rectFocus = new Rect(focus.x -4, focus.y -4, focus.w + 8, focus.h + 8, Color.red);
        	rectFocus.paintFocus(g);
        	ellipseFocus.x = rectFocus.x + rectFocus.w - 8;
        	ellipseFocus.y = rectFocus.y + rectFocus.h - 8;
        	ellipseFocus.paint(g);
        	
        	
        	criaListaCores(this);
        	for (Rect cor: cores) {
                cor.paint(g);
            }
        }
    }
    

    void criaListaCores(JFrame janela) {
    	cores.clear();
    	cores.add(new Rect(10, janela.getHeight()-100, 30, 30, Color.black, Color.black));
    	cores.add(new Rect(40, janela.getHeight()-100, 30, 30, Color.black, Color.white));
    	cores.add(new Rect(70, janela.getHeight()-100, 30, 30, Color.black, Color.gray));
    	cores.add(new Rect(100, janela.getHeight()-100, 30, 30, Color.black, Color.green));
    	cores.add(new Rect(130, janela.getHeight()-100, 30, 30, Color.black, Color.blue));
    	cores.add(new Rect(160, janela.getHeight()-100, 30, 30, Color.black, Color.pink));
    	cores.add(new Rect(190, janela.getHeight()-100, 30, 30, Color.black, Color.yellow));
    	cores.add(new Rect(220, janela.getHeight()-100, 30, 30, Color.black, Color.orange));
    	cores.add(new Rect(250, janela.getHeight()-100, 30, 30, Color.black, Color.red));
    	cores.add(new Rect(280, janela.getHeight()-100, 30, 30, Color.black, new Color(150, 75, 0)));
    	cores.add(new Rect(310, janela.getHeight()-100, 30, 30, Color.black, new Color(148, 0, 211)));
    	
    	cores.add(new Rect(10, janela.getHeight()-70, 30, 30, Color.black, Color.black));
    	cores.add(new Rect(40, janela.getHeight()-70, 30, 30, Color.black, Color.white));
    	cores.add(new Rect(70, janela.getHeight()-70, 30, 30, Color.black, Color.gray));
    	cores.add(new Rect(100, janela.getHeight()-70, 30, 30, Color.black, Color.green));
    	cores.add(new Rect(130, janela.getHeight()-70, 30, 30, Color.black, Color.blue));
    	cores.add(new Rect(160, janela.getHeight()-70, 30, 30, Color.black, Color.pink));
    	cores.add(new Rect(190, janela.getHeight()-70, 30, 30, Color.black, Color.yellow));
    	cores.add(new Rect(220, janela.getHeight()-70, 30, 30, Color.black, Color.orange));
    	cores.add(new Rect(250, janela.getHeight()-70, 30, 30, Color.black, Color.red));
    	cores.add(new Rect(280, janela.getHeight()-70, 30, 30, Color.black, new Color(150, 75, 0)));
    	cores.add(new Rect(310, janela.getHeight()-70, 30, 30, Color.black, new Color(148, 0, 211)));
    }
}
