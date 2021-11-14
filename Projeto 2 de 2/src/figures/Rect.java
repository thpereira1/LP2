package figures;

import java.awt.*;

public class Rect extends Figure {

    public Rect (int x, int y, int w, int h, Color contorno, Color fundo) {
        super(x, y, w, h, contorno, fundo);
    }

	@Override
	public void paint(Graphics g, boolean focado) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(contorno);
        g2d.drawRect(this.x-1,this.y-1, this.w+2,this.h+2);
        g2d.drawRect(this.x,this.y, this.w,this.h);
        g2d.setColor(fundo);
        
        g2d.fillRect(x+1, y+1, w-1, h-1);
        super.paint(g2d, focado);
	}
	
	public void paintFocus(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(contorno);
        g2d.drawRect(this.x,this.y, this.w,this.h);
	}


}
