
package CapaLogica;

import CapaDatos.vtrabajador;
import EstructuraDatos.ListaEnlazadaTrabajador;
import EstructuraDatos.PilaAccionesTrabajador;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ftrabajador {
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;
    
    //Integracion de la estructura de datos
    private static ListaEnlazadaTrabajador listaTrabajadores = new ListaEnlazadaTrabajador();
    public static PilaAccionesTrabajador pila = new PilaAccionesTrabajador(10);
    
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
        String[] titulos = {"ID", "Nombre", "Apaterno", "Amaterno", "Numero documento", "Direccion", "Telefono", "Email", "Sueldo", "Acceso", "Login", "Password", "Estado"};
        String[] registro = new String[13];
        totalregistros = 0;
        
        modelo = new DefaultTableModel(null, titulos);
        
      
       listaTrabajadores = new ListaEnlazadaTrabajador();
       sSQL = "SELECT p.idpersona, p.nombre, p.apaterno, p.amaterno, p.num_documento, "
         + "p.direccion, p.telefono, p.email, t.sueldo, t.acceso, t.login, t.password, t.estado "
         + "FROM persona p INNER JOIN trabajador t ON p.idpersona = t.idpersona "
         + "WHERE p.num_documento LIKE '%" + buscar + "%' ORDER BY p.idpersona DESC";
       
       try {
            Statement st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apaterno");
                registro[3] = rs.getString("amaterno");
                registro[4] = rs.getString("num_documento");
                registro[5] = rs.getString("direccion");
                registro[6] = rs.getString("telefono");
                registro[7] = rs.getString("email");
                registro[8] = rs.getString("sueldo");
                registro[9] = rs.getString("acceso");
                registro[10] = rs.getString("login");
                registro[11] = rs.getString("password");
                registro[12] = rs.getString("estado");
                
                modelo.addRow(registro);
                totalregistros++;
                
               // Guardar en lista enlazada
                vtrabajador tra = new vtrabajador();
                tra.setIdpersona(Integer.parseInt(rs.getString("idpersona")));
                tra.setNombre(rs.getString("nombre"));
                tra.setApaterno(rs.getString("apaterno"));
                tra.setAmaterno(rs.getString("amaterno"));
                tra.setNum_documento(rs.getString("num_documento"));
                tra.setDireccion(rs.getString("direccion"));
                tra.setTelefono(rs.getString("telefono"));
                tra.setEmail(rs.getString("email"));
                tra.setSueldo(Double.parseDouble(rs.getString("sueldo")));
                tra.setAcceso(rs.getString("acceso"));
                tra.setLogin(rs.getString("login"));
                tra.setPassword(rs.getString("password"));
                tra.setEstado(rs.getString("estado"));
    
                listaTrabajadores.insertarAlFinal(tra);
            }

            return modelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los datos: " + e.getMessage());
            e.printStackTrace();
            return new DefaultTableModel(null, titulos);
        }
    }   
    public boolean insertar(vtrabajador dts) {
        String sSQL1 = "INSERT INTO persona (nombre, apaterno, amaterno, num_documento, direccion, telefono, email) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sSQL2 = "INSERT INTO trabajador (idpersona, sueldo, acceso, login, password, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
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
                throw new Exception("No se pudo obtener el ID generado.");
            }

            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            pst2.setInt(1, idpersonaGenerada);
            pst2.setDouble(2, dts.getSueldo());
            pst2.setString(3, dts.getAcceso());
            pst2.setString(4, dts.getLogin());
            pst2.setString(5, dts.getPassword());
            pst2.setString(6, dts.getEstado());

            int n2 = pst2.executeUpdate();

            dts.setIdpersona(idpersonaGenerada);
            pila.push(dts);
            listaTrabajadores.insertarAlFinal(dts);

            return (n1 > 0 && n2 > 0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar trabajador: " + e.getMessage());
            return false;
        }
    }

    public boolean editar(vtrabajador dts) {
        String sSQL1 = "UPDATE persona SET nombre=?, apaterno=?, amaterno=?, num_documento=?, direccion=?, telefono=?, email=? "
                + "WHERE idpersona=?";
        String sSQL2 = "UPDATE trabajador SET sueldo=?, acceso=?, login=?, password=?, estado=? "
                + "WHERE idpersona=?";

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

            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            pst2.setDouble(1, dts.getSueldo());
            pst2.setString(2, dts.getAcceso());
            pst2.setString(3, dts.getLogin());
            pst2.setString(4, dts.getPassword());
            pst2.setString(5, dts.getEstado());
            pst2.setInt(6, dts.getIdpersona());

            int n1 = pst1.executeUpdate();
            int n2 = pst2.executeUpdate();

            return (n1 > 0 && n2 > 0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al editar trabajador: " + e.getMessage());
            return false;
        }
    }

  
    public boolean eliminar(String num_documento) {
        String obtenerId = "SELECT idpersona FROM persona WHERE num_documento = ?";
        String sSQL1 = "DELETE FROM trabajador WHERE idpersona = ?";
        String sSQL2 = "DELETE FROM persona WHERE idpersona = ?";

        try {
            PreparedStatement pstId = cn.prepareStatement(obtenerId);
            pstId.setString(1, num_documento);
            ResultSet rs = pstId.executeQuery();

            if (rs.next()) {
                int idpersona = rs.getInt("idpersona");

                // Eliminar de trabajador
                PreparedStatement pst1 = cn.prepareStatement(sSQL1);
                pst1.setInt(1, idpersona);
                pst1.executeUpdate();

                // Eliminar de persona
                PreparedStatement pst2 = cn.prepareStatement(sSQL2);
                pst2.setInt(1, idpersona);
                int res = pst2.executeUpdate();

                if (res != 0) {
                    listaTrabajadores.eliminar(num_documento); // estructura auxiliar
                    return true;
                }
            }

            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String deshacerUltimaAccion() {
        if (!pila.isEmpty()) {
            vtrabajador ultimo = pila.pop();
            if (ultimo != null && listaTrabajadores.eliminar(ultimo.getNum_documento())) {
                return "Se deshizo la última inserción (trabajador con DNI: " + ultimo.getNum_documento() + ")";
            } else {
                return "No se pudo deshacer la acción.";
            }
        } else {
            return "No hay acciones para deshacer.";
        }
    }
     
}
