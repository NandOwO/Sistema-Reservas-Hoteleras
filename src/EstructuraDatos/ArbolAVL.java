
package EstructuraDatos;

import CapaDatos.vreserva;

class NodoAVL<K extends Comparable<K>, T> {
    K clave;
    T valor;
    NodoAVL<K, T> izquierda;
    NodoAVL<K, T> derecha;
    int altura;

    public NodoAVL(K clave, T valor) {
        this.clave = clave;
        this.valor = valor;
        this.altura = 1;
    }
}

// Clase principal del Árbol AVL
public class ArbolAVL<K extends Comparable<K>, T> {
    public NodoAVL<K, T> raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    private int altura(NodoAVL<K, T> nodo) {
        return (nodo == null) ? 0 : nodo.altura;
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private int obtenerFactorBalance(NodoAVL<K, T> nodo) {
        return (nodo == null) ? 0 : altura(nodo.izquierda) - altura(nodo.derecha);
    }

    private NodoAVL<K, T> rotarDerecha(NodoAVL<K, T> y) {
        NodoAVL<K, T> x = y.izquierda;
        NodoAVL<K, T> T2 = x.derecha;

        x.derecha = y;
        y.izquierda = T2;


        y.altura = max(altura(y.izquierda), altura(y.derecha)) + 1;
        x.altura = max(altura(x.izquierda), altura(x.derecha)) + 1;

        return x;
    }


    private NodoAVL<K, T> rotarIzquierda(NodoAVL<K, T> x) {
        NodoAVL<K, T> y = x.derecha;
        NodoAVL<K, T> T2 = y.izquierda;

        y.izquierda = x;
        x.derecha = T2;

        x.altura = max(altura(x.izquierda), altura(x.derecha)) + 1;
        y.altura = max(altura(y.izquierda), altura(y.derecha)) + 1;

        return y;
    }


    public void insertar(K clave, T valor) {
        raiz = insertar(raiz, clave, valor);
    }


    private NodoAVL<K, T> insertar(NodoAVL<K, T> nodo, K clave, T valor) {

        if (nodo == null) {
            return new NodoAVL<>(clave, valor);
        }

        int comparacion = clave.compareTo(nodo.clave);

        if (comparacion < 0) {
            nodo.izquierda = insertar(nodo.izquierda, clave, valor);
        } else if (comparacion > 0) {
            nodo.derecha = insertar(nodo.derecha, clave, valor);
        } else {
            nodo.valor = valor; 
            return nodo;
        }

        nodo.altura = 1 + max(altura(nodo.izquierda), altura(nodo.derecha));

        int factorBalance = obtenerFactorBalance(nodo);

        if (factorBalance > 1 && clave.compareTo(nodo.izquierda.clave) < 0) {
            return rotarDerecha(nodo);
        }

        if (factorBalance < -1 && clave.compareTo(nodo.derecha.clave) > 0) {
            return rotarIzquierda(nodo);
        }

        if (factorBalance > 1 && clave.compareTo(nodo.izquierda.clave) > 0) {
            nodo.izquierda = rotarIzquierda(nodo.izquierda);
            return rotarDerecha(nodo);
        }

        if (factorBalance < -1 && clave.compareTo(nodo.derecha.clave) < 0) {
            nodo.derecha = rotarDerecha(nodo.derecha);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    public T buscar(K clave) {
        NodoAVL<K, T> resultado = buscar(raiz, clave);
        return (resultado == null) ? null : resultado.valor;
    }

    private NodoAVL<K, T> buscar(NodoAVL<K, T> nodo, K clave) {
        if (nodo == null || clave.compareTo(nodo.clave) == 0) {
            return nodo;
        }

        if (clave.compareTo(nodo.clave) < 0) {
            return buscar(nodo.izquierda, clave);
        } 
        else {
            return buscar(nodo.derecha, clave);
        }
    }
       
    public void eliminar(K clave) {
        raiz = eliminar(raiz, clave);
    }

    private NodoAVL<K, T> eliminar(NodoAVL<K, T> nodo, K clave) {
        if (nodo == null) {
            return nodo;
        }

        int comparacion = clave.compareTo(nodo.clave);

        if (comparacion < 0) {
            nodo.izquierda = eliminar(nodo.izquierda, clave);
        } else if (comparacion > 0) {
            nodo.derecha = eliminar(nodo.derecha, clave);
        } else {
            if ((nodo.izquierda == null) || (nodo.derecha == null)) {
                NodoAVL<K, T> temp = null;
                if (temp == nodo.izquierda) {
                    temp = nodo.derecha;
                } else {
                    temp = nodo.izquierda;
                }
                if (temp == null) {
                    nodo = null;
                } else {
                    nodo = temp;
                }
            } else {
                NodoAVL<K, T> temp = minValorNodo(nodo.derecha);
                nodo.clave = temp.clave;
                nodo.valor = temp.valor;
                nodo.derecha = eliminar(nodo.derecha, temp.clave);
            }
        }
        if (nodo == null) {
            return nodo;
        }
        nodo.altura = 1 + max(altura(nodo.izquierda), altura(nodo.derecha));

        int factorBalance = obtenerFactorBalance(nodo);

        if (factorBalance > 1 && obtenerFactorBalance(nodo.izquierda) >= 0) {
            return rotarDerecha(nodo);
        }

        if (factorBalance > 1 && obtenerFactorBalance(nodo.izquierda) < 0) {
            nodo.izquierda = rotarIzquierda(nodo.izquierda);
            return rotarDerecha(nodo);
        }

        if (factorBalance < -1 && obtenerFactorBalance(nodo.derecha) <= 0) {
            return rotarIzquierda(nodo);
        }

        if (factorBalance < -1 && obtenerFactorBalance(nodo.derecha) > 0) {
            nodo.derecha = rotarDerecha(nodo.derecha);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    private NodoAVL<K, T> minValorNodo(NodoAVL<K, T> nodo) {
        NodoAVL<K, T> actual = nodo;
        while (actual.izquierda != null) {
            actual = actual.izquierda;
        }
        return actual;
    }

    public void inorden() {
        System.out.print("Recorrido In-orden (Claves): ");
        inorden(raiz);
        System.out.println();
    }

    private void inorden(NodoAVL<K, T> nodo) {
        if (nodo != null) {
            inorden(nodo.izquierda);
            System.out.print(nodo.clave + "(" + nodo.altura + ") ");
            inorden(nodo.derecha);
        }
    }

    public void preorden() {
        System.out.print("Recorrido Pre-orden (Claves): ");
        preorden(raiz);
        System.out.println();
    }

    private void preorden(NodoAVL<K, T> nodo) {
        if (nodo != null) {
            System.out.print(nodo.clave + "(" + nodo.altura + ") ");
            preorden(nodo.izquierda);
            preorden(nodo.derecha);
        }
    }
}
