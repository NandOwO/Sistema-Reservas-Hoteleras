package CapaLogica;

import CapaDatos.vreserva;
import EstructuraDatos.ArbolAVL;
import java.util.LinkedList;
import java.util.Queue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class freserva {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    
    private ArbolAVL<Integer, vreserva> arbolReservasPorId; 
    
    private Queue<vreserva> colaReservasPendientes;

    public freserva() {
        arbolReservasPorId = new ArbolAVL<>();
        colaReservasPendientes = new LinkedList<>();
        cargarReservasEnAVL();
    }

    private void cargarReservasEnAVL() {
        String sql = "SELECT * FROM reserva WHERE estado = 'Activa' OR estado = 'Pendiente' OR estado = 'Confirmada' OR estado = 'Finalizada'";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                vreserva res = new vreserva(
                    rs.getInt("idreserva"),
                    rs.getInt("idhabitacion"),
                    rs.getInt("idcliente"),
                    rs.getInt("idtrabajador"),
                    rs.getString("tipo_reserva"),
                    rs.getDouble("costo_alojamiento"),
                    rs.getString("fecha_reserva"),
                    rs.getString("fecha_ingresa"),
                    rs.getString("fecha_salida"),
                    rs.getString("estado")
                );
                arbolReservasPorId.insertar(res.getIdreserva(), res);
            }
            System.out.println("¡Maestro! " + (arbolReservasPorId.raiz != null ? "Reservas cargadas en AVL con éxito." : "No hay reservas activas para cargar en AVL."));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar reservas en AVL desde la DB: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos en cargarReservasEnAVL: " + e.getMessage());
            }
        }
    }

    public vreserva buscarReservaPorId(int idReserva) {
        return arbolReservasPorId.buscar(idReserva);
    }

    public void encolarReservaParaInsercion(vreserva dts) {
        colaReservasPendientes.offer(dts);
        JOptionPane.showMessageDialog(null, "Reserva encolada para procesamiento. ID: " + dts.getIdreserva());
    }

    public boolean procesarColaInserciones() {
        boolean todoExito = true;
        while (!colaReservasPendientes.isEmpty()) {
            vreserva dts = colaReservasPendientes.peek();
            if (insertarEnBaseDeDatos(dts)) {
                colaReservasPendientes.poll();
                arbolReservasPorId.insertar(dts.getIdreserva(), dts);
                System.out.println("Reserva " + dts.getIdreserva() + " procesada y guardada en DB y AVL.");
            } else {
                todoExito = false;
                JOptionPane.showMessageDialog(null, "Fallo al procesar reserva " + dts.getIdreserva() + ". Se mantendrá en cola.");
                break; 
            }
        }
        return todoExito;
    }

    private boolean insertarEnBaseDeDatos(vreserva dts) {
        String sql = "INSERT INTO reserva (idhabitacion, idcliente, idtrabajador, tipo_reserva, fecha_reserva, fecha_ingresa, fecha_salida, costo_alojamiento, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = null;
        try {
            pst = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, dts.getIdhabitacion());
            pst.setInt(2, dts.getIdcliente());
            pst.setInt(3, dts.getIdtrabajador());
            pst.setString(4, dts.getTipo_reserva());
            pst.setString(5, dts.getFecha_reserva());
            pst.setString(6, dts.getFecha_entrada());
            pst.setString(7, dts.getFecha_salida());
            pst.setDouble(8, dts.getCosto_alojamiento());
            pst.setString(9, dts.getEstado());

            int n = pst.executeUpdate();


            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                dts.setIdreserva(rs.getInt(1));
            }

            return n != 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al insertar reserva en DB: " + e.getMessage());
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar PreparedStatement: " + e.getMessage());
            }
        }
    }
    
    @Deprecated
    public boolean insertar(vreserva dts) {
        encolarReservaParaInsercion(dts);
        return true;
    }

    public DefaultTableModel mostrar() {
        DefaultTableModel modelo;
        String[] titulos = {"ID", "Habitación", "Cliente", "Trabajador", "Tipo Reserva", "Costo Alojamiento", "Fecha Reserva", "Fecha Entrada", "Fecha Salida", "Estado"};
        String[] registro = new String[10];

        modelo = new DefaultTableModel(null, titulos);

        String sql = "SELECT idreserva, idhabitacion, idcliente, idtrabajador, tipo_reserva, costo_alojamiento, fecha_reserva, fecha_ingresa, fecha_salida, estado FROM reserva";

        Statement st = null;
        ResultSet rs = null;

        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                registro[0] = rs.getString("idreserva");
                registro[1] = rs.getString("idhabitacion");
                registro[2] = rs.getString("idcliente");
                registro[3] = rs.getString("idtrabajador");
                registro[4] = rs.getString("tipo_reserva");
                registro[5] = rs.getString("costo_alojamiento");
                registro[6] = rs.getString("fecha_reserva");
                registro[7] = rs.getString("fecha_ingresa");
                registro[8] = rs.getString("fecha_salida");
                registro[9] = rs.getString("estado");

                modelo.addRow(registro);
            }

            return modelo;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar reservas: " + e.getMessage());
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos en mostrar: " + e.getMessage());
            }
        }
    }

    // Agrega métodos para editar y eliminar para mantener el AVL sincronizado
    public boolean editar(vreserva dts) {
        String sql = "UPDATE reserva SET idhabitacion=?, idcliente=?, idtrabajador=?, tipo_reserva=?, fecha_reserva=?, fecha_ingresa=?, fecha_salida=?, costo_alojamiento=?, estado=? WHERE idreserva=?";
        PreparedStatement pst = null;
        try {
            pst = cn.prepareStatement(sql);
            pst.setInt(1, dts.getIdhabitacion());
            pst.setInt(2, dts.getIdcliente());
            pst.setInt(3, dts.getIdtrabajador());
            pst.setString(4, dts.getTipo_reserva());
            pst.setString(5, dts.getFecha_reserva());
            pst.setString(6, dts.getFecha_entrada()); // Asumo que getFecha_entrada() corresponde a fecha_ingresa en DB
            pst.setString(7, dts.getFecha_salida());
            pst.setDouble(8, dts.getCosto_alojamiento());
            pst.setString(9, dts.getEstado());
            pst.setInt(10, dts.getIdreserva());

            int n = pst.executeUpdate();
            if (n != 0) {
                arbolReservasPorId.insertar(dts.getIdreserva(), dts); 
                return true;
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al editar reserva: " + e.getMessage());
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar PreparedStatement: " + e.getMessage());
            }
        }
    }

    public boolean eliminar(int idreserva) { 
        String sql = "DELETE FROM reserva WHERE idreserva=?";
        PreparedStatement pst = null;
        try {
            pst = cn.prepareStatement(sql);
            pst.setInt(1, idreserva);

            int n = pst.executeUpdate();
            if (n != 0) { 
                JOptionPane.showMessageDialog(null, "Reserva eliminada de la DB. (Pendiente eliminar de AVL)"); // Mensaje temporal
                return true;
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar reserva: " + e.getMessage());
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar PreparedStatement: " + e.getMessage());
            }
        }
    }
    
    public boolean actualizarEstadoReserva(int idReserva, String nuevoEstado) {
        String SQL = "UPDATE reserva SET estado=? WHERE idreserva=?";
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, nuevoEstado);
            pst.setInt(2, idReserva);

            int n = pst.executeUpdate();

            if (n != 0) {
                vreserva reservaAActualizar = arbolReservasPorId.buscar(idReserva);
                if (reservaAActualizar != null) {
                    reservaAActualizar.setEstado(nuevoEstado);
                    arbolReservasPorId.insertar(reservaAActualizar.getIdreserva(), reservaAActualizar); 
                } else {
                    System.err.println("Advertencia: Reserva con ID " + idReserva + " no encontrada en el AVL para actualizar estado.");
                }
                pst.close();
                return true;
            } else {
                pst.close();
                return false; 
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al actualizar estado de reserva: " + e.getMessage());
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado al actualizar estado de reserva: " + e.getMessage());
            return false;
        }
    }
}
