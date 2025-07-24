
package EstructuraDatos;

import CapaDatos.vtrabajador;

class NodoTrabajador {
    vtrabajador trabajador;
    NodoTrabajador siguiente;

    public NodoTrabajador(vtrabajador trabajador) {
        this.trabajador = trabajador;
    }
}

public class ListaEnlazadaTrabajador {
    private NodoTrabajador cabeza;

    public ListaEnlazadaTrabajador() {
        cabeza = null;
    }

    public void insertarAlFinal(vtrabajador trabajador) {
        NodoTrabajador nuevo = new NodoTrabajador(trabajador);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoTrabajador actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }
    
     public boolean eliminar(String num_documento) {
        if (cabeza == null) return false;

        if (cabeza.trabajador.getNum_documento().equals(num_documento)) {
            cabeza = cabeza.siguiente;
            return true;
        }

        NodoTrabajador actual = cabeza;
        while (actual.siguiente != null &&
               !actual.siguiente.trabajador.getNum_documento().equals(num_documento)) {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
            return true;
        }

        return false;
    }
    
     public void mostrar() {
        NodoTrabajador actual = cabeza;
        while (actual != null) {
            System.out.println("Trabajador: " + actual.trabajador.getNombre() +
                               ", Documento: " + actual.trabajador.getNum_documento());
            actual = actual.siguiente;
        }
    }
    
    public void limpiar() {
        cabeza = null;
    }
    
}
