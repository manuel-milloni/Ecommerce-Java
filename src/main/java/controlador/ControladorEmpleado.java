
package controlador;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import modelo.Rol;
import modelo.RolDAO;
import static validaciones.Encriptador.encrypt;

@WebServlet("/ControladorEmpleado")
public class ControladorEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;
    List<Empleado> empleados = new ArrayList<>();
    EmpleadoDAO eDAO = new EmpleadoDAO();
    Empleado emp = new Empleado();
    RolDAO rDAO = new RolDAO();
    List<Rol> roles = new ArrayList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("Empleado")) {
            switch (accion) {
                
                case "Empleado": {
                      if(request.getSession().getAttribute("authEmpleado")!=null){
                         empleados = eDAO.getAll();
                    roles = rDAO.getAll();
                    request.setAttribute("empleados", empleados);
                    request.setAttribute("roles", roles);
                    request.getRequestDispatcher("admin/empleado.jsp").forward(request, response);
                    break;
                    
                    } else {
                        request.getRequestDispatcher("Admin").forward(request, response);
                        break;
                      
                      }
                    

                }

                case "Editar": {

                    emp = eDAO.getById(Integer.parseInt(request.getParameter("id")));
                    roles = rDAO.getAll();
                    request.setAttribute("empleado", emp);
                    request.setAttribute("roles", roles);
                    request.getRequestDispatcher("ControladorEmpleado?menu=Empleado&accion=EmpleadoModificar").forward(request, response);
                    break;

                }

                case "EmpleadoModificar": {
                    empleados = eDAO.getAll();
                    request.setAttribute("empleados", empleados);
                    request.getRequestDispatcher("admin/empleadoModificar.jsp").forward(request, response);
                    break;

                }

                case "Eliminar": {
                    eDAO.delete(Integer.parseInt(request.getParameter("id")));
                    request.getRequestDispatcher("ControladorEmpleado?menu=Empleado&accion=Empleado").forward(request, response);
                    break;

                }

                default: {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
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
        if (menu.equals("Empleado")) {
            switch (accion) {
                case "NuevoEmpleado": {

                    emp.setNombre(request.getParameter("nombre"));
                    emp.setApellido(request.getParameter("apellido"));
                    emp.setTelefono(request.getParameter("telefono"));
                    emp.setEmail(request.getParameter("email"));
                    emp.setDireccion(request.getParameter("direccion"));
                    emp.setDni(request.getParameter("dni"));
                    emp.setIdRol(Integer.parseInt(request.getParameter("rol")));
                
                try {
                    String passEncriptado=encrypt(request.getParameter("password"));
                      emp.setPassword(passEncriptado);
                } catch (Exception ex) {
                    Logger.getLogger(ControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    request.getSession().setAttribute("mensajeError", "Error: no se pudo ingresar el registro");
                }
                  
                    eDAO.save(emp);
                    request.getSession().setAttribute("mensaje", "Empleado agregado exitosamente");
                    response.sendRedirect("ControladorEmpleado?menu=Empleado&accion=Empleado");
                    break;

                }

                case "Modificar": {
                    emp.setId(Integer.parseInt(request.getParameter("id")));
                    emp.setNombre(request.getParameter("nombre"));
                    emp.setApellido(request.getParameter("apellido"));
                    emp.setTelefono(request.getParameter("telefono"));
                    emp.setEmail(request.getParameter("email"));
                    emp.setDireccion(request.getParameter("direccion"));
                    emp.setDni(request.getParameter("dni"));
                    emp.setIdRol(Integer.parseInt(request.getParameter("idRol")));
                    emp.setPassword(request.getParameter("password"));

                    eDAO.update(emp);

                    response.sendRedirect("ControladorEmpleado?menu=Empleado&accion=Empleado");
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
