package figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Line extends Figure {
	
	public Line (int x, int y, int w, int h, Color contorno, Color fundo) {
		super(x, y, w, h, contorno, fundo);
	}

	@Override
	public void paint(Graphics g, boolean focado) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(contorno);
        g2d.drawLine(x, y-1, x+w, y+h-1);
        g2d.drawLine(x, y, x+w, y+h);
        super.paint(g2d, focado);
	}

}
