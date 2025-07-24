package CapaPresentación;

import javax.swing.JOptionPane;
import CapaLogica.freserva;
import CapaDatos.vreserva;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class frmReserva extends javax.swing.JFrame {

    freserva func;
    public int idReservaEnEdicion = 0;

    /**
     * Creates new form frmReserva
     */
    public frmReserva() {
        initComponents();
        func = new freserva();
        mostrar();
    }

    void mostrar() {
        try {
            DefaultTableModel modelo;
            freserva func = new freserva();
            modelo = func.mostrar();

            tablaListado.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error al mostrar reservas: " + e);
        }
    }

    private void limpiarCampos() {
        txtIdCliente.setText("");
        txtIdHabitacion.setText("");
        txtTrabajador.setText("");
        cbTipoReserva.setSelectedIndex(0);
        txtPrecio.setText("");
        dcFechaReserva.setDate(null);
        dcFechaEntrada.setDate(null);
        dcFechaSalida.setDate(null);
        cbEstado.setSelectedIndex(0);
    }

    private void limpiarCamposDetalleReserva() {
        txtIdHabitacion.setText("");
        txtIdCliente.setText("");
        txtTrabajador.setText("");
        cbTipoReserva.setSelectedIndex(0);
        txtPrecio.setText("");
        dcFechaReserva.setDate(null);
        dcFechaEntrada.setDate(null);
        dcFechaSalida.setDate(null);
        cbEstado.setSelectedIndex(0);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIdHabitacion = new javax.swing.JTextField();
        cbEstado = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtIdCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaListado = new javax.swing.JTable();
        dcFechaReserva = new com.toedter.calendar.JDateChooser();
        dcFechaEntrada = new com.toedter.calendar.JDateChooser();
        dcFechaSalida = new com.toedter.calendar.JDateChooser();
        btnBuscar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTrabajador = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnEstado = new javax.swing.JButton();
        cbTipoReserva = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro de Reserva");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));
        jPanel1.setForeground(new java.awt.Color(51, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("ID Cliente");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 70, -1));

        jLabel5.setText("Fecha Salida");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 276, 70, 20));

        jLabel2.setText("ID Habitacion");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 101, -1));

        txtIdHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdHabitacionActionPerformed(evt);
            }
        });
        jPanel1.add(txtIdHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 150, -1));

        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Confirmado", "Pendiente", "Activo", "Finalizado", " " }));
        cbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEstadoActionPerformed(evt);
            }
        });
        jPanel1.add(cbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 150, 20));

        jLabel6.setText("Estado");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 40, 20));

        jLabel3.setText("Fecha Reserva");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 90, -1));
        jPanel1.add(txtIdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 150, -1));

        jLabel4.setText("Fecha de entrada");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 102, -1));

        tablaListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaListado);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(404, 80, 720, 380));
        jPanel1.add(dcFechaReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 150, -1));
        jPanel1.add(dcFechaEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 150, -1));
        jPanel1.add(dcFechaSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 282, 150, 20));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 100, 30));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 90, 40));
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 160, 30));

        jLabel7.setText("ID Tabajador");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 70, -1));
        jPanel1.add(txtTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 150, -1));

        jLabel8.setText("Ingrese ID de reserva a buscar:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 160, -1));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 100, 30));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/salir.gif"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 40, 100, 30));

        btnEstado.setText("Estado");
        btnEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadoActionPerformed(evt);
            }
        });
        jPanel1.add(btnEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 90, 40));

        cbTipoReserva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Online", "Telefono", "Walk-In", "Agencia" }));
        jPanel1.add(cbTipoReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 150, -1));

        jLabel9.setText("Tipo Reserva");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));
        jPanel1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 150, -1));

        jLabel10.setText("COSTO");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, -1, -1));

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 90, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1130, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdHabitacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdHabitacionActionPerformed

    private void cbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEstadoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        vreserva dts = new vreserva();
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            if (dcFechaReserva.getDate() == null || dcFechaEntrada.getDate() == null || dcFechaSalida.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione todas las fechas de la reserva.");
                return;
            }
            String fechaReserva = formato.format(dcFechaReserva.getDate());
            String fechaEntrada = formato.format(dcFechaEntrada.getDate());
            String fechaSalida = formato.format(dcFechaSalida.getDate());

            if (txtIdCliente.getText().isEmpty() || txtIdHabitacion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos.");
                return;
            }

            dts.setIdhabitacion(Integer.parseInt(txtIdHabitacion.getText()));
            dts.setIdcliente(Integer.parseInt(txtIdCliente.getText()));
            dts.setIdtrabajador(Integer.parseInt(txtTrabajador.getText()));
            dts.setTipo_reserva(cbTipoReserva.getSelectedItem().toString());
            dts.setCosto_alojamiento(Double.parseDouble(txtPrecio.getText()));
            dts.setFecha_reserva(fechaReserva);
            dts.setFecha_entrada(fechaEntrada);
            dts.setFecha_salida(fechaSalida);
            dts.setEstado(cbEstado.getSelectedItem().toString());

            func.encolarReservaParaInsercion(dts);

            if (func.procesarColaInserciones()) {
                JOptionPane.showMessageDialog(null, "¡Reserva registrada y procesada correctamente!");
                mostrar();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Fallo al registrar la reserva.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error de formato de número. Asegúrese de que ID Cliente e ID Habitación sean números válidos: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado al encolar la reserva: " + e.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
        frmInicio inicio = new frmInicio();
        inicio.setVisible(true);
        inicio.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String idBuscarStr = txtBuscar.getText();

        if (idBuscarStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese el ID de la reserva que desea buscar.");
            limpiarCamposDetalleReserva();
            return;
        }

        int idABuscar;
        try {
            idABuscar = Integer.parseInt(idBuscarStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "¡Error! El ID de reserva debe ser un número válido.");
            limpiarCamposDetalleReserva();
            return;
        }

        vreserva reservaEncontrada = func.buscarReservaPorId(idABuscar);

        if (reservaEncontrada != null) {
            JOptionPane.showMessageDialog(null, "¡Reserva encontrada! Mostrando detalles.");
            idReservaEnEdicion = reservaEncontrada.getIdreserva();
            try {

                txtIdHabitacion.setText(String.valueOf(reservaEncontrada.getIdhabitacion()));
                txtIdCliente.setText(String.valueOf(reservaEncontrada.getIdcliente()));
                txtTrabajador.setText(String.valueOf(reservaEncontrada.getIdtrabajador()));

                cbTipoReserva.setSelectedItem(reservaEncontrada.getTipo_reserva());
                txtPrecio.setText(String.valueOf(reservaEncontrada.getCosto_alojamiento()));

                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                dcFechaReserva.setDate(formatoFecha.parse(reservaEncontrada.getFecha_reserva()));
                dcFechaEntrada.setDate(formatoFecha.parse(reservaEncontrada.getFecha_entrada()));
                dcFechaSalida.setDate(formatoFecha.parse(reservaEncontrada.getFecha_salida()));

                cbEstado.setSelectedItem(reservaEncontrada.getEstado());

            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Error al parsear fechas de la reserva: " + e.getMessage());
                limpiarCamposDetalleReserva();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ninguna reserva con el ID: " + idABuscar);
            limpiarCamposDetalleReserva();
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSeleccionada = tablaListado.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione la reserva que desea eliminar de la tabla.");
            return;
        }
        int idAEliminar = Integer.parseInt(tablaListado.getValueAt(filaSeleccionada, 0).toString());
        int confirmacion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea eliminar la reserva con ID: " + idAEliminar + "?\n¡Esta acción no se puede deshacer!",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                if (func.eliminar(idAEliminar)) {
                    JOptionPane.showMessageDialog(null, "¡Reserva eliminada correctamente!");
                    mostrar();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "Fallo al eliminar la reserva. Puede que no exista o haya un error.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado al eliminar la reserva: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Eliminación de reserva cancelada.");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (idReservaEnEdicion == 0) {
            JOptionPane.showMessageDialog(null, "No hay ninguna reserva cargada para editar. Por favor, busque una o seleccione una en la tabla primero.");
            return;
        }

        vreserva dts = new vreserva();
        try {
            dts.setIdreserva(idReservaEnEdicion);

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            if (dcFechaReserva.getDate() == null || dcFechaEntrada.getDate() == null || dcFechaSalida.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione todas las fechas de la reserva.");
                return;
            }
            String fechaReserva = formato.format(dcFechaReserva.getDate());
            String fechaEntrada = formato.format(dcFechaEntrada.getDate());
            String fechaSalida = formato.format(dcFechaSalida.getDate());

            if (txtIdCliente.getText().isEmpty() || txtIdHabitacion.getText().isEmpty() || txtTrabajador.getText().isEmpty() || txtPrecio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos.");
                return;
            }

            dts.setIdhabitacion(Integer.parseInt(txtIdHabitacion.getText()));
            dts.setIdcliente(Integer.parseInt(txtIdCliente.getText()));
            dts.setIdtrabajador(Integer.parseInt(txtTrabajador.getText()));
            dts.setTipo_reserva(cbTipoReserva.getSelectedItem().toString());
            dts.setCosto_alojamiento(Double.parseDouble(txtPrecio.getText()));
            dts.setFecha_reserva(fechaReserva);
            dts.setFecha_entrada(fechaEntrada);
            dts.setFecha_salida(fechaSalida);
            dts.setEstado(cbEstado.getSelectedItem().toString());

            if (func.editar(dts)) {
                JOptionPane.showMessageDialog(null, "¡Reserva actualizada correctamente!");
                mostrar();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Fallo al actualizar la reserva. Asegúrese de que el ID existe.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error de formato de número. Asegúrese de que los campos numéricos sean válidos: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado al actualizar la reserva: " + e.getMessage());
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadoActionPerformed
        try {
            int filaSeleccionada = tablaListado.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una reserva de la tabla para cambiar su estado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String idReservaStr = tablaListado.getValueAt(filaSeleccionada, 0).toString();
            int idReserva = Integer.parseInt(idReservaStr);

            String[] estadosPosibles = {"Activa", "Pendiente", "Finalizada", "Confirmada"};
            JComboBox<String> comboBox = new JComboBox<>(estadosPosibles);

            try {
                String estadoActual = tablaListado.getValueAt(filaSeleccionada, 9).toString();
                comboBox.setSelectedItem(estadoActual);
            } catch (Exception e) {
                System.err.println("No se pudo pre-seleccionar el estado actual: " + e.getMessage());
            }

            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    comboBox,
                    "Cambiar Estado de Reserva (ID: " + idReserva + ")",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (opcion == JOptionPane.OK_OPTION) {
                String nuevoEstado = (String) comboBox.getSelectedItem();
                nuevoEstado = nuevoEstado.trim();
                System.out.println("Nuevo estado seleccionado: " + nuevoEstado);

                if (func.actualizarEstadoReserva(idReserva, nuevoEstado)) {
                    JOptionPane.showMessageDialog(this, "¡Estado de reserva actualizado correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    mostrar();

                    if (nuevoEstado.equals("Finalizada") || nuevoEstado.equals("Pagada")) { 
                        int confirmacionPago = JOptionPane.showConfirmDialog(this, "¿Desea generar el pago para esta reserva?", "Generar Pago", JOptionPane.YES_NO_OPTION);
                        if (confirmacionPago == JOptionPane.YES_OPTION) {
                            frmPago formPago = new frmPago(idReserva);
                            formPago.setVisible(true);
                            formPago.setLocationRelativeTo(null);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo actualizar el estado de la reserva. Puede que el ID no exista.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID de la reserva en la tabla no es un número válido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado al cambiar el estado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEstadoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEstado;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JComboBox<String> cbTipoReserva;
    private com.toedter.calendar.JDateChooser dcFechaEntrada;
    private com.toedter.calendar.JDateChooser dcFechaReserva;
    private com.toedter.calendar.JDateChooser dcFechaSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaListado;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdHabitacion;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtTrabajador;
    // End of variables declaration//GEN-END:variables
}
