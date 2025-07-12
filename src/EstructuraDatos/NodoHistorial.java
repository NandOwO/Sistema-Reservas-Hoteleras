
package EstructuraDatos;


class NodoHistorial {
   
    public String accion;
    public NodoHistorial siguiente;

    public NodoHistorial(String accion) {
        this.accion = accion;
        this.siguiente = null;
    }
}
