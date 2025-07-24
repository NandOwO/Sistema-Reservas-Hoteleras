package CapaPresentación;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class frmInicio extends javax.swing.JFrame {

    private String nivelAcceso;

    public frmInicio() {
        initComponents();
    }

    public frmInicio(String nombreCompleto, String acceso) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Sistema De Gestion de Reserva Hotelera");

        this.nivelAcceso = acceso;
        if (lbltipousuario != null) {
            lbltipousuario.setText("¡Bienvenido, " + nombreCompleto + "!");
            lblnombreusuario.setForeground(new java.awt.Color(0, 0, 0));
            lblnombreusuario.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        } else {
            System.err.println("Error: lblnombreusuario es null.");
        }

        if (lbltipousuario != null) {
            lbltipousuario.setText("Tipo de Usuario: " + acceso);
            lbltipousuario.setText("Tipo de Usuario: " + acceso);
            lbltipousuario.setForeground(new java.awt.Color(0, 0, 0));
            lbltipousuario.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        } else {
            System.err.println("Error: lbltipousuario es null.");
        }

        configurarPermisos();
    }

    private void configurarPermisos() {
        if (nivelAcceso != null) {
            ImageIcon icon = null;
            int iconWidth = 100;
            int iconHeight = 100;
            
            switch (nivelAcceso.toLowerCase()) {
                case "administrador":
                    fileMenu.setVisible(true);
                    editMenu.setVisible(true);
                    cutMenuItem.setEnabled(true);
                    copyMenuItem.setEnabled(true);

                    helpMenu.setVisible(true);
                    contentMenuItem.setEnabled(true);
                    aboutMenuItem.setEnabled(true);
                    jMenuItem1.setEnabled(true);

                    jMenu2.setVisible(true);
                    jMenuItem2.setEnabled(true);
                    
                    try {

                        icon = new ImageIcon(getClass().getResource("/imagen/admin.png")); 
                        if (icon != null && icon.getImage() != null) {
                            Image img = icon.getImage();
                            Image scaledImg = img.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
                            icon = new ImageIcon(scaledImg);
                        }
                    } catch (Exception e) {
                        System.err.println("Error al cargar icono de administrador: " + e.getMessage());
                    }
                    
                    break;

                case "usuario":
                    fileMenu.setVisible(true);
                    editMenu.setVisible(true);
                    cutMenuItem.setEnabled(true);
                    copyMenuItem.setEnabled(true);

                    helpMenu.setVisible(true);
                    contentMenuItem.setEnabled(true);
                    aboutMenuItem.setEnabled(true);
                    jMenuItem1.setEnabled(true);

                    jMenu2.setVisible(false);
                    jMenuItem2.setEnabled(false);
                    
                    try {

                        icon = new ImageIcon(getClass().getResource("/imagen/usuario.png"));
                        if (icon != null && icon.getImage() != null) {
                            Image img = icon.getImage();
                            Image scaledImg = img.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
                            icon = new ImageIcon(scaledImg);
                        }
                    } catch (Exception e) {
                        System.err.println("Error al cargar icono de usuario: " + e.getMessage());
                    }
                    break;
                default:

                    JOptionPane.showMessageDialog(this, "Nivel de acceso desconocido: " + nivelAcceso + ". Acceso restringido.", "Error de Acceso", JOptionPane.ERROR_MESSAGE);
                    fileMenu.setVisible(false);
                    editMenu.setVisible(false);
                    helpMenu.setVisible(false);
                    jMenu2.setVisible(false);
                    cutMenuItem.setEnabled(false);
                    copyMenuItem.setEnabled(false);
                    contentMenuItem.setEnabled(false);
                    aboutMenuItem.setEnabled(false);
                    jMenuItem1.setEnabled(false);
                    jMenuItem2.setEnabled(false);
                    break;
            }
            
             if (lblimagen != null && icon != null) {
                lblimagen.setIcon(icon);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        lblnombreusuario = new javax.swing.JLabel();
        lbltipousuario = new javax.swing.JLabel();
        lblimagen = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblnombreusuario.setText("jLabel1");
        desktopPane.add(lblnombreusuario);
        lblnombreusuario.setBounds(340, 110, 330, 30);

        lbltipousuario.setText("jLabel2");
        desktopPane.add(lbltipousuario);
        lbltipousuario.setBounds(340, 150, 280, 40);
        desktopPane.add(lblimagen);
        lblimagen.setBounds(90, 70, 180, 180);

        fileMenu.setMnemonic('f');
        fileMenu.setText("Sisreserva");
        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Archivo");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Habitaciones");
        cutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Productos");
        copyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(copyMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Reservas");

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Reservas ");
        contentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("Clientes");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        jMenuItem1.setText("Pagos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        helpMenu.add(jMenuItem1);

        menuBar.add(helpMenu);

        jMenu2.setText("Configuraciones");

        jMenuItem2.setText("Usuarios y Accesos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        menuBar.add(jMenu2);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutMenuItemActionPerformed
        frmHabitación habitacion = new frmHabitación();
        habitacion.setVisible(true);
        habitacion.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_cutMenuItemActionPerformed

    private void copyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyMenuItemActionPerformed
        frmProducto producto = new frmProducto();
        producto.setVisible(true);
        producto.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_copyMenuItemActionPerformed

    private void contentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentMenuItemActionPerformed
        // TODO add your handling code here:
        frmReserva reserva = new frmReserva();
        reserva.setVisible(true);
        reserva.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_contentMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        // TODO add your handling code here:
        frmCliente cliente = new frmCliente();
        cliente.setVisible(true);
        cliente.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        frmTrabajador usuario = new frmTrabajador();
        usuario.setVisible(true);
        usuario.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        frmPago pago = new frmPago();
        pago.setVisible(true);
        pago.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JLabel lblimagen;
    public static javax.swing.JLabel lblnombreusuario;
    public static javax.swing.JLabel lbltipousuario;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

}
