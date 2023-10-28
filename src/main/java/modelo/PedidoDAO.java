package modelo;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import config.Conexion;

public class PedidoDAO {
	  private Connection conexion;
	  private ResultSet rs;
	  private PreparedStatement ps;
	  private DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	    
	public PedidoDAO() {
		
	}
	
	public boolean save(Pedido pedido) {
		 String sql="INSERT INTO pedido(fechaPedido, idEmpleado,idProveedor, nroPedido) VALUES(?,?,?,?);";
		 try {
			 Conexion con= new Conexion();
			 conexion=con.getConexion();
			 ps=conexion.prepareStatement(sql);
			 
			 String fecha=pedido.getFecha().format(formato);
			 ps.setString( 1, fecha);
			 ps.setInt(2, pedido.getEmpleado().getId());
			 ps.setInt(3, pedido.getProveedor().getIdProveedor());
			 ps.setInt(4,  pedido.getNroPedido());
			 ps.executeUpdate();
			 ps.close();
			 return true;
			 
		 } catch(SQLException e) {
			  System.out.println(e.toString());
			  return false;
		 }
		
		
	}
	
	public List<Pedido> getAll(){
		    String sql="SELECT * FROM pedido;";
		    List<Pedido> pedidos=new ArrayList<>();
		    LocalDate fecha;
		    try {
		    	 Conexion con= new Conexion();
				 conexion=con.getConexion();
				 ps=conexion.prepareStatement(sql);
				 rs=ps.executeQuery();
				 while(rs.next()) {
					 Pedido pedido=new Pedido();
					 pedido.setIdPedido(rs.getInt("idPedido"));
					 fecha=LocalDate.parse(rs.getString("fechaPedido"));
					 pedido.setFecha(fecha);
					 Empleado emp=new Empleado();
					 emp.setId(rs.getInt("idempleado"));
					 pedido.setEmpleado(emp);
					 Proveedor proveedor=new Proveedor();
					 proveedor.setIdProveedor(rs.getInt("idProveedor"));
					 pedido.setProveedor(proveedor);
					 pedido.setNroPedido(rs.getInt("NroPedido"));
					 pedidos.add(pedido);
					 
					 
				 }
				 ps.close();
		    	
		    }catch(SQLException e) {
		    	 System.out.println(e.toString());
		    	
		    }
		    return pedidos;
		
		
	}
	
	public boolean delete(int id) {
		 String sql="DELETE FROM pedido WHERE idPedido=?;";
		 try {
			  Conexion con=new Conexion();
			  conexion=con.getConexion();
			  ps=conexion.prepareStatement(sql);
			  ps.executeUpdate();
			  ps.close();
			  return true;
			 
		 } catch(SQLException e) {
			  System.out.println(e.toString());
			  return false;
		 }
		
		
	}
	
    public int getMaxNroPedido() {
        String sql = "SELECT MAX(nroPedido) AS nroPedido FROM pedido";
        int nroPedido = 1;

        try {
            Conexion con = new Conexion();
            conexion = con.getConexion();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
           
            while (rs.next()) {

                nroPedido = rs.getInt("nroPedido");
            }
             ps.close();
             return (nroPedido + 1);

        } catch (SQLException e) {
            System.out.println(e.toString());
            return -1;

        }
       

    }
    
	public List<Pedido> getByProveedor(String razonSocial){
	    String sql="SELECT * FROM pedido INNER JOIN proveedor ON pedido.idProveedor=proveedor.idProveedor WHERE razonSocial LIKE '%"+razonSocial+"%';";
	    List<Pedido> pedidos=new ArrayList<>();
	    LocalDate fecha;
	    try {
	    	 Conexion con= new Conexion();
			 conexion=con.getConexion();
			 ps=conexion.prepareStatement(sql);
			 rs=ps.executeQuery();
			 while(rs.next()) {
				 Pedido pedido=new Pedido();
				 pedido.setIdPedido(rs.getInt("idPedido"));
				 fecha=LocalDate.parse(rs.getString("fechaPedido"));
				 pedido.setFecha(fecha);
				 Empleado emp=new Empleado();
				 emp.setId(rs.getInt("idEmpleado"));
				 pedido.setEmpleado(emp);
				 Proveedor proveedor=new Proveedor();
				 proveedor.setIdProveedor(rs.getInt("pedido.idProveedor"));
				 pedido.setProveedor(proveedor);
				 pedido.setNroPedido(rs.getInt("NroPedido"));
				 pedidos.add(pedido);
				 
				 
			 }
			 ps.close();
	    	
	    }catch(SQLException e) {
	    	 System.out.println("Error en getByProveedor: "+e.toString());
	    	
	    }
	    return pedidos;
	
	
}
	
	
	public Pedido getById(int id){
	    String sql="SELECT * FROM pedido WHERE idPedido=?;";
	    Pedido pedido=null;
	    LocalDate fecha;
	    try {
	    	 Conexion con= new Conexion();
			 conexion=con.getConexion();
			 ps=conexion.prepareStatement(sql);
			 ps.setInt(1, id);
			 rs=ps.executeQuery();
			 while(rs.next()) {
				 pedido=new Pedido();
				 pedido.setIdPedido(rs.getInt("idPedido"));
				 fecha=LocalDate.parse(rs.getString("fechaPedido"));
				 pedido.setFecha(fecha);
				 Empleado emp=new Empleado();
				 emp.setId(rs.getInt("idempleado"));
				 pedido.setEmpleado(emp);
				 Proveedor proveedor=new Proveedor();
				 proveedor.setIdProveedor(rs.getInt("idProveedor"));
				 pedido.setProveedor(proveedor);
				 pedido.setNroPedido(rs.getInt("NroPedido"));
				
			 }
			 ps.close();
	    	
	    }catch(SQLException e) {
	    	 System.out.println(e.toString());
	    	
	    }
	    return pedido;
		 
	
	
}
    
    
	

	

}
