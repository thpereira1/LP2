package Figures;

import java.awt.*;

public class Ellipse {
    private int x, y;
    private int w, h;
	private Color bgc, cdb;

    public Ellipse (int x, int y, int w, int h, Color cdb, Color bgc) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
	this.cdb = cdb;
	this.bgc = bgc;
    }

    public void print () {
        System.out.format("Elipse possui o tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
	g2d.setColor(cdb);
        g2d.drawOval(this.x, this.y, this.w, this.h);
	this.x+=1; this.y+=1;
	this.w-=1; this.h-=1;
	g.setColor(bgc);
	g2d.fillOval(this.x, this.y, this.w, this.h);
    }
}
