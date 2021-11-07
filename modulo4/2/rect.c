#include <stdio.h>
#include <stdlib.h>
#include "rect.h"

typedef struct Rect {
	int x, y;
	int w, h;
} Rect;

Rect* rect_new (void) {
	Rect* this = malloc(sizeof(Rect));
	this->x = 1;
	this->y = 0;
	this->w = 35;
	this->h = 10;
}

void rect_drag (Rect* this, int dx, int dy) {
	this->x += dx;
	this->y += dy;
}

void rect_print (Rect* this) {
	printf("Retangulo possui o tamanho de (%d,%d) na posicao (%d,%d).\n", this->w, this->h, this->x, this->y);
}
