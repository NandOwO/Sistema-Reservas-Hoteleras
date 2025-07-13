/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package CapaPresentación.admin;

import CapaPresentacion.component.CardPanel;
import raven.tabbed.TabbedForm;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;


import raven.crazypanel.CrazyPanel;
import raven.crazypanel.FlatLafStyleComponent;
import raven.crazypanel.MigLayoutConstraints;

import com.formdev.flatlaf.extras.FlatSVGIcon;

/**
 *
 * @author FERNANDO
 */
public class DashboardForm extends TabbedForm {

    private JLabel titleLabel;
    private JLabel welcomeLabel;

    private CrazyPanel kpiPanel;
    private CardPanel availableRoomsCard;
    private CardPanel currentOccupancyCard;
    private CardPanel checkinsTodayCard;
    private CardPanel dailyRevenueCard;
    private CrazyPanel detailsPanel;


    private JPanel arrivalsDeparturesPanel;
    private JPanel roomStatusPanel;

    private CrazyPanel headerCrazyPanel;


    public DashboardForm() {
        initComponents();
        initCustomComponents();
        initData();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();

        jScrollPane2.setViewportView(jEditorPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1020, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 622, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
private void initCustomComponents() {
        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(new Color(0xFFFFFF));


        CrazyPanel headerCrazyPanel = new CrazyPanel();
        
        headerCrazyPanel.setMigLayoutConstraints(new MigLayoutConstraints(
                "fill, wrap 1",
                "[]",
                "[]10[]",
                null
        ));

        titleLabel = new JLabel("Dashboard del Hotelix");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        titleLabel.setForeground(new Color(0x4B4E6D)); // Ultra Violet
        headerCrazyPanel.add(titleLabel);

        welcomeLabel = new JLabel("<html>Bienvenido, Fernando Díaz (Admin)<br><small>Resumen de la operación del hotel</small></html>");
        welcomeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        welcomeLabel.setForeground(new Color(0x95A3B3));
        headerCrazyPanel.add(welcomeLabel);
        add(headerCrazyPanel, BorderLayout.NORTH);

        kpiPanel = new CrazyPanel();
        kpiPanel.setFlatLafStyleComponent(new FlatLafStyleComponent(
                "arc:20;[light]background:shade(@background,2%);[dark]background:tint(@background,2%)",
                null
        ));
        // Corrección 4: MigLayoutConstraints constructor
        kpiPanel.setMigLayoutConstraints(new MigLayoutConstraints(
                "wrap 4, insets 10",
                "fill, sg card",
                "fill",
                null
        ));

        availableRoomsCard = new CardPanel();
        kpiPanel.add(availableRoomsCard);

        currentOccupancyCard = new CardPanel();
        kpiPanel.add(currentOccupancyCard);

        // Corrección 5: Nombre de variable
        checkinsTodayCard = new CardPanel();
        kpiPanel.add(checkinsTodayCard);

        dailyRevenueCard = new CardPanel();
        kpiPanel.add(dailyRevenueCard);

        add(kpiPanel, BorderLayout.CENTER);

        detailsPanel = new CrazyPanel();
        detailsPanel.setFlatLafStyleComponent(new FlatLafStyleComponent(
                "arc:20;[light]background:shade(@background,2%);[dark]background:tint(@background,2%)",
                null
        ));
        detailsPanel.setMigLayoutConstraints(new MigLayoutConstraints(
                "wrap 2, insets 10",
                "push[fill, 50%]push[fill, 50%]push",
                "fill", 
                null 
        ));

        arrivalsDeparturesPanel = new JPanel(new BorderLayout(10, 10));
        arrivalsDeparturesPanel.setOpaque(false);
        JLabel arrivalsTitle = new JLabel("Próximos Check-ins y Check-outs");
        arrivalsTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        arrivalsTitle.setForeground(new Color(0x222222)); // Eerie Black
        arrivalsDeparturesPanel.add(arrivalsTitle, BorderLayout.NORTH);
        arrivalsDeparturesPanel.add(new JLabel("<< Tabla de Próximas Reservas >>", JLabel.CENTER), BorderLayout.CENTER);
        detailsPanel.add(arrivalsDeparturesPanel);

        roomStatusPanel = new JPanel(new BorderLayout(10, 10));
        roomStatusPanel.setOpaque(false);
        JLabel roomStatusTitle = new JLabel("Estado de Habitaciones");
        roomStatusTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        roomStatusTitle.setForeground(new Color(0x222222)); // Eerie Black
        roomStatusPanel.add(roomStatusTitle, BorderLayout.NORTH);
        roomStatusPanel.add(new JLabel("<< Gráfico/Lista de Estado de Habitaciones >>", JLabel.CENTER), BorderLayout.CENTER);
        detailsPanel.add(roomStatusPanel);

        add(detailsPanel, BorderLayout.SOUTH);
    }

    private void initData() {

        java.net.URL roomsIconUrl = getClass().getResource("/raven/drawer/icon/bedroom.svg");
        java.net.URL occupancyIconUrl = getClass().getResource("/raven/drawer/icon/chart.svg"); 
        java.net.URL checkinIconUrl = getClass().getResource("/raven/drawer/icon/chek-in.svg");   
        java.net.URL moneyIconUrl = getClass().getResource("/raven/drawer/icon/money.svg");    

        System.out.println("URL para roomsIcon: " + roomsIconUrl);
        System.out.println("URL para occupancyIcon: " + occupancyIconUrl);
        System.out.println("URL para checkinIcon: " + checkinIconUrl);
        System.out.println("URL para moneyIcon: " + moneyIconUrl);

        FlatSVGIcon roomsIcon = null;
        FlatSVGIcon occupancyIcon = null;
        FlatSVGIcon checkinIcon = null;
        FlatSVGIcon moneyIcon = null;

        try {
            if (roomsIconUrl != null) {
                roomsIcon = new FlatSVGIcon(roomsIconUrl);
                roomsIcon.derive(1f); // Escalar al 100%
            } else {
                System.err.println("ERROR: No se encontró roomsIcon en /nando/drawer/incon/dashboard.svg");
            }

            if (occupancyIconUrl != null) {
                occupancyIcon = new FlatSVGIcon(occupancyIconUrl);
                occupancyIcon.derive(1f);
            } else {
                System.err.println("ERROR: No se encontró occupancyIcon en /nando/drawer/incon/chart.svg");
            }

            if (checkinIconUrl != null) {
                checkinIcon = new FlatSVGIcon(checkinIconUrl);
                checkinIcon.derive(1f);
            } else {
                System.err.println("ERROR: No se encontró checkinIcon en /nando/drawer/incon/email.svg");
            }

            if (moneyIconUrl != null) {
                moneyIcon = new FlatSVGIcon(moneyIconUrl);
                moneyIcon.derive(1f);
            } else {
                System.err.println("ERROR: No se encontró moneyIcon en /nando/drawer/incon/chat.svg");
            }

        } catch (Exception e) {
            System.err.println("Excepción al cargar ícono SVG: " + e.getMessage());
            e.printStackTrace();
        }

        // Asegúrate de que CardPanel.setCardData acepta FlatSVGIcon
        availableRoomsCard.setCardData(roomsIcon, "15", "Habitaciones Disponibles");
        currentOccupancyCard.setCardData(occupancyIcon, "85%", "Ocupación Actual");
        checkinsTodayCard.setCardData(checkinIcon, "7", "Check-ins Hoy");
        dailyRevenueCard.setCardData(moneyIcon, "S/ 12,500", "Ingresos del Día");
    }

    public void refreshDashboardData() {
        initData();
    }

    @Override
    public boolean formClose() {
        return true;
    }

    @Override
    public void formOpen() {
        refreshDashboardData();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
