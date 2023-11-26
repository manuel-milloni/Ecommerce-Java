
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
import modelo.ProductoDAO;
import modelo.Producto;



@WebServlet("/ControladorCarrito")
public class ControladorCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ProductoDAO pDAO = new ProductoDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        HttpSession session = request.getSession();
        if (menu.equals("carrito")) {
            switch (accion) {

                case "agregar": {
                    ArrayList<Carrito> carritoLista = new ArrayList<>();
                    Carrito carrito = new Carrito();
                    int id = Integer.parseInt(request.getParameter("id"));
                    Producto producto = pDAO.getById(id);
                    carrito.setIdProducto(id);
                    carrito.setCantidad(1);
                    carrito.setStock(producto.getStock());
                    carrito.setPrecio(producto.getPrecio()); 
                    ArrayList<Carrito> carrito_lista = (ArrayList<Carrito>) session.getAttribute("carrito-lista");

                    //Primer producto al carrito
                    if (carrito_lista == null) {
                        carritoLista.add(carrito);
                        session.setAttribute("carrito-lista", carritoLista);
                        request.setAttribute("mensajeAgregado", "Producto agregado al carrito");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } //Ya existe la lista carrito
                    else {
                        carritoLista = carrito_lista;
                        boolean existe = false;
                        for (Carrito c : carritoLista) {
                            if (c.getIdProducto() == id) {
                                existe = true;

                            }

                        }
                        //Si el producto no existe en la lista, lo agrego
                        if (!existe) {
                            carritoLista.add(carrito);
                            request.setAttribute("mensajeAgregado", "Producto agregado al carrito");
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                        } //Producto ya se encuentra en el carrito
                        else {
                            request.setAttribute("mensajeExiste", "Producto ya se encuentra en el carrito");
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                        }

                    }

                    break;
                }

                case "carrito": {
                    request.getRequestDispatcher("carrito.jsp").forward(request, response);
                    break;
                }

                case "incrementar": {
                    int id = Integer.parseInt(request.getParameter("id"));

                    ArrayList<Carrito> carrito_lista = (ArrayList<Carrito>) session.getAttribute("carrito-lista");
                    if (carrito_lista != null) {
                        for (Carrito c : carrito_lista) {

                            if (id == c.getIdProducto()) {
                            	 Producto producto = pDAO.getById(id);
                            	 c.setStock(producto.getStock());
                                if (c.getStock() < c.getCantidad() + 1) {
                                    request.setAttribute("mensaje", "La cant pedida excede el stock disponible. Stock: " + c.getStock());
                                    request.getRequestDispatcher("ControladorCarrito?menu=carrito&accion=carrito").forward(request, response);
                                    break;
                                } else {
                                    c.setCantidad(c.getCantidad() + 1);
                                    break;
                                }

                            }

                        }
                    }
                    
                    request.getRequestDispatcher("ControladorCarrito?menu=carrito&accion=carrito").forward(request, response);
                    break;

                    

                  

                }

                case "decrementar": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    ArrayList<Carrito> carrito_lista = (ArrayList<Carrito>) request.getSession().getAttribute("carrito-lista");
                    if (carrito_lista != null) {
                        for (Carrito c : carrito_lista) {
                            if (id == c.getIdProducto()) {
                                if (c.getCantidad() > 1) {
                                    c.setCantidad(c.getCantidad() - 1);
                                }

                            }

                        }

                    }

                    request.getRequestDispatcher("ControladorCarrito?menu=carrito&accion=carrito").forward(request, response);
                    break;

                }

                case "eliminar": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    ArrayList<Carrito> carrito_lista = (ArrayList<Carrito>) request.getSession().getAttribute("carrito-lista");
                    if (carrito_lista != null) {
                        for (Carrito c : carrito_lista) {
                            if (id == c.getIdProducto()) {
                                carrito_lista.remove(carrito_lista.indexOf(c));
                                break;
                            }
                        }

                    }
                    request.getRequestDispatcher("ControladorCarrito?menu=carrito&accion=carrito").forward(request, response);
                    break;
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
