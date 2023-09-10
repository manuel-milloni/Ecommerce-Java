
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LineaVentaDAO {

    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;

    public LineaVentaDAO() {

    }

    public void save(LineaVenta lv) {
        String sql = "INSERT INTO linea_de_venta(cantProducto,nroVenta,idProducto) VALUES(?,?,?) ";
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, lv.getCantProducto());
            ps.setInt(2, lv.getNroVenta());
            ps.setInt(3, lv.getIdProducto());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

}
