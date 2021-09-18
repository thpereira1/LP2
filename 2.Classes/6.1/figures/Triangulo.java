package figures;

import java.awt.*;
import java.awt.Graphics;
import java.awt.geom.Line2D;

public class Triangulo extends Figure {
     int x1,x2,x3;
	  private int y1,y2,y3;
	   private Color fundo;

    public Triangulo (int x1,int x2,int x3,int y1,int y2,int y3, Color fundo) {
        this.x1 = x1;
	      this.x2 = x2;
	      this.x3 = x3;
        this.y1 = y1;
	      this.y2 = y2;
	      this.y3 = y3;
	      this.fundo = fundo;
    }
    @Override
    public void print () {
        System.out.format("Triangulo possui o tamanho de (%d,%d) na posicao (%d,%d,%d,%d).\n",
            this.x1, this.x2, this.x3, this.y1,this.y2,this.y3);
    }
    @Override
    public void paint (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
        g.drawPolygon(new int[] {x1, x2, x3}, new int[] {y1, y2, y3}, 3);
	}
}
