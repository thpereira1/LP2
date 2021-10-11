#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int r,g,b;
} Color;

typedef struct Figure {
    int x, y;
    void (* print) (struct Figure*);
} Figure;


typedef struct {
    Figure super;
    int w, h;
} Rect;

void rect_print (Rect* this) {
    Figure* sup = (Figure*) this;
    printf("Retangulo possui o tamanho (%d,%d) na posicao (%d,%d).\n", this->w, this->h, sup->x, sup->y);
}
Rect* rect_new (int x, int y, int w, int h) {
    Rect*   this  = malloc(sizeof(Rect));
    Figure* sup = (Figure*) this;
    sup->print = (rect_print);
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
}

typedef struct {
    Figure super;
    int w, h;
} Ellipse;

void Ellipse_print (Ellipse* this) {
    Figure* sup = (Figure*) this;
    printf("Elipse possui o tamanho (%d,%d) na posicao (%d,%d).\n",
    this->w, this->h, sup->x, sup->y);
}

Ellipse* ellipse_new (int x, int y, int w, int h) {
    Ellipse* this = malloc(sizeof(Ellipse));
    Figure* sup = (Figure*) this;
    sup->print = (Ellipse_print);
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
}


typedef struct {
    Figure super;
    char tam_texto[100];
    char fonte[50];
    int tamanho;
} Texto;


void Texto_print(Texto* this) {
    Figure* super = (Figure*) this;
    printf("Texto com tamanho de: (%s), fonte: (%d) e posição: (%d, %d)", this->tam_texto, this->fonte, super->x, super->y);
}

Texto* texto_new (int x, int y, char tam_texto, char fonte, int tamanho) {
    Texto* this = malloc(sizeof(Texto));
    Figure* sup = (Figure*) this;
    sup->print = (Texto_print);
    sup->x = x;
    sup->y = y;
    this->tam_texto = tam_texto;
    this->fonte = fonte;
}

typedef struct {
    Figure super;
    int x3, y1, y2, y3;
} Triangle;

void Triangle_print(Triangle* this) {
    Figure* super = (Figure*) this;
    printf("Triangulo com os vertices: (%d, %d), (%d, %d) e (%d, %d)", super->x, super->y, this->x3, this->y1, this->y2,this->y3);
}

Triangle* triangle_new(int x, int y, int x3, int y1, int y2, int y3) {
    Triangle* this = malloc(sizeof(Triangle));
    Figure* sup = (Figure*) this;
    sup-> print = (Triangle_print);
    sup->x = x;
    sup->y = y;
    this->x3 = x3;
    this->y1 = y1;
    this->y2 = y2;
    this->y3 = y3;

}



int main(){
    Figure* figs[4] = {
	    (Figure*) rect_new(50,50,50,50);
            (Figure*) ellipse_new(50,5,90,100);
            (Figure*) texto_new(100,205,"hello, world","ITALIC",40);
            (Figure*) triangle_new(100,200,150,120,130,120);
    };

    for(int i=0; i<4; i++) {
            figs[i]->print(figs[i]);
    }

    for(int i=0; i<4; i++) {
            free(figs[i]);
    }


}
