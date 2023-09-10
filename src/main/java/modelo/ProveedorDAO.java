
package modelo;

import config.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

public class ProveedorDAO {

    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;

    public ProveedorDAO() {

    }

    public void save(Proveedor prov) {
        String sql = "INSERT INTO proveedor(cuit, razonSocial, telefono, email) VALUES (?,?,?,?)";

        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, prov.getCuit());
            ps.setString(2, prov.getRazonSocial());
            ps.setString(3, prov.getTelefono());
            ps.setString(4, prov.getEmail());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    public List<Proveedor> getAll() {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedor";
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Proveedor prov = new Proveedor();
                prov.setIdProveedor(rs.getInt("idProveedor"));
                prov.setCuit(rs.getString("cuit"));
                prov.setRazonSocial(rs.getString("razonSocial"));
                prov.setTelefono(rs.getString("telefono"));
                prov.setEmail(rs.getString("email"));
                proveedores.add(prov);

            }
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return proveedores;
    }

    public Proveedor getById(int id) {
        String sql = "SELECT * FROM proveedor WHERE idProveedor=?";
        Proveedor prov = new Proveedor();
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {

                prov.setIdProveedor(rs.getInt("idProveedor"));
                prov.setCuit(rs.getString("cuit"));
                prov.setRazonSocial(rs.getString("razonSocial"));
                prov.setTelefono(rs.getString("telefono"));
                prov.setEmail(rs.getString("email"));

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return prov;
    }

    public void delete(int id) {
        String sql = "DELETE FROM proveedor WHERE idProveedor=?";
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

    public void update(Proveedor prov) {
        String sql = "UPDATE proveedor SET cuit=?, razonSocial=?, telefono=?, email=? WHERE idProveedor=?";
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, prov.getCuit());
            ps.setString(2, prov.getRazonSocial());
            ps.setString(3, prov.getTelefono());
            ps.setString(4, prov.getEmail());
            ps.setInt(5, prov.getIdProveedor());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public Proveedor getByCuit(String cuit) {
        String sql = "SELECT * FROM proveedor WHERE cuit=?";
        Proveedor prov = new Proveedor();
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, cuit);
            rs = ps.executeQuery();

            while (rs.next()) {
                prov.setIdProveedor(rs.getInt("idProveedor"));
                prov.setCuit(rs.getString("cuit"));
                prov.setRazonSocial(rs.getString("razonSocial"));
                prov.setTelefono(rs.getString("telefono"));
                prov.setEmail(rs.getString("email"));

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return prov;

    }

}
