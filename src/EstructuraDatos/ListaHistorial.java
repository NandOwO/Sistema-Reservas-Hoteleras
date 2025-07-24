
package EstructuraDatos;

class NodoHistorial {
   
    public String accion;
    public NodoHistorial siguiente;

    public NodoHistorial(String accion) {
        this.accion = accion;
        this.siguiente = null;
    }
}

public class ListaHistorial {
    private NodoHistorial cabeza;

    public ListaHistorial() {
        this.cabeza = null;
    }

    public void agregarAccion(String accion) {
        NodoHistorial nuevo = new NodoHistorial(accion);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoHistorial temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
    }

    public void mostrarDesdeInicio() {
        NodoHistorial temp = cabeza;
        System.out.println("Historial de acciones:");
        while (temp != null) {
            System.out.println("- " + temp.accion);
            temp = temp.siguiente;
        }
    }
    
    public String obtenerHistorialComoTexto() {
        NodoHistorial actual = cabeza;
        StringBuilder historialTexto = new StringBuilder(); //se acumula las acciones 

        while (actual != null) {
            historialTexto.append(actual.accion).append("\n");
            actual = actual.siguiente;
        }

        return historialTexto.toString();
    }
}

