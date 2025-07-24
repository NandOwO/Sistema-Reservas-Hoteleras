
package CapaLogica;

import CapaDatos.vcliente;
import EstructuraDatos.ListaEnlazadaCliente;
import EstructuraDatos.ListaEnlazadaHabitaciones;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class fcliente {
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;

    private static ListaEnlazadaCliente listaClientes = new ListaEnlazadaCliente();
    public static EstructuraDatos.PilaAccionesCliente pila = new EstructuraDatos.PilaAccionesCliente(10);

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
        String[] titulos = {"ID", "Codigo","Nombre", "Apaterno", "Amaterno", "Numero documento", "Direccion", "Telefono", "Email"};
        String[] registro = new String[9];
        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        listaClientes = new ListaEnlazadaCliente();

        sSQL = "SELECT p.idpersona, p.nombre, p.apaterno, p.amaterno, p.num_documento, "
                + "p.direccion, p.telefono, p.email, c.codigo_cliente "
                + "FROM persona p INNER JOIN cliente c ON p.idpersona = c.idpersona "
                + "WHERE p.num_documento LIKE '%" + buscar + "%' ORDER BY p.idpersona DESC";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("codigo_cliente");
                registro[2] = rs.getString("nombre");
                registro[3] = rs.getString("apaterno");
                registro[4] = rs.getString("amaterno");
                registro[5] = rs.getString("num_documento");
                registro[6] = rs.getString("direccion");
                registro[7] = rs.getString("telefono");
                registro[8] = rs.getString("email");

                modelo.addRow(registro);
                totalregistros++;

                vcliente cli = new vcliente();
                cli.setIdpersona(Integer.parseInt(rs.getString("idpersona")));
                cli.setCodigo_cliente(rs.getString("codigo_cliente"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApaterno(rs.getString("apaterno"));
                cli.setAmaterno(rs.getString("amaterno"));
                cli.setNum_documento(rs.getString("num_documento"));
                cli.setDireccion(rs.getString("direccion"));
                cli.setTelefono(rs.getString("telefono"));
                cli.setEmail(rs.getString("email"));
                listaClientes.insertarAlFinal(cli);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los datos: " + e.getMessage());
            e.printStackTrace();
            return new DefaultTableModel(null, titulos);
        }
    }
    
    public boolean insertar(vcliente dts) {
        String sSQL1 = "INSERT INTO persona (nombre, apaterno, amaterno, num_documento, direccion, telefono, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sSQL2 = "INSERT INTO cliente (idpersona, codigo_cliente) VALUES (?, ?)";

        try {
            // Insertar en persona
            PreparedStatement pst1 = cn.prepareStatement(sSQL1, Statement.RETURN_GENERATED_KEYS);
            pst1.setString(1, dts.getNombre());
            pst1.setString(2, dts.getApaterno());
            pst1.setString(3, dts.getAmaterno());
            pst1.setString(4, dts.getNum_documento());
            pst1.setString(5, dts.getDireccion());
            pst1.setString(6, dts.getTelefono());
            pst1.setString(7, dts.getEmail());
            int n1 = pst1.executeUpdate();

            ResultSet rs = pst1.getGeneratedKeys();
            int idpersonaGenerada = -1;
            if (rs.next()) {
                idpersonaGenerada = rs.getInt(1);
            }

            if (idpersonaGenerada == -1) {
                throw new Exception("No se pudo obtener el ID generado de persona");
            }

            // Insertar en cliente
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            pst2.setInt(1, idpersonaGenerada);
            pst2.setString(2, dts.getCodigo_cliente());
            int n2 = pst2.executeUpdate();

            dts.setIdpersona(idpersonaGenerada);
            pila.push(dts);
            return (n1 > 0 && n2 > 0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean editar(vcliente dts) {
        String sSQL1 = "UPDATE persona SET nombre=?, apaterno=?, amaterno=?, num_documento=?, direccion=?, telefono=?, email=? WHERE idpersona=?";
        String sSQL2 = "UPDATE cliente SET codigo_cliente=? WHERE idpersona=?";

        try {
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            pst1.setString(1, dts.getNombre());
            pst1.setString(2, dts.getApaterno());
            pst1.setString(3, dts.getAmaterno());
            pst1.setString(4, dts.getNum_documento());
            pst1.setString(5, dts.getDireccion());
            pst1.setString(6, dts.getTelefono());
            pst1.setString(7, dts.getEmail());
            pst1.setInt(8, dts.getIdpersona());
            int n1 = pst1.executeUpdate();

            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            pst2.setString(1, dts.getCodigo_cliente());
            pst2.setInt(2, dts.getIdpersona());
            int n2 = pst2.executeUpdate();

            return (n1 > 0 && n2 > 0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al editar cliente: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminar(int idpersona) {
        String sSQL1 = "DELETE FROM cliente WHERE idpersona = ?";
        String sSQL2 = "DELETE FROM persona WHERE idpersona = ?";

        try {
            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            pst1.setInt(1, idpersona);
            pst1.executeUpdate();

            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            pst2.setInt(1, idpersona);
            int n2 = pst2.executeUpdate();

            if (n2 > 0) {
                listaClientes.eliminarPorCodigo(String.valueOf(idpersona));
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
    
    public String deshacerUltimaAccion() {
        if (!pila.isEmpty()) {
            vcliente ultimo = pila.pop();
            if (ultimo != null && listaClientes.eliminarPorCodigo(ultimo.getCodigo_cliente())) {
                return "Se deshizo la última inserción (cliente con código: " + ultimo.getCodigo_cliente() + ")";
            } else {
                return "No se pudo deshacer la acción.";
            }
        } else {
            return "No hay acciones para deshacer.";
        }
    }

    
    

}
