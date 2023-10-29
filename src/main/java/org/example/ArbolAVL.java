package org.example;

public class ArbolAVL {
    NodoAVL root;
    public void clearAll() {
        root = null;
    }
    //INSERCION
    public void insertar(int key) {
        root = insertarAVL(root, key);
    }
    private NodoAVL insertarAVL(NodoAVL nodoActual, int key) {
        if (nodoActual == null) {
            return (new NodoAVL(key));
        }
        if (key < nodoActual.key) {
            nodoActual.left = insertarAVL(nodoActual.left, key);
        } else if (key > nodoActual.key) {
            nodoActual.right = insertarAVL(nodoActual.right, key);
        } else {return nodoActual;}
        //ACTUALIZACION DE ALTURA
        nodoActual.altura = 1 +
                max(getAltura(nodoActual.left), getAltura(nodoActual.right));
        // EQUILIBRIO
        int fe = getFactorEquilibrio(nodoActual);
        // MOVIMIENTO A LA DERECHA
        if (fe > 1 && key < nodoActual.left.key) {
            return rightRotate(nodoActual);
        }
        // MOVIMIENTO A LA IZQUIERDA
        if (fe < -1 && key > nodoActual.right.key) {
            return leftRotate(nodoActual);
        }
        return nodoActual;
    }
    //BUSQUEDA
    public void buscar(int elemento) {
        if (BuscaEnAVL(root, elemento)) {
            System.out.println("Existe");
        } else {
            System.out.println("No Existe");
        }
    }
    private boolean BuscaEnAVL(NodoAVL nodoActual, int elemento) {
        if (nodoActual == null) {
            return false;
        } else if (elemento == nodoActual.key) {
            return true;
        } else if (elemento < nodoActual.key) {
            return BuscaEnAVL(nodoActual.left, elemento);
        } else {
            return BuscaEnAVL(nodoActual.right, elemento);
        }
    }

    //ELIMINACION
    public void eliminar(int key) {
        root = eliminarAVL(root, key);
    }
    private NodoAVL eliminarAVL(NodoAVL nodoActual, int key) {
        if (nodoActual == null)
            return nodoActual;
        if (key < nodoActual.key) {
            nodoActual.left = eliminarAVL(nodoActual.left, key);
        } else if (key > nodoActual.key) {
            nodoActual.right = eliminarAVL(nodoActual.right, key);
        } else {
            if ((nodoActual.left == null) || (nodoActual.right == null)) {
                NodoAVL temp = null;
                if (temp == nodoActual.left) {
                    temp = nodoActual.right;
                } else {
                    temp = nodoActual.left;
                }
            } else {
                NodoAVL temp = getNodoConValorMaximo(nodoActual.left);
                nodoActual.key = temp.key;
                nodoActual.left = eliminarAVL(nodoActual.left, temp.key);
            }
        }
        //ACTUALIZA LA ALTURA
        nodoActual.altura = max(getAltura(nodoActual.left), getAltura(nodoActual.right)) + 1;
        int fe = getFactorEquilibrio(nodoActual);
        if (fe > 1 && getFactorEquilibrio(nodoActual.left) >= 0) {
            return rightRotate(nodoActual);
        }
        if (fe < -1 && getFactorEquilibrio(nodoActual.right) <= 0) {
            return leftRotate(nodoActual);
        }
        return nodoActual;
    }
   //MOVIMIENTOS
    private NodoAVL rightRotate(NodoAVL nodoActual) {
        NodoAVL nuevaRaiz = nodoActual.left;
        NodoAVL temp = nuevaRaiz.right;
        nuevaRaiz.right = nodoActual;
        nodoActual.left = temp;
        nodoActual.altura = max(getAltura(nodoActual.left), getAltura(nodoActual.right)) + 1;
        nuevaRaiz.altura = max(getAltura(nuevaRaiz.left), getAltura(nuevaRaiz.right)) + 1;
        return nuevaRaiz;
    }
    // MOVER A LA IZQUIERDA
    private NodoAVL leftRotate(NodoAVL nodoActual) {
        NodoAVL nuevaRaiz = nodoActual.right;
        NodoAVL temp = nuevaRaiz.left;
        nuevaRaiz.left = nodoActual;
        nodoActual.right = temp;
        nodoActual.altura = max(getAltura(nodoActual.left), getAltura(nodoActual.right)) + 1;
        nuevaRaiz.altura = max(getAltura(nuevaRaiz.left), getAltura(nuevaRaiz.right)) + 1;
        return nuevaRaiz;
    }
  //MOSTRAR EL ARBOL
    public void mostrarArbolAVL() {
        System.out.println("Arbol AVL");
        showTree(root, 0);
    }
    private void showTree(NodoAVL nodo, int depth) {
        if (nodo.right != null) {
            showTree(nodo.right, depth + 1);
        }
        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }
        System.out.println("(" + nodo.key + ")");

        if (nodo.left != null) {
            showTree(nodo.left, depth + 1);
        }
    }
    //Obtiene la altura
    private int getAltura(NodoAVL nodoActual) {
        if (nodoActual == null) {
            return 0;
        }
        return nodoActual.altura;
    }
    // Devuelve el mayor entre dos numeros
    private int max(int a, int b) {
        return (a > b) ? a : b;
    }
    // Factor de equilibrio de un nodo
    private int getFactorEquilibrio(NodoAVL nodoActual) {
        if (nodoActual == null) {
            return 0;
        }
        return getAltura(nodoActual.left) - getAltura(nodoActual.right);
    }
    private NodoAVL getNodoConValorMaximo(NodoAVL node) {
        NodoAVL current = node;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }
}
