
package EstructuraDatos;

import CapaDatos.vhabitacion;

public class PilaAccionesHabitacion {
    private vhabitacion[] pila;
    private int top;
    private int capacidad;

    public PilaAccionesHabitacion(int capacidad) {
        this.capacidad = capacidad;
        this.pila = new vhabitacion[capacidad];
        this.top = -1;
    }

    public void push(vhabitacion hab) {
        if (!isFull()) {
            pila[++top] = hab;
        } else {
            System.out.println("La pila de acciones está llena.");
        }
    }

    public vhabitacion pop() {
        if (!isEmpty()) {
            return pila[top--];
        }
        return null;
    }

    public vhabitacion peek() {
        if (!isEmpty()) {
            return pila[top];
        }
        return null;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacidad - 1;
    }

    public void mostrar() {
        System.out.println("Contenido de la pila:");
        for (int i = top; i >= 0; i--) {
            System.out.println("[" + pila[i].getNumero() + "]");
        }
    }
}