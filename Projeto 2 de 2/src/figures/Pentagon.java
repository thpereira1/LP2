package figures;

import java.awt.*;

public class Pentagon extends Figure {
	private Polygon p = new Polygon();

	public Pentagon (int x, int y, int w, int h, Color contorno, Color fundo) {
		super(x, y, w, h, contorno, fundo);
        
        p.npoints = 4;
        p.xpoints[0] = (int) (x + w/2);
        p.xpoints[1] = x + w;
        p.xpoints[2] = (int) (x + w*0.75);
        p.xpoints[3] = (int) (x + w*0.25);
        
        p.ypoints[0] = y;
        p.ypoints[1] = (int) (y + h*0.40);
        p.ypoints[2] = y+h;
        p.ypoints[3] = y+h;
        
        p.addPoint(x, (int) (y + h*0.40));
    }

	@Override
	public void paint(Graphics g, boolean focado) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(contorno);
        
        Pentagon pContorno = new Pentagon(x-1, y-1, w+2, h+2, contorno, fundo);
        g2d.drawPolygon(pContorno.p.xpoints, pContorno.p.ypoints, pContorno.p.npoints);
        g2d.drawPolygon(p.xpoints, p.ypoints, p.npoints);
        g2d.setColor(fundo);
        g2d.fillPolygon(p);
        super.paint(g2d, focado);
	}

}
