
package EstructuraDatos;

import CapaDatos.vproducto;

class NodoProducto {
    vproducto producto;
    NodoProducto izquierda, derecha;

    public NodoProducto(vproducto producto) {
        this.producto = producto;
    }
}

public class ArbolBinarioProducto {
    private NodoProducto raiz;

    public void insertar(vproducto producto) {
        raiz = insertarRec(raiz, producto);
    }

    private NodoProducto insertarRec(NodoProducto nodo, vproducto producto) {
        if (nodo == null) return new NodoProducto(producto);
        if (producto.getNombre().compareToIgnoreCase(nodo.producto.getNombre()) < 0)
            nodo.izquierda = insertarRec(nodo.izquierda, producto);
        else
            nodo.derecha = insertarRec(nodo.derecha, producto);
        return nodo;
    }
    

    public vproducto buscar(String nombre) {
        NodoProducto actual = raiz;
        while (actual != null) {
            int cmp = nombre.compareToIgnoreCase(actual.producto.getNombre());
            if (cmp == 0) return actual.producto;
            actual = (cmp < 0) ? actual.izquierda : actual.derecha;
        }
        return null;
    }
    
    

    public void inOrden() {
        System.out.print("Inorden: ");
        inOrdenRec(raiz);
        System.out.println();
    }

    private void inOrdenRec(NodoProducto nodo) {
        if (nodo != null) {
            inOrdenRec(nodo.izquierda);
            System.out.print(nodo.producto.getNombre() + " ");
            inOrdenRec(nodo.derecha);
        }
    }
    
    public void limpiar() {
        raiz = null;
    }
}
