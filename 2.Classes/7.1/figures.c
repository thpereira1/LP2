#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int r,g,b;
} Color;

struct Figure;
typedef void (* Figure_Print) (struct Figure*);

typedef struct Figure {
    int x, y;
    Color fg, bg;
    void (* print) (struct Figure*);
} Figure;



typedef struct {
    Figure super;
    int width, height;
} Rect;

void rect_print (Rect* this) {
    Figure* sup = (Figure*) this;
    printf("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
           this->width, this->height, sup->x, sup->y);
}

Rect* rect_new (int x, int y, int width, int height) {
    Rect*   this  = malloc(sizeof(Rect));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) rect_print;
    sup->x = x;
    sup->y = y;
    this->width = width;
    this->height = height;
}



typedef struct {
    Figure super;
    int width, height;
} Ellipse;

void Ellipse_print (Ellipse* this) {
    Figure* sup = (Figure*) this;
    printf("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
           this->width, this->height, sup->x, sup->y);
}

Ellipse* ellipse_new (int x, int y, int width, int height) {
    Ellipse* this = malloc(sizeof(Ellipse));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) Ellipse_print;
    sup->x = x;
    sup->y = y;
    this->width = width;
    this->height = height;
}



void main (void) {
    Figure* figs[4] = {
        (Figure*) rect_new(20,20,110,110),
        (Figure*) ellipse_new(50,20,170,330),
        (Figure*) rect_new(20,20,110,110),
        (Figure*) ellipse_new(200,100,295,120)
    };

    ///

    for (int i=0; i<4; i++) {
        figs[i]->print(figs[i]);
    }

    ///

    for (int i=0; i<4; i++) {
        free(figs[i]);
    }
}
