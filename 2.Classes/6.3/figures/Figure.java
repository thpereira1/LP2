package figures;

import java.awt.Color;
import java.awt.Graphics;


public abstract class Figure {
	public abstract void print ();
    	int x, y, w, h;
    	Color contorno;
    	Color fundo;
    public abstract void paint (Graphics g);
}
