#include <stdio.h>
#include <math.h>

typedef struct {
  float f1x, f1y, df2x, df2y;
  float angulation;
  float size;
} elipse;


void print_elipse(elipse* p) {
    printf("A Elipse possui %f de tamanho, raios nas posicoes (%f,%f) e (%f,%f) e a rotacao da mesma é de %.1f graus.\n", p->size, p->f1x, p->f1y, p->df2x, p->df2y, p->angulation);
}

int main (void) {
    elipse p1 = {2,3,5,2,5,90};
    print_elipse(&p1);
    return 0;
}
