
package EstructuraDatos;

import CapaDatos.vcliente;

public class PilaAccionesCliente {
    private vcliente[] pila;
    private int top;
    private int capacidad;

    public PilaAccionesCliente(int capacidad) {
        this.capacidad = capacidad;
        pila = new vcliente[capacidad];
        top = -1;
    }

    public void push(vcliente cliente) {
        if (!isFull()) {
            pila[++top] = cliente;
        } else {
            System.out.println("La pila de acciones está llena.");
        }
    }
    
     public vcliente pop() {
        if (!isEmpty()) {
            return pila[top--];
        }
        return null;
    }

    public vcliente peek() {
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
        System.out.println("Contenido de la pila de clientes:");
        for (int i = top; i >= 0; i--) {
            System.out.println("[" + pila[i].getCodigo_cliente() + " - " + pila[i].getNombre() + "]");
        }
    }
    
}
