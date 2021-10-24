package figures;

import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

public class Ellipse extends Figure {
    public int x, y, w, h;
    Random rand = new Random();


    public Ellipse (int x, int y, int w, int h, Color fundo, Color contorno) {
        super(x, y, w, h, fundo, contorno);
    }



    private void print () {
        System.out.format("Ellipse possui o tamanho de (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
	    
        g2d.draw(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
	    g2d.fillOval(this.x,this.y, this.w,this.h);
        
        g2d.setColor(fundo);
	   
    }
}
