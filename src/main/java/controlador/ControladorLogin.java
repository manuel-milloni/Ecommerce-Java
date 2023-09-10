
package controlador;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.ClienteDAO;
import modelo.Cliente;
import static validaciones.Encriptador.decrypt;


@WebServlet("/ControladorLogin")
public class ControladorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ClienteDAO cDAO = new ClienteDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        HttpSession session = request.getSession();
        if (menu.equals("login")) {
            switch (accion) {

                case "login": {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    break;

                }

                case "logout": {
                    session.setAttribute("auth", null);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;
                }

                case "registro": {
                    request.getRequestDispatcher("registro.jsp").forward(request, response);

                }
                default: {
                    request.getRequestDispatcher("ControladorInicio?menu=inicio&accion=inicio").forward(request, response);

                }

            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("login")) {
            switch (accion) {
                case "loginCliente": {
                    HttpSession session = request.getSession();
                    String password = request.getParameter("password");
                    Cliente c = cDAO.login(request.getParameter("email"));
                    try {
                        if (password.equals(decrypt(c.getPassword()))) {
                            session.setAttribute("auth", c);
                            response.sendRedirect("ControladorInicio?menu=inicio&accion=inicio");

                        } else {
                            session.setAttribute("mensaje", "Usuario o Contraseña incorrecto");
                            response.sendRedirect("ControladorLogin?menu=login&accion=login");
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
