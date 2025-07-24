
package CapaLogica;

import CapaDatos.vhabitacion;
import EstructuraDatos.ListaEnlazadaHabitaciones;
import EstructuraDatos.ListaHistorial;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import EstructuraDatos.PilaAccionesHabitacion;



public class fhabitacion {
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;
    
     //integracion de la estructura de datos
    private static ListaHistorial historial = new ListaHistorial(); 
    private static ListaEnlazadaHabitaciones listaHabitaciones = new ListaEnlazadaHabitaciones();
    public static EstructuraDatos.PilaAccionesHabitacion pila = new EstructuraDatos.PilaAccionesHabitacion(10);

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Número", "Piso", "Descripción", "Características", "Precio Diario", "Estado", "Tipo"};
        String[] registro = new String[8];
        totalregistros = 0;

        modelo = new DefaultTableModel(null, titulos);
        
        // Limpiar lista enlazada antes de llenar
        listaHabitaciones = new ListaEnlazadaHabitaciones();

        sSQL = "SELECT * FROM habitacion WHERE piso LIKE '%" + buscar + "%' ORDER BY idhabitacion";
        
     

        try {
            Statement st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idhabitacion");
                registro[1] = rs.getString("numero");
                registro[2] = rs.getString("piso");
                registro[3] = rs.getString("descripcion");
                registro[4] = rs.getString("caracteristicas");
                registro[5] = rs.getString("precio_diario");
                registro[6] = rs.getString("estado");
                registro[7] = rs.getString("tipo_habitacion");
                
                modelo.addRow(registro);
                totalregistros++;
                
               // Guardar en lista enlazada
                vhabitacion hab = new vhabitacion();
                hab.setIdhabitacion(Integer.parseInt(rs.getString("idhabitacion")));
                hab.setNumero(rs.getString("numero"));
                hab.setPiso(rs.getString("piso"));
                hab.setDescripcion(rs.getString("descripcion"));
                hab.setCaracteristicas(rs.getString("caracteristicas"));
                hab.setPrecio_diario(rs.getString("precio_diario"));
                hab.setEstado(rs.getString("estado"));
                hab.setTipo_habitacion(rs.getString("tipo_habitacion"));
                listaHabitaciones.insertarAlFinal(hab);
            }

            return modelo;

        } catch (Exception e) {
            return null;
        }
         
        
    }
    
    public boolean insertar(vhabitacion dts) {
        sSQL = "INSERT INTO habitacion (numero, piso, descripcion, caracteristicas, precio_diario, estado, tipo_habitacion) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getPiso());
            pst.setString(3, dts.getDescripcion());
            pst.setString(4, dts.getCaracteristicas());
            pst.setString(5, dts.getPrecio_diario());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getTipo_habitacion());

            
            if (pst.executeUpdate() != 0) {
                historial.agregarAccion("Insertó habitación: " + dts.getNumero());
                pila.push(dts);
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public boolean editar(vhabitacion dts) {
        sSQL = "UPDATE habitacion SET numero=?, piso=?, descripcion=?, caracteristicas=?, precio_diario=?, estado=?, tipo_habitacion=? WHERE idhabitacion=?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getPiso());
            pst.setString(3, dts.getDescripcion());
            pst.setString(4, dts.getCaracteristicas());
            pst.setString(5, dts.getPrecio_diario());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getTipo_habitacion());
            pst.setInt(8, dts.getIdhabitacion());

            if (pst.executeUpdate() != 0) {
                historial.agregarAccion("Editó habitación: " + dts.getNumero());
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(vhabitacion dts) {
        sSQL = "DELETE FROM habitacion WHERE idhabitacion=?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdhabitacion());

            if (pst.executeUpdate() != 0) {
                historial.agregarAccion("Eliminó habitación: " + dts.getNumero());
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean eliminarPorNumero(String numero) {
        sSQL = "DELETE FROM habitacion WHERE numero=?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, numero);

            if (pst.executeUpdate() != 0) {
                historial.agregarAccion("Deshizo inserción de habitación: " + numero);
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    
    public void mostrarHistorial() {
        historial.mostrarDesdeInicio();
    }
    
    public void mostrarDesdeLista() {
        listaHabitaciones.mostrar();
    }
    
    public String obtenerHistorialTexto() {
        return historial.obtenerHistorialComoTexto();
    }
    
    public String deshacerUltimaAccion() {
        if (!pila.isEmpty()) {
            vhabitacion ultima = pila.pop();
            if (ultima != null && eliminarPorNumero(ultima.getNumero())) {
                return "Se deshizo la última inserción (habitacion nro: " + ultima.getNumero() + ")";
            } else {
                return "No se pudo deshacer la acción.";
            }
        } else {
            return "No hay acciones para deshacer.";
        }
    }

}
