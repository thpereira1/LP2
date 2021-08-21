public class Poligono {
    public static void main (String[] args) {
       Polygon p1= new Polygon(50,30,5,100,100);
        p1.print();
    }
}

class Polygon {
    int posx;
    int posy;
    int sides;
    int h;
    int w;

    Polygon (int posx, int posy, int sides, int h, int w) {
        this.posx = posx;
        this.posy = posy;
        this.sides = sides;
        this.h = h;
        this.w = w;
    }

    void print() {
        System.out.format ("O Poligono possui %d lados com o tamanho de (%d,%d) e a posicao de (%d,%d).\n ", this.sides, this.h, this.w, this.posx, this.posy);
    }

}
