package CapaPresentacion.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import raven.crazypanel.CrazyPanel;
import raven.crazypanel.FlatLafStyleComponent;
import raven.crazypanel.MigLayoutConstraints;
import com.formdev.flatlaf.extras.FlatSVGIcon;

public class CardPanel extends CrazyPanel {

    private JLabel iconLabel;
    private JLabel valueLabel;
    private JLabel descriptionLabel;

    public CardPanel() {
        initComponents();
    }

    private void initComponents() {
        // Estilo de la tarjeta (crazyPanel2 de tu ejemplo)
        setFlatLafStyleComponent(new FlatLafStyleComponent(
                "arc:20;[light]background:lighten(@background,3%);[dark]background:darken(@background,3%)",
                new String[]{
                    // Estilo para el icono
                    "", // El primer componente que agregues tendrá este estilo
                    // Estilo para la descripción
                    "[light]foreground:tint(@foreground,50%);[dark]foreground:shade(@foreground,50%)", // Segundo componente
                    // Estilo para el valor
                    "font:bold +3" // Tercer componente
                }
        ));
        setMigLayoutConstraints(new MigLayoutConstraints(
                "inset s 10 10 10 15",
                "[]20 push[trail]", // Icono, espacio, push (empuja a la derecha), valor
                "[]0[]", // Dos filas, 0 espacio entre ellas (descripción y valor)
                new String[]{
                    "span 1 2", // El icono abarca 2 filas
                    "wrap", // La descripción termina la fila
                    "ay top" // La descripción se alinea arriba
                }
        ));

        iconLabel = new JLabel();
        valueLabel = new JLabel();
        descriptionLabel = new JLabel();

        add(iconLabel);
        add(descriptionLabel);
        add(valueLabel);
    }

    public void setCardData(FlatSVGIcon icon, String value, String description) { // <--- CAMBIO AQUÍ
        if (icon != null) {
            iconLabel.setIcon(icon);
        } else {
            iconLabel.setIcon(null);
            iconLabel.setText("?"); // Muestra un '?' si el ícono no se cargó
        }
        valueLabel.setText(value);
        descriptionLabel.setText(description);
    }
}
