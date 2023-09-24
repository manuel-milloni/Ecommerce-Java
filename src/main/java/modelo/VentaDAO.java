
package modelo;


import java.time.format.DateTimeFormatter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.Conexion;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime dateTime;

    public VentaDAO() {

    }

    public boolean save(Venta v) {
        String sql = "INSERT INTO venta(fechaVenta, idCliente, nroVenta) VALUES(?,?,?)";
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);

            ps.setString(1, v.getFecha().format(formato));
            ps.setInt(2, v.getIdCliente());
            ps.setInt(3, v.getNroVenta());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public List<Venta> getAll() {
        String sql = "SELECT * FROM venta";
        List<Venta> ventas = new ArrayList<>();
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
          
            while (rs.next()) {
                Venta v = new Venta();
                v.setId(rs.getInt(1));
                LocalDateTime fechaVenta = LocalDateTime.parse(rs.getString(2), formato);

                v.setFecha(fechaVenta);
                v.setIdCliente(rs.getInt(3));
                v.setNroVenta(rs.getInt("nroVenta"));
                ventas.add(v);

            }
              ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ventas;

    }

    public Venta getById(int id) {
        String sql = "SELECT * FROM venta WHERE idVenta=id";
        LocalDateTime fecha;
        Venta v = new Venta();
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                v.setId(rs.getInt(1));
                fecha = LocalDateTime.parse(rs.getString(2), formato);
                v.setFecha(fecha);
                v.setIdCliente(rs.getInt(3));
                v.setNroVenta(rs.getInt("nroVenta"));

            }
                        ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return v;

    }

    public void delete(int id) {
        String sql = "DELETE FROM venta WHERE idVenta=?";
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

    public int getMaxNroVenta() {
        String sql = "SELECT MAX(nroVenta) AS nroVenta FROM venta";
        int nroVenta = 1;

        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
           
            while (rs.next()) {

                nroVenta = rs.getInt("nroVenta");
            }
             ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());

        }
        return (nroVenta + 1);

    }

    public int getIdByFechaAndCliente(Venta venta) {
        String sql = "SELECT * FROM venta WHERE  fechaVenta=? AND idCliente=?";
        int id = 0;
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            String fecha = venta.getFecha().format(formato);
            System.out.println("Fecha: " + fecha);
            ps.setString(1, fecha);
            ps.setInt(2, venta.getIdCliente());
            rs = ps.executeQuery();
          
            while (rs.next()) {
                id = rs.getInt("idVenta");

            }
              ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return id;
    }
    
    
    

}
