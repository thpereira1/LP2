package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figure {

    public Ellipse (int x, int y, int w, int h, Color contorno, Color fundo) {
    	super(x, y, w, h, contorno, fundo);
    }

	@Override
	public void paint(Graphics g, boolean focado) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(contorno);
        g2d.draw(new Ellipse2D.Double(this.x-1,this.y-1, this.w+2,this.h+2));
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
        g2d.setColor(fundo);
        g2d.fillOval(x, y, w, h);       
        super.paint(g2d, focado);
	}
    
}
