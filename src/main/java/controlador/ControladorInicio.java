
package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ControladorInicio")
public class ControladorInicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("inicio")) {
            switch (accion) {
                case "inicio": {
                   
                   
                    request.setAttribute("buscador", request.getParameter("buscador"));
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
        if (menu.equals("inicio")) {
            switch (accion) {

                case "buscador": {
                    String descripcion = request.getParameter("buscador");
                  
                  
                    response.sendRedirect("ControladorInicio?menu=inicio&accion=inicio&buscador="+descripcion);
                  
                    

                }

            }

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
