package CapaPresentación.admin;

import CapaLogica.fhabitacion;
import raven.alerts.MessageAlerts;
import raven.tabbed.TabbedForm;
import CapaDatos.vhabitacion;
import CapaLogica.fhabitacion;
import CapaPresentación.admin.swing.CheckBoxTableHeaderRenderer;
import CapaPresentación.admin.swing.TableHeaderAlignment;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.Component;
import java.awt.Font;
import java.awt.Window;
import javax.swing.*;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;

public class frmhabitacion extends TabbedForm {

    private String accion = "guardar";
    fhabitacion func = new fhabitacion();

    public frmhabitacion() {
        initComponents();
        init();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
    }

    public void init() {
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");

        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "font:bold;");

        table.putClientProperty(FlatClientProperties.STYLE, ""
                + "rowHeight:70;"
                + "showHorizontalLines:true;"
                + "intercellSpacing:0,1;"
                + "cellFocusColor:$TableHeader.hoverBackground;"
                + "selectionBackground:$TableHeader.hoverBackground;"
                + "selectionForeground:$Table.foreground;");

        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "trackArc:999;"
                + "trackInsets:3,3,3,3;"
                + "thumbInsets:3,3,3,3;"
                + "background:$Table.background;");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +5;");

        txtbuscar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
        txtbuscar.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("raven/drawer/icon/search.svg"));
        txtbuscar.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:5,20,5,20;"
                + "background:$Panel.background");
        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));
        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table));
    }

    void ocultar_columnas() {
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

//    void inhabilitar() {
//        txthabitacion.setEnabled(false);
//        txtnumero.setEnabled(false);
//        cbopiso.setEnabled(false);
//        txtdescripcion.setEnabled(false);
//        txtcaracteristicas.setEnabled(false);
//        txtprecio.setEnabled(false);
//        cboestado.setEnabled(false);
//        cbotipohabitacion.setEnabled(false);
//
//        btnnuevo.setEnabled(false);
//        btnguardar.setEnabled(false);
//        btncancelar.setEnabled(false);
//        btneliminar.setEnabled(false);
//
//        txthabitacion.setText("");
//        txtnumero.setText("");
//        txtdescripcion.setText("");
//        txtcaracteristicas.setText("");
//        txtprecio.setText("");
//    }
//    void habilitar() {
//        txthabitacion.setEnabled(true);
//        txtnumero.setEnabled(true);
//        cbopiso.setEnabled(true);
//        txtdescripcion.setEnabled(true);
//        txtcaracteristicas.setEnabled(true);
//        txtprecio.setEnabled(true);
//        cboestado.setEnabled(true);
//        cbotipohabitacion.setEnabled(true);
//
//        btnnuevo.setEnabled(true);
//        btnguardar.setEnabled(true);
//        btncancelar.setEnabled(true);
//        btneliminar.setEnabled(true);
//    }
    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            modelo = func.mostrar(buscar);
            table.setModel(modelo);
            ocultar_columnas();
        } catch (Exception e) {
            MessageAlerts.getInstance().showMessage("Error al buscar", "No se encontro la tabla a la base de datos", MessageAlerts.MessageType.ERROR);
            System.out.println(e);
        }
    }

//    void limpiarCampos() {
//        txthabitacion.setText("");
//        txtnumero.setText("");
//        txtdescripcion.setText("");
//        txtcaracteristicas.setText("");
//        txtprecio.setText("");
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        buttonAction1 = new CapaPresentación.admin.swing.ButtonAction();
        buttonAction2 = new CapaPresentación.admin.swing.ButtonAction();
        buttonAction3 = new CapaPresentación.admin.swing.ButtonAction();
        buttonAction4 = new CapaPresentación.admin.swing.ButtonAction();
        buttonAction5 = new CapaPresentación.admin.swing.ButtonAction();
        txtbuscar = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbTitle.setText("Buscar :");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SELECT", "#", "Numero", "Piso", "Descripcion", "Caracteristicas", "precio_diario", "estado", "Tipo de Habitacion", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
        scroll.setViewportView(table);

        buttonAction1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/buscar.png"))); // NOI18N
        buttonAction1.setText("Buscar");

        buttonAction2.setText("Historial");

        buttonAction3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/eliminar.png"))); // NOI18N
        buttonAction3.setText("Eliminar");

        buttonAction4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/salir.gif"))); // NOI18N
        buttonAction4.setText("Salir");
        buttonAction4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction4ActionPerformed(evt);
            }
        });

        buttonAction5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/nuevo.GIF"))); // NOI18N
        buttonAction5.setText("Nueva");
        buttonAction5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 1148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonAction5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonAction1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonAction3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonAction2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonAction4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(200, 200, 200))))))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAction1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAction3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAction2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAction4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAction5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 1160, 660));
    }// </editor-fold>//GEN-END:initComponents

    private void tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMousePressed

    }//GEN-LAST:event_tableMousePressed

    private void buttonAction4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAction4ActionPerformed

    private void buttonAction5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction5ActionPerformed
         frmRegistroHabitacion nuevo = new frmRegistroHabitacion();
         DefaultOption option = new DefaultOption(){
             @Override
             public boolean closeWhenClickOutside() {
                 return true; 
             }
         };
         
         String actions[] = new String[]{"Cancel","Save"};
         GlassPanePopup.showPopup(new SimplePopupBorder(nuevo, "Registrar habitacion",actions,(pc, i) -> {
             if (i==1) {
                 //save
             }else{
             pc.closePopup();
             }
             
         }));
    }//GEN-LAST:event_buttonAction5ActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CapaPresentación.admin.swing.ButtonAction buttonAction1;
    private CapaPresentación.admin.swing.ButtonAction buttonAction2;
    private CapaPresentación.admin.swing.ButtonAction buttonAction3;
    private CapaPresentación.admin.swing.ButtonAction buttonAction4;
    private CapaPresentación.admin.swing.ButtonAction buttonAction5;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables
}
