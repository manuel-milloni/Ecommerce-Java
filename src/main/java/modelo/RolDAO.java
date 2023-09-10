
package modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

import config.Conexion;


public class RolDAO {

    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;

    public RolDAO() {

    }

    public List<Rol> getAll() {
        String sql = "SELECT * FROM rol";
        List<Rol> roles = new ArrayList<>();
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Rol rol = new Rol();
                rol.setId(rs.getInt("idRol"));
                rol.setDescripcion(rs.getString("descripcion"));
                roles.add(rol);

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return roles;
    }

    public Rol getById(int id) {
        String sql = "SELECT * FROM rol WHERE idRol=?";
        Rol rol = new Rol();
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                rol.setId(rs.getInt("idRol"));
                rol.setDescripcion(rs.getString("descripcion"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return rol;

    }

}
