
package controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CategoriaDAO;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Categoria;

import static validaciones.Encriptador.encrypt;

@WebServlet("/ControladorCliente")
public class ControladorCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ClienteDAO cDAO = new ClienteDAO();
    CategoriaDAO catDAO = new CategoriaDAO();
    Cliente c = new Cliente();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("Cliente")) {
            switch (accion) {

                case "Cliente": {
                    if (request.getSession().getAttribute("authEmpleado") != null) {
                        List<Categoria> categorias = catDAO.getAll();
                        request.setAttribute("categorias", categorias);
                        request.getRequestDispatcher("admin/cliente.jsp").forward(request, response);
                        break;

                    } else {
                        request.getRequestDispatcher("Admin").forward(request, response);
                        break;
                    }

                }

                case "Editar": {
                    c = cDAO.getById(Integer.parseInt(request.getParameter("id")));

                    List<Categoria> categorias = catDAO.getAll();
                    request.setAttribute("cli", c);
                    request.setAttribute("categorias", categorias);
                    request.getRequestDispatcher("admin/clienteModificar.jsp").forward(request, response);
                    break;

                }

                case "Eliminar": {
                    cDAO.delete(Integer.parseInt(request.getParameter("id")));
                    request.getRequestDispatcher("ControladorCliente?menu=Cliente&accion=Cliente").forward(request, response);
                    break;

                }

                case "login": {
                    Cliente c = cDAO.login(request.getParameter("email"));

                    if (c != null) {
                        request.getSession().setAttribute("auth", c);

                    }

                }

                default: {
                    request.getRequestDispatcher("ControladorCliente?menu=Cliente&accion=Cliente").forward(request, response);
                    break;

                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("Cliente")) {
            switch (accion) {
                case "NuevoCliente": {
                    Cliente cli = new Cliente();
                    String password = request.getParameter("password");
                    String password_2 = request.getParameter("password_2");
                    if (password.equals(password_2)) {

                        cli.setNombre(request.getParameter("nombre"));
                        cli.setApellido(request.getParameter("apellido"));
                        cli.setTelefono(request.getParameter("telefono"));
                        cli.setEmail(request.getParameter("email"));
                        cli.setDireccion(request.getParameter("direccion"));
                        cli.setCuit(request.getParameter("cuit"));
                        cli.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
                        try {
                            cli.setPassword(encrypt(request.getParameter("password")));
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }

                        if (cDAO.save(cli) == 1) {
                            if (request.getSession().getAttribute("authAdmin") != null) {

                                request.getSession().setAttribute("mensajeExito", "Cliente agregado exitosamente");
                                response.sendRedirect("ControladorCliente?menu=Cliente&accion=Cliente");
                                break;
                            } else {
                                request.getSession().setAttribute("auth", cli);
                                response.sendRedirect("ControladorInicio?menu=inicio&accion=inicio");
                                break;
                            }
                        } else {

                            if (request.getSession().getAttribute("authAdmin") != null) {
                                request.getSession().setAttribute("mensajeError", "Error al ingresar cliente");
                                response.sendRedirect("ControladorCliente?menu=Cliente&accion=Cliente");
                                break;
                            } else {
                                request.getSession().setAttribute("mensajeError", "Error no se genero el registro");
                                response.sendRedirect("ControladorLogin?menu=login&accion=registro");
                                break;
                            }
                        }

                    } else {

                        if (request.getSession().getAttribute("authAdmin") != null) {
                            request.getSession().setAttribute("mensajeError", "Las contrasenas deben coincidir");
                            response.sendRedirect("ControladorCliente?menu=Cliente&accion=Cliente");
                            break;
                        } else {
                            request.getSession().setAttribute("mensajeError", "Las contrasenas deben coincidir");
                            response.sendRedirect("ControladorLogin?menu=login&accion=registro");
                            break;
                        }

                    }
                }

                case "Modificar": {
                    c.setId(Integer.parseInt(request.getParameter("id")));
                    c.setNombre(request.getParameter("nombre"));
                    c.setApellido(request.getParameter("apellido"));
                    c.setTelefono(request.getParameter("telefono"));
                    c.setEmail(request.getParameter("email"));
                    c.setDireccion(request.getParameter("direccion"));
                    c.setCuit(request.getParameter("cuit"));
                    c.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
                   
                    cDAO.update(c);
                    response.sendRedirect("ControladorCliente?menu=Cliente&accion=Cliente");
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
