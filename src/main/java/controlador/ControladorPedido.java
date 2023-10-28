package controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Carrito;
import modelo.Empleado;
import modelo.EmpleadoDAO;

import modelo.LineaPedido;
import modelo.LineaPedidoDAO;
import modelo.Pedido;
import modelo.PedidoDAO;
import modelo.Proveedor;
import modelo.ProveedorDAO;


@WebServlet("/ControladorPedido")
public class ControladorPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   String menu = request.getParameter("menu");
	        String accion = request.getParameter("accion");
	        if(menu.equals("pedido")){
	        	  switch(accion) {
	        	  case "pedido":{
	        		  request.setAttribute("buscador", request.getParameter("buscador"));
	        		   request.getRequestDispatcher("admin/pedido.jsp").forward(request, response) ;
	        		  break;
	        	  }
	        	  
	        	  case "nuevoPedido":{
	        		  
	        		 
	        		
	        		  request.setAttribute("buscador", request.getParameter("buscador"));
	        		   request.getRequestDispatcher("admin/nuevoPedido.jsp").forward(request, response);
	        		 
	        		   break;
	        		  
	        	  }
	        	  
	        	  case "detalle":{
	        		    int id=Integer.parseInt(request.getParameter("id"));
	        		    PedidoDAO pDAO=new PedidoDAO();
	        		   
	        		    Pedido pedido=pDAO.getById(id);
	        		    EmpleadoDAO eDAO= new EmpleadoDAO();
	        		    ProveedorDAO provDAO=new ProveedorDAO();
	        		    LineaPedidoDAO lpDAO=new LineaPedidoDAO();
	        		   
	        		  
	        		    pedido.setEmpleado(eDAO.getById(pedido.getEmpleado().getId()));
	        		    pedido.setProveedor(provDAO.getById(pedido.getProveedor().getIdProveedor()));
	        		    pedido.setLineasPedido(lpDAO.getAllByPedido(pedido.getNroPedido()));
	        		    request.setAttribute("pedido", pedido);
	        		    request.getRequestDispatcher("admin/detallePedido.jsp").forward(request, response);
	        		    break;
	        		    
	        		  
	        	  }
	        	   
	        	  
	        	  
	        	  }
	        	
	        	
	        }
	
	
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if(menu.equals("pedido")) {
        	  switch(accion) {
              case "buscadornp":{
              	   String buscador=request.getParameter("buscador");
              	   response.sendRedirect("ControladorPedido?menu=pedido&accion=nuevoPedido&buscador="+buscador);
              	   break;
              	   
              	
              }
              
              case "buscador":{
             	   String buscador=request.getParameter("buscador");
             	   System.out.println("Buscador: "+buscador);
             	   response.sendRedirect("ControladorPedido?menu=pedido&accion=pedido&buscador="+buscador);
             	   break;
             	   
             	
             }
              
              case "registrar":{
            	
              	ArrayList<Carrito> carritoPedido = (ArrayList<Carrito>) request.getSession().getAttribute("carritoPedido");
              	if(carritoPedido==null) {
              		response.sendRedirect("ControladorPedido?menu=pedido&accion=nuevoPedido");
              		System.out.println("Proveedor: "+request.getParameter("prove"));
              		System.out.println("id: "+request.getParameter("id"));
              		break;
              		
              	} else {
              		 //Si algun item se le asigno cantidad 0 vuelve al pedido.
              		  for(Carrito item: carritoPedido) {
              			     if(item.getCantidad()==0) {
              			    	    response.sendRedirect("ControladorPedido?menu=pedido&accion=nuevoPedido");
              		        		break;
              			    	 
              			     }
              			  
              		  }
              		  
              	        Pedido pedido = new Pedido();
              	        PedidoDAO pDAO=new PedidoDAO();
                          LocalDate fecha = LocalDate.now();
                          LineaPedidoDAO lpDAO=new LineaPedidoDAO();
                         
                          int idProveedor=Integer.parseInt(request.getParameter("prove"));
                          ProveedorDAO provDAO=new ProveedorDAO();
                          Empleado empleado = (Empleado) request.getSession().getAttribute("authEmpleado");
                          Proveedor proveedor=provDAO.getById(idProveedor);
                          pedido.setFecha(fecha);
                          
                          pedido.setEmpleado(empleado);
                          pedido.setProveedor(proveedor);
                          int nroPedido = pDAO.getMaxNroPedido();
                          System.out.println("Nro pedido: "+nroPedido);
                          if(nroPedido>=1) {
                        	  pedido.setNroPedido(nroPedido);	   
                        	  
                          }
                         
                          
                          if (pDAO.save(pedido)) {
                             
                              for (Carrito item : carritoPedido) {
                                  LineaPedido lp = new LineaPedido();
                                  lp.setCantProducto(item.getCantidad());
                                  lp.setNroPedido(nroPedido);
                                  lp.setIdProducto(item.getIdProducto());
                                 
                                  lpDAO.save(lp);
                                  
                              }
                              request.getSession().setAttribute("carritoPedido", null);
                              request.getSession().setAttribute("mensajeExito", "Pedido generado exitosamente");
                              response.sendRedirect("ControladorPedido?menu=pedido&accion=pedido"); 
              		        break;
              		  
              	           } else {
              	        	 request.getSession().setAttribute("mensajeError", "Error al generar el pedido");
              	        	   response.sendRedirect("ControladorPedido?menu=pedido&accion=pedido");
              	        	   break;
              	           }
              	           }
              	
              	
              }
              
              
              }
        	
        }
      
		
	}
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
