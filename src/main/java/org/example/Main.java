package org.example;

public class Main {
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();
        //MOVIMIENTO A LA DERECHA
        /*
                20 (2)
              /
            12 (1)
          /
        5 (0)
        */
        System.out.println("MOVIMIENTO A LA DERECHA");
        arbol.insertar(20);
        arbol.insertar(12);
        arbol.insertar(5);
        arbol.mostrarArbolAVL();

        System.out.println("\n***********************************************************************");

        //MOVIMIENTO SIMPLE A LA IZQUIERDA
        System.out.println("\nMOVIMIENTO A LA IZQUIERDA");
        /*
                 10 (-2)
             /           \
            5 (0)       18 (-1)
                     /          \
                  15 (0)      20 (-1)
                                 \
                                25 (0)
        */
        arbol.clearAll();
        arbol.insertar(10);
        arbol.insertar(5);
        arbol.insertar(18);
        arbol.insertar(15);
        arbol.insertar(20);
        arbol.insertar(25);
        arbol.mostrarArbolAVL();
    }
}
