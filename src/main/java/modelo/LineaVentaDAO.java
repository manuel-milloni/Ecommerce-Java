
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class LineaVentaDAO {

    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;

    public LineaVentaDAO() {

    }

    public void save(LineaVenta lv) {
        String sql = "INSERT INTO linea_de_venta(cantProducto,nroVenta,idProducto, precio) VALUES(?,?,?,?); ";
        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, lv.getCantProducto());
            ps.setInt(2, lv.getNroVenta());
            ps.setInt(3, lv.getIdProducto());
            ps.setDouble(4, lv.getPrecio());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }
    
    public ArrayList<LineaVenta> getAllByVenta(int nroVenta){
    	 String sql="SELECT * FROM linea_de_venta WHERE nroVenta=?;";
    	 ArrayList<LineaVenta> lineas= new ArrayList<>();
    	 try {
    		 Conexion con=new Conexion();
    		 conexion=con.getConexion();
    		 ps=conexion.prepareStatement(sql);
    		 ps.setInt(1, nroVenta);
    		 rs=ps.executeQuery();
    		 while(rs.next()) {
    			  LineaVenta lv=new LineaVenta();
    			  lv.setIdLineaVenta(rs.getInt("idLineaVenta"));
    			  lv.setCantProducto(rs.getInt("cantProducto"));
    			  lv.setNroVenta(rs.getInt("nroVenta"));
    			  lv.setIdProducto(rs.getInt("idProducto"));
    			  lv.setPrecio(rs.getDouble("precio"));
    			  lineas.add(lv);
    			 
    		 }
    		 
    		 
    	 } catch(SQLException e) {
    		  System.out.println(e.toString());
    		 
    	 }
    	 
    	 return lineas;
    	
    	
    }

}
