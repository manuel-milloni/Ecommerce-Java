
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.Conexion;

import java.util.List;
import java.util.ArrayList;

public class ProductoDAO {

    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;

    public ProductoDAO() {

    }

    public List<Producto> getAll() {
        String sql = "SELECT * FROM producto";
        List<Producto> productos = new ArrayList<>();
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
          
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setIdTipo(rs.getInt("idTipo"));
                p.setStock(rs.getInt("stock"));
                p.setImagen(rs.getString("imagen"));

                productos.add(p);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return productos;
    }

    public void save(Producto p) {
        String sql = "INSERT INTO producto(descripcion, precio, idTipo, stock, imagen) VALUES(?,?,?,?,?)";
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, p.getDescripcion());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getIdTipo());
            ps.setInt(4, p.getStock());
            ps.setString(5, p.getImagen());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    
       //Devuelve lista tipo Carrito completando cada elemento con los datos del producto
    public List<Carrito> getCarritoProductos(ArrayList<Carrito> listaCarrito) {
        List<Carrito> productos = new ArrayList<>();

        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            if (!listaCarrito.isEmpty()) {
                for (Carrito item : listaCarrito) {
                    String sql = "SELECT * FROM producto WHERE idProducto=?";
                    ps = conexion.prepareStatement(sql);
                    ps.setInt(1, item.getIdProducto());
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        Carrito c = new Carrito();
                        c.setIdProducto(rs.getInt("idProducto"));
                        c.setDescripcion(rs.getString("descripcion"));
                        c.setIdTipo(rs.getInt("idTipo"));
                        c.setCantidad(1);
                        c.setPrecio(rs.getDouble("precio"));
                        c.setStock(rs.getInt("stock"));
                        c.setCantidad(item.getCantidad());
                        productos.add(c);

                    }
                    ps.close();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return productos;

    }

    public double getTotal(ArrayList<Carrito> listaCarrito) {
        double total = 0;

        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            if (!listaCarrito.isEmpty()) {
                for (Carrito c : listaCarrito) {
                    String sql = "SELECT * FROM producto WHERE idProducto=?";
                    ps = conexion.prepareStatement(sql);
                    ps.setInt(1, c.getIdProducto());
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        total = total + (c.getCantidad() * rs.getDouble("precio"));

                    }
                    ps.close();

                }

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return total;

    }

    public Producto getById(int id) {
        String sql = "SELECT * FROM producto WHERE idProducto=?";
        Producto producto = null;
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setIdTipo(rs.getInt("idTipo"));
                producto.setStock(rs.getInt("stock"));
                producto.setImagen(rs.getString("imagen"));

            }
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return producto;

    }

    public void updateStock(int id, int stock) {
        String sql = "UPDATE producto SET stock=? WHERE idProducto=?";
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    public List<Producto> getByDescripcion(String desc) {
        String sql = "SELECT * FROM producto WHERE descripcion LIKE '%" + desc + "%'";
        List<Producto> productos = new ArrayList<>();
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setIdTipo(rs.getInt("idTipo"));
                p.setStock(rs.getInt("stock"));
                p.setImagen(rs.getString("imagen"));
                productos.add(p);

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return productos;
    }
    
    
    public void update(Producto p){
       String sql="UPDATE producto SET descripcion=?, precio=?, idTipo=?, stock=? WHERE idProducto=?";
       try{
         Conexion con=new Conexion();
         conexion=con.getConexion();
         ps=conexion.prepareStatement(sql);
         ps.setString(1, p.getDescripcion());
         ps.setDouble(2, p.getPrecio());
         ps.setInt(3, p.getIdTipo());
         ps.setInt(4, p.getStock());
         ps.setInt(5, p.getIdProducto());
         ps.executeUpdate();
         ps.close();
                 
       } catch(SQLException e){
           System.out.println(e.toString());
       }
        
        
    }

}
