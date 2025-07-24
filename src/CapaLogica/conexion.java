
package CapaLogica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;



public class conexion {
     public conexion() {
    }
    
    private static conexion instancia; 
    Connection conectar = null; 
    String usuario =  "root"; 
    String contraseña = "Chiclayo2005"; 
    String bd = "sistemareservahotel"; 
    String ip = "127.0.0.1"; 
    String puerto = "3306"; 
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection conectar (){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contraseña);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        return conectar;
    }
    
    public void cerrarResultado(ResultSet resultado){
        try {
            resultado.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void cerrarStatement(Statement statement){
        try {
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void cerrarConexion(Connection conexion) throws SQLException{
        try {
            conectar.close();
            
        } catch (Exception e) {
            System.out.println(e);
            conectar.close();
        }finally{
            conectar.close();
        }
    }
    
    //Patron Singleton
    public static conexion getInstance(){
        if (instancia == null) {
            instancia = new conexion(); 
        }
        return instancia; 
    }
}
