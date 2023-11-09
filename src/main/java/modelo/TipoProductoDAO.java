
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.Conexion;
import java.util.ArrayList;
import java.util.List;

public class TipoProductoDAO {

    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;

    public TipoProductoDAO() {

    }

    public void save(String descripcion) {
        String sql = "INSERT INTO tipo_producto(descripcion) values(?)";
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();

            ps = conexion.prepareStatement(sql);
            ps.setString(1, descripcion);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    public List<TipoProducto> getAll() {
        String sql = "SELECT * FROM tipo_producto ORDER BY idTipo ASC";
        List<TipoProducto> tiposProductos = new ArrayList<>();
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();

            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
           
            while (rs.next()) {
                TipoProducto tp = new TipoProducto();
                tp.setIdTipo(rs.getInt(1));
                tp.setDescripcion(rs.getString(2));
                tiposProductos.add(tp);
            }
             ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return tiposProductos;

    }

    public TipoProducto getById(int id) {
        String sql = "SELECT * FROM tipo_producto WHERE idTipo=?";
        TipoProducto tp = new TipoProducto();
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();

            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
          
            while (rs.next()) {

                tp.setIdTipo(rs.getInt(1));
                tp.setDescripcion(rs.getString(2));

            }
              ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return tp;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM tipo_producto WHERE idTipo=?";
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();

            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }

    }

    public void update(TipoProducto tp) {
        String sql = "UPDATE tipo_producto SET descripcion=? WHERE idTipo=?";
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();

            ps = conexion.prepareStatement(sql);
            ps.setString(1, tp.getDescripcion());
            ps.setInt(2, tp.getIdTipo());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

}
