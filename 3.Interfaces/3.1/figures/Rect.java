package figures;

import java.awt.*;
import java.util.Random;

public class Rect extends Figure {
    Random rand = new Random();
    public int contornoR, contornoG, contornoB, fundoR, fundoG, fundoB;
    public int x, y, w, h;


    public Rect (int x, int y, int w, int h, Color fundo, Color contorno){
        super(x, y, w, h, fundo, contorno);
    }

    private void print () {
        System.out.format("Retangulo possui o tamanho de (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }
    @Override
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLUE);
        g2d.fillRect(this.x, this.y, this.w, this.h);
        g2d.setColor(new Color(0,255,0));
        g2d.drawRect(this.x,this.y, this.w,this.h);
        g2d.fillRect(x+1, y+1, this.w-1, this.h-1);

    }
}
