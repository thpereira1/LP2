#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int r,g,b;
} Color;

struct Figure;
typedef void (* Figure_Print) (struct Figure*);
typedef int  (* Figure_Area)  (struct Figure*);

typedef struct {
    void (* print) (struct Figure*);
    int  (* area)  (struct Figure*);
} Figure_vtable;

typedef struct Figure {
    int x, y;
    Color fg, bg;
    Figure_vtable* vtable;
} Figure;

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int width, height;
} Rect;

void rect_print (Rect* this) {
    Figure* sup = (Figure*) this;
    printf("Retangulo de tamanho (%d,%d) na posicao (%d,%d) e area %d.\n",
           this->width, this->height, sup->x, sup->y, sup->vtable->area(sup));
}

int rect_area (Rect* this) {
    Figure* sup = (Figure*) this;
    return this->width * this->height;
}

Figure_vtable rect_vtable = {
    (Figure_Print) rect_print,
    (Figure_Area)  rect_area
};

Rect* rect_new (int x, int y, int width, int height) {
    Rect*   this  = malloc(sizeof(Rect));
    Figure* sup = (Figure*) this;
    sup->vtable = &rect_vtable;
    sup->x = x;
    sup->y = y;
    this->width = width;
    this->height = height;
}

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int width, height;
} Ellipse;

void ellipse_print (Rect* this) {
    Figure* sup = (Figure*) this;
    printf("Elipse de tamanho (%d,%d) na posicao (%d,%d) e area %d.\n",
           this->width, this->height, sup->x, sup->y, sup->vtable->area(sup));
}

int ellipse_area (Rect* this) {
    Figure* sup = (Figure*) this;
    return this->width * this->height;
}

Figure_vtable ellipse_vtable = {
    (Figure_Print) ellipse_print,
    (Figure_Area)  ellipse_area
};

Ellipse* ellipse_new (int x, int y, int width, int height) {
    Ellipse* this = malloc(sizeof(Ellipse));
    Figure* sup = (Figure*) this;
    sup->vtable = &ellipse_vtable;
    sup->x = x;
    sup->y = y;
    this->width = width;
    this->height = height;
}

///////////////////////////////////////////////////////////////////////////////

void main (void) {
    Figure* figs[4] = {
        (Figure*) rect_new(30,30,300,300),
        (Figure*) ellipse_new(40,10,140,300),
        (Figure*) rect_new(40,40,400,400),
        (Figure*) ellipse_new(210,110,305,130)
    };

    ///

    for (int i=0; i<4; i++) {
        figs[i]->vtable->print(figs[i]);
    }

    ///

    for (int i=0; i<4; i++) {
        free(figs[i]);
    }
}
