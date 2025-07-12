
package CapaPresentación;

import CapaPresentación.LoginRegistro.frmLogin;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Font;
import javax.swing.UIManager;
import raven.tabbed.WindowsTabbed;
import CapaPresentación.admin.frmMenu; 


public class aplicacion {
    public static void main(String[] args) {
        
        FlatLaf.registerCustomDefaultsSource("raven.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup(); 
        frmLogin inicio = new frmLogin(); 
        inicio.setVisible(true);
        inicio.setLocationRelativeTo(null);
    }
}
