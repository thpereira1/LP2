import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import buttons.Botao;

import java.util.ArrayList;
import java.util.Random;

import figures.*;

import java.io.*;

class ListApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    ArrayList<Rect> cores = new ArrayList<Rect>();
    ArrayList<Botao> botoes = new ArrayList<Botao>();
    Botao botaoFocus = null;
    Random rand = new Random();
    Point posMouse = null;
    Figure focus = null;
    Rect rectFocus = null;
    Ellipse ellipseFocus = new Ellipse(0, 0, 12, 12, Color.red, Color.white);
    boolean bolinhaFocus = false;
    boolean coresFocus = false;

    ListFrame () {
        this.setTitle("Projeto 2 de LP2");
        this.setSize(750, 750);
        setLocationRelativeTo(null);
        
        try {
        	FileInputStream f = new FileInputStream("projeto.bin");
        	ObjectInputStream o = new ObjectInputStream(f);
        	this.figs = (ArrayList<Figure>) o.readObject();
        	o.close();
        } catch (Exception x) {
        	System.out.println("Erro ao tentar abrir o arquivo!");
        }
        
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                	try {
                		FileOutputStream f = new FileOutputStream("projeto.bin");
                		ObjectOutputStream o = new ObjectOutputStream(f);
                		o.writeObject(figs);
                		o.flush();
                		o.close();
                	} catch (Exception x) {
                		System.out.println("Erro ao tentar gravar o arquivo!");
                	}
                	
                    System.exit(0);
                }
            }
        );
        
        botoes.add(new Botao(1, new Line(20, 40, 35, 35, Color.black, Color.black)));
        botoes.add(new Botao(2, new Rect(20, 85, 35, 35, Color.black, Color.black)));
        botoes.add(new Botao(3, new Ellipse(20, 130, 35, 35, Color.black, Color.black)));
        botoes.add(new Botao(4, new Pentagon(20, 175, 35, 35, Color.black, Color.black)));
        
        
        
        this.addMouseListener(new MouseAdapter() {
        	public void mousePressed (MouseEvent evt) {
        		posMouse = getMousePosition();
        		
        		if ((15 <= posMouse.x && posMouse.x <= 60) && (35 <= posMouse.y && posMouse.y <= 215)) {
        			bolinhaFocus = false;
                	coresFocus = false;
                	focus = null;
        			for (Botao botao: botoes) {
        				if (botao.clicked(posMouse.x, posMouse.y)) {
        					botaoFocus = botao;
        				}       				       				
        			}
        			repaint();
        		} else {
            		if (focus != null && (cores.get(0).x <= posMouse.x && posMouse.x <= (cores.get(21).x + cores.get(21).w)) &&
            				(cores.get(0).y <= posMouse.y && posMouse.y <= (cores.get(21).y + cores.get(21).h))) {
            			coresFocus = true;
    		        	bolinhaFocus = false;
    		        	figs.remove(focus);      			
            			int i = 0;
            			for (Rect cor: cores) {
            		        if (cor.clicked(posMouse.x, posMouse.y)) {      		        	       		     
             		        	if (i <= 10) {                      
                            		focus.contorno = cor.fundo;
                            		figs.add(focus);	
                            	} else {   
                            		if (focus.getClass().getSimpleName().equals("Line")) {
                            			focus.contorno = cor.fundo;                        			
                            		} else {
                            			focus.fundo = cor.fundo;
                            		}                		
                            		figs.add(focus);
                            	}
             		        	repaint();
                            	break;
                            }
                            i++;
                        }
            		} else if (ellipseFocus.clicked(posMouse.x, posMouse.y)) {
            			bolinhaFocus = true;
            			coresFocus = false;
                    } else {
                    	bolinhaFocus = false;
                    	coresFocus = false;
                    	focus = null;
                    	
                    	if (botaoFocus != null) {
                    		int idx = botaoFocus.idx;
                    		if (idx == 1) {                  			
                    			figs.add(new Line(posMouse.x, posMouse.y, 70, 0, Color.black, Color.white));                           
                    		} else if (idx == 2) {
                    			figs.add(new Rect(posMouse.x, posMouse.y, 70, 70, Color.black, Color.white));
                    		} else if (idx == 3) {
                    			figs.add(new Ellipse(posMouse.x, posMouse.y, 70, 70, Color.black, Color.white));
                    		} else {
                    			figs.add(new Pentagon(posMouse.x, posMouse.y, 70, 70, Color.black, Color.white));
                    		}
                    		focus = figs.get(figs.size()-1);
                    		botaoFocus = null;
                    		
                    	} else {
                    		for (Figure fig: figs) {
                                if (fig.clicked(posMouse.x, posMouse.y)) {                   
                                	focus = fig;                              	
                                }
                            }
                    	}
                    	
                		if (focus != null) {
                			figs.remove(focus);       	
                        	figs.add(focus);
                		} else {
                			ellipseFocus.x = -10;
                        	ellipseFocus.y = -10;
                		}
                		repaint();
                    }
        		}
        	}
		});      
        
        this.addMouseMotionListener(new MouseMotionAdapter() {
        	public void mouseDragged(MouseEvent evt) {
        		Point posNovaMouse = getMousePosition();
        		
        		if (bolinhaFocus) {     			
        			figs.remove(focus);

        			focus.w += posNovaMouse.x - posMouse.x;
        			focus.h += posNovaMouse.y - posMouse.y;
        			
        			if (focus.getClass().getSimpleName().equals("Pentagon")) {
            			Pentagon p = (Pentagon) focus;
            			focus = new Pentagon(focus.x, focus.y, focus.w, focus.h, focus.contorno, p.fundo);
            		}
        			figs.add(focus);
        			repaint();  
        			
        			posMouse.x = posNovaMouse.x;
            		posMouse.y = posNovaMouse.y;
        			
        		} else  {
        			if (!coresFocus) {
        				if (focus != null) {             			        			
                    		figs.remove(focus);
                    		
                    		focus.drag(posNovaMouse.x - posMouse.x, posNovaMouse.y - posMouse.y);
                    		
                    		if (focus.getClass().getSimpleName().equals("Pentagon")) {
                    			Pentagon p = (Pentagon) focus;
                    			focus = new Pentagon(focus.x, focus.y, focus.w, focus.h, focus.contorno, p.fundo);
                    		}
                        	figs.add(focus); 
                    		repaint();	
                    		
                    		posMouse.x = posNovaMouse.x;
                    		posMouse.y = posNovaMouse.y;
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
                    figs.add(new Line(x, y, w, 0, contorno, fundo));
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
                	ellipseFocus.x = -10;
                	ellipseFocus.y = -10;
                }
                repaint();
        	}
		});
    }

    public void paint (Graphics g) {
        super.paint(g);
        
        for (Figure fig: this.figs) {
        	if (fig == focus) {
        		fig.paint(g, true);
        		
        		ellipseFocus.x = focus.x + focus.w - 4;
            	ellipseFocus.y = focus.y + focus.h - 4;
        	} else {
        		fig.paint(g, false);
        	}
        }
        
        for (Botao botao: botoes) {
        	botao.paint(g, botao == botaoFocus);
        }
        
        if (focus != null) {      	
        	criaListaCores(this);
        	for (Rect cor: cores) {
                cor.paint(g, false);
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
