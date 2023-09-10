
package controlador;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import static validaciones.Encriptador.decrypt;

@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L; 
    EmpleadoDAO eDAO = new EmpleadoDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if(accion!=null){
                    switch (accion) {
            case "login" -> {

                request.getRequestDispatcher("/admin/adminLogin.jsp").forward(request, response);
                break;

            }

            case "logout" -> {
                request.getSession().setAttribute("authEmpleado", null);
                request.getRequestDispatcher("Admin?accion=login").forward(request, response);
                break;

            }
            default -> {
                request.getRequestDispatcher("/admin/adminLogin.jsp").forward(request, response);
                break;
            }

        }
            
            
            
        } else {
          request.getRequestDispatcher("/admin/adminLogin.jsp").forward(request, response);
        } 
            
   

    

}

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Empleado emple = null;
        emple = eDAO.getByEmail(email);
        if (emple != null) {
            try {
                if (password.equals(decrypt(emple.getPassword()))) {
                 
                      

                     
                            request.getSession().setAttribute("authEmpleado", emple);
                            response.sendRedirect("Controlador?menu=Producto&accion=Producto");
                        

                    

                } else {
                     request.getSession().setAttribute("mensaje", "Usuario o contraseña incorrectos");
                      response.sendRedirect("Admin?accion=login");

}

            } catch (Exception ex) {
                Logger.getLogger(Admin.class  

.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            request.getSession().setAttribute("mensaje", "Usuario o contraseña incorrectos");
            response.sendRedirect("Admin?accion=login");

        }

    }

    @Override
public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
