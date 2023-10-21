
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;

    public ClienteDAO() {

    }

    public List<Cliente> getAll() {
        String sql = "SELECT * FROM cliente";
        List<Cliente> clientes = new ArrayList<>();
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setId(rs.getInt(1));
                cli.setNombre(rs.getString(2));
                cli.setApellido(rs.getString(3));
                cli.setTelefono(rs.getString(4));
                cli.setEmail(rs.getString(5));
                cli.setDireccion(rs.getString(6));
                cli.setCuit(rs.getString(7));
                cli.setIdCategoria(rs.getInt(8));
                cli.setPassword(rs.getString(9));

                clientes.add(cli);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return clientes;

    }

    public boolean save(Cliente c) {
        String sql = "INSERT INTO cliente(nombre, apellido, telefono, email, direccion, cuit, idCategoria, password) VALUES(?,?,?,?,?,?,?,?)";
        
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());

            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getDireccion());
            ps.setString(6, c.getCuit());
            ps.setInt(7, c.getIdCategoria());
            ps.setString(8, c.getPassword());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return false;

    }

    public void update(Cliente c) {
        String sql = "UPDATE cliente SET nombre=?, apellido=?, telefono=?, email=?, direccion=?, cuit=?, idCategoria=? WHERE idCliente=?";
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());

            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getDireccion());
            ps.setString(6, c.getCuit());
            ps.setInt(7, c.getIdCategoria());
       
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    public void delete(int id) {
        String sql = "DELETE FROM cliente WHERE idCliente=?";
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

    public Cliente getById(int id) {
        String sql = "SELECT * FROM cliente WHERE idCliente=?";
        Cliente cli = null;
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                cli = new Cliente();
                cli.setId(rs.getInt("idCliente"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApellido(rs.getString("apellido"));
                cli.setTelefono(rs.getString("telefono"));
                cli.setEmail(rs.getString("email"));
                cli.setDireccion(rs.getString("direccion"));
                cli.setCuit(rs.getString("cuit"));
                cli.setIdCategoria(rs.getInt("idCategoria"));
                cli.setPassword(rs.getString("password"));

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return cli;

    }

    public Cliente login(String email) {
        String sql = "SELECT * FROM cliente WHERE email=?";
        Cliente cli = null;
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, email);
           
            rs = ps.executeQuery();

            while (rs.next()) {
              
                cli = new Cliente();
                cli.setId(rs.getInt("idCliente"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApellido(rs.getString("apellido"));
                cli.setTelefono(rs.getString("telefono"));
                cli.setEmail(rs.getString("email"));
                cli.setDireccion(rs.getString("direccion"));
                cli.setCuit(rs.getString("cuit"));
                cli.setIdCategoria(rs.getInt("idCategoria"));
                cli.setPassword(rs.getString("password"));

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return cli;
    }

}

