
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;

    public CategoriaDAO() {

    }

    public List<Categoria> getAll() {
        String sql = "SELECT * FROM categoria";
        List<Categoria> categorias = new ArrayList<>();
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setId(rs.getInt(1));
                cat.setDescripcion(rs.getString(2));
                categorias.add(cat);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return categorias;

    }

    public void save(String descripcion) {
        String sql = "INSERT INTO categoria(descripcion) VALUES(?)";
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

    public void update(Categoria cat) {
        String sql = "UPDATE categoria SET descripcion=? WHERE idCategoria=?";
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, cat.getDescripcion());
            ps.setInt(2, cat.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    public void delete(int id) {
        String sql = "DELETE FROM categoria WHERE idCategoria=?";
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public Categoria getById(int id) {
        String sql = "SELECT * FROM categoria WHERE idCategoria=?";
        Categoria cat = new Categoria();
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while (rs.next()) {

                cat.setId(rs.getInt(1));
                cat.setDescripcion(rs.getString(2));

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return cat;

    }

}
