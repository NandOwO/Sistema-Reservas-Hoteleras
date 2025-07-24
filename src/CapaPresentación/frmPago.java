/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CapaPresentación;

import CapaLogica.fpago;
import CapaDatos.vpago;
import CapaDatos.vreserva;
import CapaLogica.freserva;
import java.awt.Font;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class frmPago extends javax.swing.JFrame {

    public int idReservaAPagar = 0;
    private vreserva reservaActual = null;
    private vpago pagoActual = null;

    public frmPago() {
        initComponents();
    }

    public frmPago(int idReserva) {
        initComponents();
        this.idReservaAPagar = idReserva;
        txtIdReserva.setText(String.valueOf(idReserva));
        txtIdReserva.setEditable(false);
        cargarDatosDeReservaParaPago(idReserva);
    }

    private void cargarDatosDeReservaParaPago(int idReserva) {
        System.out.println("DEBUG: Intentando cargar datos para reserva ID: " + idReserva); // DEBUG
        freserva funcReserva = new freserva(); 
        reservaActual = funcReserva.buscarReservaPorId(idReserva); 

        if (reservaActual != null) {
            txtTotalPago.setText(String.valueOf(reservaActual.getCosto_alojamiento()));
            txtIGV.setText("0.19"); 
            System.out.println("DEBUG: Datos de reserva cargados exitosamente. Costo: " + reservaActual.getCosto_alojamiento()); // DEBUG
        } else {
            JOptionPane.showMessageDialog(this, "¡Lo siento, Maestro! No se encontró la reserva con ID: " + idReserva + ". No se pudo cargar el precio.", "Error de Carga", JOptionPane.ERROR_MESSAGE);
            txtTotalPago.setText("0.00");
            txtIGV.setText("0.00");
            System.out.println("DEBUG: NO se pudo cargar la reserva con ID: " + idReserva); // DEBUG
        }
    }

    private void simularImpresion() {
        final JOptionPane printingPane = new JOptionPane("¡Imprimiendo su comprobante! Por favor, espere un momento...",
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                new Object[]{},
                null);
        final javax.swing.JDialog dialog = printingPane.createDialog(this, "¡Imprimiendo!");
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(javax.swing.JDialog.DO_NOTHING_ON_CLOSE);

        javax.swing.Timer timer = new javax.swing.Timer(2000, e -> {
            dialog.dispose();
            JOptionPane.showMessageDialog(this, "¡Impresión completada! Su comprobante está listo. 📄", "¡Impresión Exitosa!", JOptionPane.INFORMATION_MESSAGE);
        });
        timer.setRepeats(false);
        timer.start();

        dialog.setVisible(true);
    }

    private void mostrarBoletaFactura() {
        if (reservaActual == null || pagoActual == null) {
            JOptionPane.showMessageDialog(this, "No hay datos de reserva o pago disponibles para generar la boleta/factura.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // --- Inicio de la construcción de la boleta/factura más segura ---
        StringBuilder sb = new StringBuilder();
        sb.append("====================================================\n");
        sb.append("                 COMPROBANTE DE PAGO                \n");
        sb.append("====================================================\n");
        sb.append("Tipo de Comprobante: ").append(pagoActual.getTipo_comprobante()).append("\n");
        sb.append("Número Comprobante:  ").append(pagoActual.getNum_comprobante()).append("\n");
        sb.append("Fecha de Emisión:    ").append(pagoActual.getFecha_emision()).append("\n");
        sb.append("Fecha de Pago:       ").append(pagoActual.getFecha_pago()).append("\n");
        sb.append("----------------------------------------------------\n");
        sb.append("Detalles de la Reserva:\n");
        sb.append("  Reserva ID:        #").append(reservaActual.getIdreserva()).append("\n");
        sb.append("  Tipo de Servicio:  ").append(reservaActual.getTipo_reserva()).append("\n");
        sb.append("  Fecha de Entrada:  ").append(reservaActual.getFecha_entrada()).append("\n");
        sb.append("  Fecha de Salida:   ").append(reservaActual.getFecha_salida()).append("\n");
        sb.append("  Estado de Reserva: ").append(reservaActual.getEstado()).append("\n");
        sb.append("----------------------------------------------------\n");
        sb.append("Desglose del Pago:\n");
        sb.append("  Costo Alojamiento: S/ ").append(String.format("%.2f", reservaActual.getCosto_alojamiento())).append("\n");
        sb.append("  IGV (").append(String.format("%.2f", pagoActual.getIgv() * 100)).append("%):       S/ ").append(String.format("%.2f", reservaActual.getCosto_alojamiento() * pagoActual.getIgv())).append("\n");
        sb.append("----------------------------------------------------\n");
        sb.append("TOTAL A PAGAR:       S/ ").append(String.format("%.2f", pagoActual.getTotal_pago())).append("\n");
        sb.append("====================================================\n");
        sb.append("          ¡Gracias por su preferencia!              \n");
        sb.append("          Visítenos pronto de nuevo.                \n");
        sb.append("====================================================\n");
        // --- Fin de la construcción de la boleta/factura más segura ---

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(450, 300)); 

        Object[] options = {"Imprimir", "Cerrar"};

        int result = JOptionPane.showOptionDialog(
                this,
                scrollPane,
                "¡Maestro! Comprobante de Pago",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        if (result == JOptionPane.YES_OPTION) {
            simularImpresion();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtIdReserva = new javax.swing.JTextField();
        cbTipoComprobante = new javax.swing.JComboBox<>();
        txtNumComprobante = new javax.swing.JTextField();
        txtIGV = new javax.swing.JTextField();
        txtTotalPago = new javax.swing.JTextField();
        dcFechaEmision = new com.toedter.calendar.JDateChooser();
        dcFechaPago = new com.toedter.calendar.JDateChooser();
        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtIdReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdReservaActionPerformed(evt);
            }
        });

        cbTipoComprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Boleta ", "Factura", " " }));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel1.setText("Reserva");

        jLabel2.setText("Comprobante");

        jLabel3.setText("IGV");

        jLabel4.setText("Comprobante");

        jLabel5.setText("Total de pago");

        jLabel6.setText("Fecha de Emision");

        jLabel7.setText("Fecha de pago");

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtIdReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(cbTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtNumComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtTotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dcFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(98, 98, 98)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dcFechaPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        vpago dts = new vpago();
        fpago func = new fpago();

        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String fechaEmision = formato.format(dcFechaEmision.getDate());
            String fechaPago = formato.format(dcFechaPago.getDate());

            dts.setIdreserva(Integer.parseInt(txtIdReserva.getText()));
            dts.setTipo_comprobante(cbTipoComprobante.getSelectedItem().toString());
            dts.setNum_comprobante(txtNumComprobante.getText());
            dts.setIgv(Double.parseDouble(txtIGV.getText()));
            dts.setTotal_pago(Double.parseDouble(txtTotalPago.getText()));
            dts.setFecha_emision(fechaEmision);
            dts.setFecha_pago(fechaPago);

            if (func.insertar(dts)) {
                JOptionPane.showMessageDialog(null, "Pago registrado correctamente.");
                pagoActual = dts; 
                
                mostrarBoletaFactura();
                
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar el pago.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtIdReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdReservaActionPerformed

    }//GEN-LAST:event_txtIdReservaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cbTipoComprobante;
    private com.toedter.calendar.JDateChooser dcFechaEmision;
    private com.toedter.calendar.JDateChooser dcFechaPago;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtIGV;
    private javax.swing.JTextField txtIdReserva;
    private javax.swing.JTextField txtNumComprobante;
    private javax.swing.JTextField txtTotalPago;
    // End of variables declaration//GEN-END:variables
}
