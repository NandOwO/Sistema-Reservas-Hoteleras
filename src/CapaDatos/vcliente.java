
package CapaDatos;

public class vcliente {
    private int idCliente; 
    private String nombre; 
    private String dni;
    private String telefono; 
    
    public vcliente(){
    }
    
    public vcliente(int idcliente, String nombre, String dni, String telefono){
        this.idCliente = idcliente; 
        this.nombre = nombre;
        this.dni = dni; 
        this.telefono = telefono; 
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getDni() {
        return dni;
    }


    public void setDni(String dni) {
        this.dni = dni;
    }


    public String getTelefono() {
        return telefono;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
