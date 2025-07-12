
package EstructuraDatos;

import java.util.Stack;

public class PilaAcciones {
    private Stack<String> pila;

    public PilaAcciones() {
        pila = new Stack<>();
    }

    // Registrar una acción
    public void registrarAccion(String accion) {
        if (pila.size() >= 5) {
            pila.remove(0); // Elimina la acción más antigua
        }
        pila.push(accion);
    }

    // Revierte la última acción
    public String deshacerAccion() {
        if (!pila.isEmpty()) {
            return pila.pop();
        } else {
            return "No hay acciones para deshacer.";
        }
    }

    // Ver acciones registradas (para pruebas)
    public void mostrarAcciones() {
        System.out.println("Acciones en la pila:");
        for (String acc : pila) {
            System.out.println(acc);
        }
    }

    public boolean estaVacia() {
        return pila.isEmpty();
    }

    public int tamanio() {
        return pila.size();
    }
}
