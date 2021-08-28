package Figures;

import java.awt.*;

public class Rect {
    private int x, y;
    private int w, h;
	private Color bgc, cdb;

    public Rect (int x, int y, int w, int h, Color cdb, Color bgc) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
	this.cdb = cdb;
	this.bgc = bgc;
    }

    public void print () {
        System.out.format("Retangulo possui o tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
	g2d.setColor(cdb);
        g2d.drawRect(this.x, this.y, this.w, this.h);
	this.x+=1; this.y+=1;
	this.w-=1; this.h-=1;
	g2d.setColor(bgc);
	g2d.fillRect(this.x, this.y, this.w, this.h);
    }
}
