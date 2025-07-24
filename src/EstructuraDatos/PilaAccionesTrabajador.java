
package EstructuraDatos;

import CapaDatos.vtrabajador;

public class PilaAccionesTrabajador {
   private vtrabajador[] pila;
    private int top;
    private int capacidad;

    public PilaAccionesTrabajador(int capacidad) {
        this.capacidad = capacidad;
        pila = new vtrabajador[capacidad];
        top = -1;
    } 
    
    public void push(vtrabajador trabajador) {
        if (!isFull()) {
            pila[++top] = trabajador;
        } else {
            System.out.println("La pila de trabajadores está llena.");
        }
    }

    public vtrabajador pop() {
        if (!isEmpty()) {
            return pila[top--];
        }
        return null;
    }

    public vtrabajador peek() {
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
        System.out.println("Contenido de la pila de trabajadores:");
        for (int i = top; i >= 0; i--) {
            System.out.println("[" + pila[i].getNombre() + " - DNI: " + pila[i].getNum_documento() + "]");
        }
    }
}
