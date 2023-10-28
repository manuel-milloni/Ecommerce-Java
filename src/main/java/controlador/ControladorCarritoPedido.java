package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Carrito;
import modelo.Producto;
import modelo.ProductoDAO;


@WebServlet("/ControladorCarritoPedido")
public class ControladorCarritoPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 ProductoDAO pDAO = new ProductoDAO();
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
       






	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     String menu = request.getParameter("menu");
	        String accion = request.getParameter("accion");
	        HttpSession session = request.getSession();
	        if(menu.equals("pedido")) {
	        	switch(accion) {
	        	case "agregar":{
	                ArrayList<Carrito> carritoLista = new ArrayList<>();
                    Carrito carrito = new Carrito();
                    int id = Integer.parseInt(request.getParameter("id"));
                    Producto producto = pDAO.getById(id);
                    carrito.setIdProducto(id);
                    carrito.setCantidad(1);
                    carrito.setStock(producto.getStock());
                   
                    ArrayList<Carrito> carritoPedido = (ArrayList<Carrito>) session.getAttribute("carritoPedido");
                    //Primer producto al carrito
                    if (carritoPedido == null) {
                        carritoLista.add(carrito);
                        session.setAttribute("carritoPedido", carritoLista);
                        request.setAttribute("mensajeAgregado", "Producto agregado al carrito");
                        request.getRequestDispatcher("ControladorPedido?menu=pedido&accion=nuevoPedido").forward(request, response);
                        break;
                    } //Ya existe la lista carrito
                    else {
                        carritoLista = carritoPedido;
                        boolean existe = false;
                        for (Carrito c : carritoLista) {
                            if (c.getIdProducto() == id) {
                                existe = true;

                            }

                        }   //Si el producto no existe en la lista, lo agrego
                        if (!existe) {
                            carritoLista.add(carrito);
                            
                            request.getRequestDispatcher("ControladorPedido?menu=pedido&accion=nuevoPedido").forward(request, response);
                            break;
                        } //Producto ya se encuentra en el carrito
                        else {
                            request.setAttribute("mensajeExiste", "Producto ya se encuentra en el carrito");
                            request.getRequestDispatcher("ControladorPedido?menu=pedido&accion=nuevoPedido").forward(request, response);
                            break;
                        }
	        		
	        		
	        	}
	        	
	        	
	        	}
	        	case "eliminar": {
	        		ArrayList<Carrito> carritoPedido = (ArrayList<Carrito>) session.getAttribute("carritoPedido");
	        		 int id = Integer.parseInt(request.getParameter("id"));
	        		for(Carrito item: carritoPedido) {
	        			   if(item.getIdProducto()==id) {
	        				    carritoPedido.remove(item);
	        				    break;
	        			   }
	        			
	        		}
	        		 request.getRequestDispatcher("ControladorPedido?menu=pedido&accion=nuevoPedido").forward(request, response);
	        		 break;
	        		
	        	}
	        	
	        	case "incrementar":{
	        		ArrayList<Carrito> carritoPedido = (ArrayList<Carrito>) session.getAttribute("carritoPedido");
	        		 int id = Integer.parseInt(request.getParameter("id"));
	        		 for(Carrito item: carritoPedido) {
	        			 if(item.getIdProducto()==id) {
	        				  item.setCantidad(item.getCantidad()+1);
	        				 break;
	        			 }
	        			 
	        		 }
	        		 request.getRequestDispatcher("ControladorPedido?menu=pedido&accion=nuevoPedido").forward(request, response);
	        		 break;
	        		
	        		
	        	}
	        	
	           	case "decrementar":{
	        		ArrayList<Carrito> carritoPedido = (ArrayList<Carrito>) session.getAttribute("carritoPedido");
	        		 int id = Integer.parseInt(request.getParameter("id"));
	        		 for(Carrito item: carritoPedido) {
	        			 if(item.getIdProducto()==id) {
	        				 if(item.getCantidad()>0) {
	        					 item.setCantidad(item.getCantidad()-1);
		        				 break;	 
	        				 }
	        				 
	        			 }
	        			 
	        		 }
	        		 request.getRequestDispatcher("ControladorPedido?menu=pedido&accion=nuevoPedido").forward(request, response);
	        		 break;
	        		
	        		
	        	}
	        	
	        	
	        	}
	        	
	        
	        
	        
	
	
	}
	        }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	public String getServletInfo() {
		
		return null; 
	}

}
