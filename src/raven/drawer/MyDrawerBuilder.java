package raven.drawer;

import CapaPresentación.admin.DashboardForm;

import CapaPresentación.admin.frmhabitacion;
import com.sun.tools.javac.Main;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.MenuValidation;
import raven.drawer.component.menu.SimpleMenuOption;
import raven.swing.AvatarIcon;
import raven.tabbed.WindowsTabbed;

public class MyDrawerBuilder extends SimpleDrawerBuilder {

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        return new SimpleHeaderData()
                .setIcon(new AvatarIcon(getClass().getResource("/raven/drawer/profile.png"), 60, 60, 999))
                .setTitle("ADMINISTRADOR")
                .setDescription("SuperUser");
    }

    @Override
    public SimpleMenuOption getSimpleMenuOption() {
        String menus[][] = {
            {"~PANEL PRINCIPAL~"},
            {"Dashboard"},
            {"~OPERACIONES~"},
            {"Nueva Reserva", "Consultar Reservas", "Chek-in", "Chek-out", "Calendario Ocupación"},
            {"Hospedaje", "Listado y Registro de \nHabitaciones"},
            {"Servicio y Consumos", "Registrar Consumo","Gestión de Productos"},
            {"~ADMINISTRACION~"},
            {"Clientes", "Listado de Clientes", "Nuevo Cliente"},
            {"Empleados", "Lista de Empleados", "Nuevo Empleado", "Gestion de Roles"},
            {"Finanzas y Pagos","Registrar Pago","Historial de Pagos","Facturación"},
            {"~REPORTES~"},
            {"Reportes de Ocupacion", "Reportes de Ingreso", "Reportes de CLientes"},
            {"Logout"}};

        String icons[] = {
            "dashboard.svg",
            "calendar.svg",
            "email.svg",
            "service.svg",
            "client.svg",
            "forms.svg",
            "chart.svg",
            "reports.svg",
            "logout.svg"};

        return new SimpleMenuOption()
                .setMenus(menus)
                .setIcons(icons)
                .setBaseIconPath("raven/drawer/icon")
                .setIconScale(0.45f)
                .addMenuEvent(new MenuEvent() {
                    @Override
                    public void selected(MenuAction action, int index, int subIndex) {
                        if (index == 0) {
                            WindowsTabbed.getInstance().addTab("Test Form", new DashboardForm());   
                        }
                        if (subIndex == 1) {
                            WindowsTabbed.getInstance().addTab("Habitaciones", new frmhabitacion());
                        }
                        System.out.println("Menu selected " + index + " " + subIndex);
                    }
                })
                .setMenuValidation(new MenuValidation() {
                    @Override
                    public boolean menuValidation(int index, int subIndex) {
//                        if (index == 0) {
//                            return false;
//                        } else if (index == 3) {
//                            return false;
//                        }
                        return true;
                    }
                });
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData()
                .setTitle("HoteLix")
                .setDescription("Version 1.1.0");
    }

}
