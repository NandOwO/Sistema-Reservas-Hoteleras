package CapaLogica;

import CapaDatos.vproducto;
import EstructuraDatos.ArbolBinarioProducto;
import EstructuraDatos.ArregloProducto;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class fproducto {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;

    //Integracion de las estructuras de datos
    private ArregloProducto arreglo = new ArregloProducto(200);
    private ArbolBinarioProducto arbol = new ArbolBinarioProducto();

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Nombre", "Descripción", "Unidad", "Precio"};
        String[] registro = new String[5];
        totalregistros = 0;

        modelo = new DefaultTableModel(null, titulos);

        sSQL = "SELECT * FROM producto WHERE nombre LIKE '%" + buscar + "%' ORDER BY idproducto";

        try {
            Statement st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            arreglo.limpiar();
            arbol.limpiar();

            while (rs.next()) {
                registro[0] = rs.getString("idproducto");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("descripcion");
                registro[3] = rs.getString("unidad_medida");
                registro[4] = rs.getString("precio_venta");

                modelo.addRow(registro);
                totalregistros++;

                // llenar estructura
                vproducto prod = new vproducto();
                prod.setIdproducto(Integer.parseInt(rs.getString("idproducto")));
                prod.setNombre(rs.getString("nombre"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setUnidad_medida(rs.getString("unidad_medida"));
                prod.setPrecio_venta(Double.parseDouble(rs.getString("precio_venta")));

                arreglo.agregar(prod);
                arbol.insertar(prod);

            }

            return modelo;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertar(vproducto dts) {
        sSQL = "INSERT INTO producto (nombre, descripcion, unidad_medida, precio_venta) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, dts.getUnidad_medida());
            pst.setDouble(4, dts.getPrecio_venta());

            if (pst.executeUpdate() != 0) {
                arreglo.agregar(dts);
                arbol.insertar(dts);
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(vproducto dts) {
        sSQL = "UPDATE producto SET nombre=?, descripcion=?, unidad_medida=?, precio_venta=? WHERE idproducto=?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, dts.getUnidad_medida());
            pst.setDouble(4, dts.getPrecio_venta());
            pst.setInt(5, dts.getIdproducto());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarPorNombre(String nombre) {
        sSQL = "DELETE FROM producto WHERE nombre = ?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, nombre);

            if (pst.executeUpdate() != 0) {
                arreglo.eliminar(nombre);

                arbol.limpiar();
                for (int i = 0; i < arreglo.getTotal(); i++) {
                    vproducto p = arreglo.buscarPorIndice(i);
                    if (p != null) {
                        arbol.insertar(p);
                    }
                }

                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public vproducto buscarEnArbol(String nombre) {
        return arbol.buscar(nombre);
    }

}
