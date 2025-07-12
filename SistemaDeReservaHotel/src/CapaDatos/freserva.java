package CapaDatos;

import CapaLogica.conexion;
import CapaLogica.reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class freserva {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";

    public boolean insertar(reserva dts){
        sSQL = "INSERT INTO reserva (idcliente, idhabitacion, fecha_reserva, fecha_entrada, fecha_salida, estado) " +
               "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdcliente());
            pst.setInt(2, dts.getIdhabitacion());
            pst.setString(3, dts.getFecha_reserva());
            pst.setString(4, dts.getFecha_entrada());
            pst.setString(5, dts.getFecha_salida());
            pst.setString(6, dts.getEstado());

            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar reserva: " + e.getMessage());
            return false;
        }
    }
}
