package CapaLogica;

import CapaDatos.*;
import CapaDatos.vpago;
import CapaLogica.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class fpago {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();

    public boolean insertar(vpago dts) {
        String sql = "INSERT INTO pago (idreserva, tipo_comprobante, num_comprobante, igv, total_pago, fecha_emision, fecha_pago) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, dts.getIdreserva());
            pst.setString(2, dts.getTipo_comprobante());
            pst.setString(3, dts.getNum_comprobante());
            pst.setDouble(4, dts.getIgv());
            pst.setDouble(5, dts.getTotal_pago());
            pst.setString(6, dts.getFecha_emision());
            pst.setString(7, dts.getFecha_pago());

            int n = pst.executeUpdate();
            return n != 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar pago: " + e);
            return false;
        }
    }
}
