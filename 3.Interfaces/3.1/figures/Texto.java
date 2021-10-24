package figures;
import java.awt.Graphics;
import java.awt.*;
import java.util.Random;

public  class Texto extends Figure{
    public String palavra, fonte;
    public int fundoR, fundoG, fundoB;
	public int tamanho, x, y, w, h;
    public int contornoR, contornoG, contornoB;
    Random rand = new Random();

	public void print () {
        System.out.format("Retangulo possui o tamanho de (%d,%d) na posicao (%d,%d).\n",
            this.palavra, this.fonte, this.tamanho);
	}


    public Texto (String palavra, String fonte, int x, int y, int w, int h, Color fundo, Color contorno){
        super(x, y, w, h, fundo, contorno);
    }

    
    //@Override
    public void paint (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font(fonte, Font.ITALIC, this.x));
        g2d.setColor(new Color (rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
		g2d.drawString("alo, teste",x,y);
        
    }
}
