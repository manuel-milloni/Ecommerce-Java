
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.Conexion;

import java.util.List;
import java.util.ArrayList;

public class EmpleadoDAO {

    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;

    public EmpleadoDAO() {

    }

    public List<Empleado> getAll() {
        String sql = "SELECT * FROM empleado";
        List<Empleado> empleados = new ArrayList<>();
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setId(rs.getInt(1));
                emp.setNombre(rs.getString(2));
                emp.setApellido(rs.getString(3));
                emp.setTelefono(rs.getString(4));
                emp.setEmail(rs.getString(5));
                emp.setDireccion(rs.getString(6));
                emp.setDni(rs.getString(7));
                emp.setIdRol(rs.getInt(8));
                emp.setPassword(rs.getString(9));

                empleados.add(emp);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return empleados;

    }

    public void save(Empleado emp) {
        String sql = "INSERT INTO empleado(nombre, apellido, telefono, email, direccion, dni, idRol, password) VALUES(?,?,?,?,?,?,?,?)";
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getApellido());

            ps.setString(3, emp.getTelefono());
            ps.setString(4, emp.getEmail());
            ps.setString(5, emp.getDireccion());
            ps.setString(6, emp.getDni());
            ps.setInt(7, emp.getIdRol());
            ps.setString(8, emp.getPassword());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    public void update(Empleado c) {
        String sql = "UPDATE empleado SET nombre=?, apellido=?, telefono=?, email=?, direccion=?, dni=?, idRol=? WHERE idEmpleado=?";
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());

            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getDireccion());
            ps.setString(6, c.getDni());
            ps.setInt(7, c.getIdRol());
         
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    public void delete(int id) {
        String sql = "DELETE FROM empleado WHERE idEmpleado=?";
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

    public Empleado getById(int id) {
        String sql = "SELECT * FROM empleado WHERE idEmpleado=?";
        Empleado emp = new Empleado();
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {

                emp.setId(rs.getInt(1));
                emp.setNombre(rs.getString(2));
                emp.setApellido(rs.getString(3));
                emp.setTelefono(rs.getString(4));
                emp.setEmail(rs.getString(5));
                emp.setDireccion(rs.getString(6));
                emp.setDni(rs.getString(7));
                emp.setIdRol(rs.getInt(8));
                emp.setPassword(rs.getString(9));

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return emp;

    }

    public Empleado getByEmail(String email) {
        String sql = "SELECT * FROM empleado WHERE email=?";
        Empleado emp = null;
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                emp=new Empleado();
                emp.setId(rs.getInt("idEmpleado"));
                emp.setNombre(rs.getString("nombre"));
                emp.setApellido(rs.getString("apellido"));
                emp.setTelefono(rs.getString("telefono"));
                emp.setEmail(rs.getString("email"));
                emp.setDireccion(rs.getString("direccion"));
                emp.setDni(rs.getString("dni"));
                emp.setIdRol(rs.getInt("idRol"));
                emp.setPassword(rs.getString("password"));

            }
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return emp;

    }

}
