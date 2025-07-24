
package EstructuraDatos;

import CapaDatos.vhabitacion;


class Nodo {
    vhabitacion habitacion;
    Nodo siguiente;

    public Nodo(vhabitacion habitacion) {
        this.habitacion = habitacion;
        this.siguiente = null;
    }
}
public class ListaEnlazadaHabitaciones {
    
    private Nodo cabeza;

    public ListaEnlazadaHabitaciones() {
        cabeza = null;
    }

    // Insertar al final
    public void insertarAlFinal(vhabitacion habitacion) {
        Nodo nuevo = new Nodo(habitacion);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    // Elimina por número de habitación
    public boolean eliminar(String numero) {
        if (cabeza == null) return false; // si la lista esta vacia, no se elimina

        if (cabeza.habitacion.getNumero().equals(numero)) {
            cabeza = cabeza.siguiente;//apunta al siguiente
            return true;
        }

        Nodo actual = cabeza;
        while (actual.siguiente != null && !actual.siguiente.habitacion.getNumero().equals(numero)) {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente; //apunta directamente al siguiente
            return true;
        }

        return false;
    }

    // Muestra todas las habitaciones 
    public void mostrar() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println("Habitación: " + actual.habitacion.getNumero());
            actual = actual.siguiente;
        }
    }
    
    
     public void limpiar() {
       cabeza = null;  // Elimina todos los nodos
    }
}

