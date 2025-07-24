
package EstructuraDatos;

import CapaDatos.vcliente;

class NodoCliente {
    vcliente cliente;
    NodoCliente siguiente;

    public NodoCliente(vcliente cliente) {
        this.cliente = cliente;
        this.siguiente = null;
    }
}
public class ListaEnlazadaCliente {
    private NodoCliente cabeza;
    
    public ListaEnlazadaCliente() {
        cabeza = null;
    }
    
    public void insertarAlFinal(vcliente cliente) {
        NodoCliente nuevo = new NodoCliente(cliente);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoCliente actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }
    
    public boolean eliminarPorCodigo(String codigo) {
        if (cabeza == null) return false;

        if (cabeza.cliente.getCodigo_cliente().equals(codigo)) {
            cabeza = cabeza.siguiente;
            return true;
        }

        NodoCliente actual = cabeza;
        while (actual.siguiente != null && !actual.siguiente.cliente.getCodigo_cliente().equals(codigo)) {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
            return true;
        }

        return false;
    }
    
    public void mostrar() {
        NodoCliente actual = cabeza;
        while (actual != null) {
            System.out.println("Cliente: " + actual.cliente.getCodigo_cliente() + " - " + actual.cliente.getNombre());
            actual = actual.siguiente;
        }
    }

    public void limpiar() {
        cabeza = null;
    }

}
