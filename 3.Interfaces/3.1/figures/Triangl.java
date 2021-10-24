package figures;

import java.awt.*;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.util.Random;

public class Triangl extends Figure {
     public int x1,x2,x3;
	  public int y1,y2,y3;
      public int x,y,w, h;

	   private Color fundo;
       private Color contorno;
       Random rand = new Random();


    public Triangl (int x1, int x2, int x3, int y1, int y2, int y3, int x, int y, int w, int h, Color fundo, Color contorno){
        super(x, y, w, h, fundo, contorno);
    }

    //@Override
    public void print () {
        System.out.format("Triangulo possui o tamanho de (%d,%d) na posicao (%d,%d,%d,%d).\n",
            this.x1, this.x2, this.x3, this.y1,this.y2,this.y3);
    }
    @Override
    public void paint (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
        g.drawPolygon(new int[] {x1, x2, x3}, new int[] {y1, y2, y3}, 3);
        g.fillPolygon(new int[] {x1, x2, x3}, new int[] {y1, y2, y3}, 3);
        g2d.setColor(new Color (rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
	}
}
