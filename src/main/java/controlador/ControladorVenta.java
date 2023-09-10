
package controlador;

import java.io.IOException;


import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Carrito;
import modelo.Cliente;
import modelo.LineaVenta;
import modelo.LineaVentaDAO;
import modelo.Venta;
import modelo.VentaDAO;
import modelo.ProductoDAO;

@WebServlet("/ControladorVenta")
public class ControladorVenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
    VentaDAO vDAO = new VentaDAO();
    LineaVentaDAO lvDAO = new LineaVentaDAO();
    ProductoDAO pDAO = new ProductoDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("venta")) {
            switch (accion) {
                
                case "venta":{
                       if(request.getSession().getAttribute("authEmpleado")!=null){
                            request.getRequestDispatcher("admin/ventas.jsp").forward(request, response);
                
                              break;
                       } else {
                         request.getRequestDispatcher("Admin").forward(request, response);
                         break;
                       }
                      
                }

                case "nuevaVenta": {

                    if (request.getSession().getAttribute("carrito-lista") == null) {
                        request.getSession().setAttribute("mensaje", "Debe ingresar al menos un producto en el carrito");
                        response.sendRedirect("ControladorCarrito?menu=carrito&accion=carrito");
                        break;
                    }

                    if (request.getSession().getAttribute("auth") == null) {
                        response.sendRedirect("ControladorLogin?menu=login&accion=login");
                        break;
                    }

                    Venta venta = new Venta();
                    LocalDateTime fecha = LocalDateTime.now();
                    venta.setFecha(fecha);
                    Cliente cliente = (Cliente) request.getSession().getAttribute("auth");
                    venta.setIdCliente(cliente.getId());
                    int nroVenta = vDAO.getMaxNroVenta();
                    venta.setNroVenta(nroVenta);

                    if (vDAO.save(venta)) {
                        ArrayList<Carrito> lista_carrito = (ArrayList<Carrito>) request.getSession().getAttribute("carrito-lista");
                        for (Carrito item : lista_carrito) {
                            LineaVenta lv = new LineaVenta();
                            lv.setCantProducto(item.getCantidad());
                            lv.setNroVenta(nroVenta);
                            lv.setIdProducto(item.getIdProducto());
                            lvDAO.save(lv);
                            pDAO.updateStock(item.getIdProducto(), item.getStock() - item.getCantidad());
                        }
                        request.getSession().setAttribute("carrito-lista", null);
                        request.getSession().setAttribute("mensajeExito", "Pedido generado exitosamente");
                        response.sendRedirect("index.jsp");
                        
                        break;

                    } else {
                          request.getSession().setAttribute("mensajeError", "Pedido generado exitosamente");
                          response.sendRedirect("index.jsp");
                          break;
                    
                    }

                }
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
