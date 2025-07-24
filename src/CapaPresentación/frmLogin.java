/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CapaPresentación;

import CapaLogica.conexion;
import com.mysql.cj.protocol.Resultset;
import java.awt.Color;
import java.awt.Color;
import javax.swing.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.*;


/**
 *
 * @author FERNANDO
 */
public class frmLogin extends javax.swing.JFrame {

    conexion conecta = conexion.getInstance(); 
    public frmLogin() {  
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    
    private void validarDatos() {
    String password = String.valueOf(cajaContraseña.getPassword());
    String usuario = cajaUsuario.getText();

    if (password.isEmpty() || usuario.isEmpty() || usuario.equals("Ingrese su nombre de usuario") || password.equals("********")) {
        JOptionPane.showMessageDialog(null, "Por favor, ingrese usuario y contraseña.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
    } else {
        Connection conectar = null;
        PreparedStatement seleccionar = null;
        ResultSet consulta = null;
        try {
            conectar = conecta.conectar();
            String sql = "SELECT p.nombre, p.apaterno, p.amaterno, t.acceso, t.estado " +
                         "FROM persona p INNER JOIN trabajador t ON p.idpersona = t.idpersona " +
                         "WHERE t.login = ? AND t.password = ?";
            
            seleccionar = conectar.prepareStatement(sql);
            seleccionar.setString(1, usuario);
            seleccionar.setString(2, password);

            consulta = seleccionar.executeQuery();

            if (consulta.next()) {
                String acceso = consulta.getString("acceso");
                String estado = consulta.getString("estado");
                
                String nombre = consulta.getString("nombre");
                String apaterno = consulta.getString("apaterno");
                String amaterno = consulta.getString("amaterno");
                String nombreCompleto = nombre + " " + apaterno + " " + amaterno;

                if ("Activado".equals(estado)) {
                    mensajeVerificado();
                    
                    frmInicio menu = new frmInicio(nombreCompleto, acceso); 
                    
                    frmInicio.lblnombreusuario.setText("¡Bienvenido, " + nombreCompleto + "!"); 
                    frmInicio.lbltipousuario.setText("Tipo de Usuario: " + acceso); 
                    
                    this.setVisible(false);
                    menu.setVisible(true);
                    menu.setLocationRelativeTo(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Su cuenta está deshabilitada. Contacte al administrador.", "Cuenta Deshabilitada", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error de Login", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de base de datos: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            System.err.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage(), "Error General", JOptionPane.ERROR_MESSAGE);
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (consulta != null) consulta.close();
                if (seleccionar != null) seleccionar.close();
                if (conectar != null) conecta.cerrarConexion(conectar);
            } catch (SQLException ex) {
                System.err.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
    }
}
    
    private void mensajeVerificado(){
        JOptionPane.showMessageDialog(null, "¡Bienvenido! Has iniciado sesión exitosamente.", "Login Exitoso", JOptionPane.INFORMATION_MESSAGE);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jPanel1 = new CapaPresentación.RoundedPanel();
        cajaUsuario = new javax.swing.JTextField();
        cajaContraseña = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        miLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(149, 163, 179));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cajaUsuario.setBackground(new java.awt.Color(255, 255, 255));
        cajaUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cajaUsuarioMousePressed(evt);
            }
        });
        cajaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaUsuarioActionPerformed(evt);
            }
        });
        jPanel1.add(cajaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 360, 40));

        cajaContraseña.setBackground(new java.awt.Color(255, 255, 255));
        cajaContraseña.setText("********");
        cajaContraseña.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cajaContraseñaMousePressed(evt);
            }
        });
        jPanel1.add(cajaContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 360, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/logo.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 100, 80));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(34, 34, 34));
        jLabel2.setText("INICIAR SESION");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

        jLabel3.setText("USUARIO");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        jLabel4.setText("CONTRASEÑA");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, -1, -1));

        btnVolver.setBackground(new java.awt.Color(75, 78, 109));
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("SALIR");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        jPanel1.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, 120, 50));

        btnLogin.setBackground(new java.awt.Color(75, 78, 109));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("LOGIN");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, 120, 50));

        bg.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 540, 570));

        jPanel2.setBackground(new java.awt.Color(75, 78, 109));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        miLabel.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        miLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        miLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/loginImagen.png"))); // NOI18N
        miLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        miLabel.setIconTextGap(0);
        jPanel2.add(miLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 570));

        bg.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, -1, 570));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 1006, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cajaUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cajaUsuarioMousePressed
        if (cajaUsuario.getText().equals("Ingrese su nombre de usuario")) {
            cajaUsuario.setText("");
            cajaUsuario.setForeground(Color.black);
        }

        if (String.valueOf(cajaContraseña.getPassword()).isEmpty()) {
            cajaContraseña.setText("********");
            cajaContraseña.setForeground(Color.gray);
        }

    }//GEN-LAST:event_cajaUsuarioMousePressed

    private void cajaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cajaUsuarioActionPerformed

    private void cajaContraseñaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cajaContraseñaMousePressed
        if (String.valueOf(cajaContraseña.getPassword()).equals("********")) {
            cajaContraseña.setText("");
            cajaContraseña.setForeground(Color.black);
        } 
        
        if (cajaUsuario.getText().isEmpty()){
            cajaUsuario.setText("Ingrese su nombre de usuario");
            cajaUsuario.setForeground(Color.gray);
        }
    }//GEN-LAST:event_cajaContraseñaMousePressed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        validarDatos();
    }//GEN-LAST:event_btnLoginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnVolver;
    private javax.swing.JPasswordField cajaContraseña;
    private javax.swing.JTextField cajaUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel miLabel;
    // End of variables declaration//GEN-END:variables
}
