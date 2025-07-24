
package EstructuraDatos;

import CapaDatos.vproducto;

public class ArregloProducto {
    private vproducto[] productos;
    private int tamaño;
    private int total;

    public ArregloProducto(int tamaño) {
        this.tamaño = tamaño;
        this.productos = new vproducto[tamaño];
        this.total = 0;
    }
    
    public boolean agregar(vproducto producto) {
        if (total < tamaño) {
            productos[total++] = producto;
            return true;
        }
        return false;
    }

    public boolean eliminar(String nombre) {
        for (int i = 0; i < total; i++) {
            if (productos[i].getNombre().equalsIgnoreCase(nombre)) {
                for (int j = i; j < total - 1; j++) {
                    productos[j] = productos[j + 1];
                }
                productos[--total] = null;
                return true;
            }
        }
        return false;
    }
    
    public vproducto buscar(String nombre) {
        for (int i = 0; i < total; i++) {
            if (productos[i].getNombre().equalsIgnoreCase(nombre)) {
                return productos[i];
            }
        }
        return null;
    }
    
    public vproducto buscarPorIndice(int indice) {
        if (indice >= 0 && indice < total) {
            return productos[indice];
        }
        return null;
    }

    public void mostrar() {
        for (int i = 0; i < total; i++) {
            System.out.println(productos[i]);
        }
    }

    public int getTotal() {
        return total;
    }

    public void limpiar() {
        for (int i = 0; i < total; i++) {
            productos[i] = null;
        }
        total = 0;
    }

}
