package figures;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import ivisible.IVisible;

public abstract class Figure implements IVisible, Serializable {
	public int x, y, w, h;
	public Color contorno, fundo;
    
    public Figure (int x, int y, int w, int h, Color contorno, Color fundo) {
		this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.contorno = contorno;
        this.fundo = fundo;
	}
    
    public void drag (int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    
    public boolean clicked (int x, int y) {
        return (this.x<=x && x<=this.x+this.w && this.y<=y && y<=this.y+this.h);
    }
    
    public void paint(Graphics g, boolean focado) {
    	if (focado) {
    		Rect contornoFoco = new Rect(this.x -4, this.y -4, this.w + 8, this.h + 8, Color.red, Color.red);
    		contornoFoco.paintFocus(g);
    		Ellipse elipseFoco = new Ellipse(contornoFoco.x + contornoFoco.w - 8, contornoFoco.y + contornoFoco.h - 8, 
    				12, 12, Color.red, Color.white);
    		elipseFoco.paint(g, false);
    	}
    }
    
}
