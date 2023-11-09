
package controlador;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Proveedor;
import modelo.ProveedorDAO;

@WebServlet("/ControladorProveedor")
public class ControladorProveedor extends HttpServlet {
	private static final long serialVersionUID = 1L;
             ProveedorDAO pDAO=new ProveedorDAO();
             List<Proveedor> proveedores= new ArrayList<>();
             Proveedor pro=new Proveedor();
             
      


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String menu=request.getParameter("menu");
           String accion=request.getParameter("accion");
            if(menu.equals("Proveedor")){
            switch(accion){
               case "Proveedor":{
                     if(request.getSession().getAttribute("authEmpleado")!=null){
                          proveedores=pDAO.getAll();
                    
                     request.setAttribute("proveedores", proveedores);
                     request.getRequestDispatcher("admin/proveedor.jsp").forward(request, response);
                     break; 
                    
                    } else {
                        request.getRequestDispatcher("Admin").forward(request, response);
                        break;
                      
                      }
                    
                }
                
               
                
                case "Editar":{
               pro=pDAO.getById(Integer.parseInt(request.getParameter("id")));
                   
               request.setAttribute("proveedor", pro);
                    System.out.println(pro.getRazonSocial());
               request.getRequestDispatcher("ControladorProveedor?menu=Proveedor&accion=ProveedorModificar").forward(request, response);
               break;
               
         
                }
                
                 case "ProveedorModificar":{
             proveedores=pDAO.getAll();
             request.setAttribute("proveedores", proveedores);
             request.getRequestDispatcher("admin/proveedorModificar.jsp").forward(request, response);
             break;
                }
                
              
                 case "Eliminar":{
                	 
             if(pDAO.delete(Integer.parseInt(request.getParameter("id")))) {
            	 request.getSession().setAttribute("mensajeExito", "Proveedor eliminado exitosamente");
            	 request.getRequestDispatcher("ControladorProveedor?menu=Proveedor&accion=Proveedor").forward(request, response);
                 break;	 
            	 
             } else {
            	 request.getSession().setAttribute("mensajeError", "Error al eliminar Proveedor");
            	 request.getRequestDispatcher("ControladorProveedor?menu=Proveedor&accion=Proveedor").forward(request, response);
                 break;	 
            	 
            	 
             }
             
            
             
                    }
                 default: {
                      response.sendError(HttpServletResponse.SC_NOT_FOUND, "La página solicitada no se encontró.");
                 
                 }
            }
            }
      
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu=request.getParameter("menu");
           String accion=request.getParameter("accion");
            if(menu.equals("Proveedor")){
            switch(accion){
                case "NuevoProveedor":{
                    Proveedor prove=null;
                    prove=pDAO.getByCuit(request.getParameter("cuit"));
                    
                   
                    
                   if(prove.getCuit()==null){
                     Proveedor prov=new Proveedor();
                   prov.setCuit(request.getParameter("cuit"));
                   prov.setRazonSocial(request.getParameter("razonSocial"));
                   prov.setTelefono(request.getParameter("telefono"));
                   prov.setEmail(request.getParameter("email"));
                   pDAO.save(prov);
                   
                   } 
                    request.getSession().setAttribute("mensaje", "Proveedor agregado exitosamente");
                   response.sendRedirect("ControladorProveedor?menu=Proveedor&accion=Proveedor");
                   break;
                
                
                }
                
                  case "Modificar":{
             pro.setIdProveedor(Integer.parseInt(request.getParameter("id")));
             pro.setCuit(request.getParameter("cuit"));
             pro.setRazonSocial(request.getParameter("razonSocial"));
             pro.setTelefono(request.getParameter("telefono"));
             pro.setEmail(request.getParameter("email"));
             pDAO.update(pro);
             
             response.sendRedirect("ControladorProveedor?menu=Proveedor&accion=Proveedor");
             break;
                }
            
            }
            }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
