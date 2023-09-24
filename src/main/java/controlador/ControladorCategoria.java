
package controlador;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Categoria;
import modelo.CategoriaDAO;


@WebServlet("/ControladorCategoria")
public class ControladorCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CategoriaDAO cDAO = new CategoriaDAO();
    Categoria cat = new Categoria();
    List<Categoria> categorias = new ArrayList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("Categoria")) {
            switch (accion) {

                case "Categoria": {
                    if (request.getSession().getAttribute("authEmpleado") != null) {
                        categorias = cDAO.getAll();
                        request.setAttribute("categorias", categorias);
                        request.getRequestDispatcher("admin/categoria.jsp").forward(request, response);
                        break;

                    } else {
                        request.getRequestDispatcher("Admin").forward(request, response);
                        break;

                    }

                }

                case "Editar": {
                    cat = cDAO.getById(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("categoria", cat);
                    request.getRequestDispatcher("ControladorCategoria?menu=Categoria&accion=CategoriaModificar").forward(request, response);
                    break;

                }
                case "CategoriaModificar": {
                    categorias = cDAO.getAll();
                    request.setAttribute("categorias", categorias);
                    request.getRequestDispatcher("admin/categoriaModificar.jsp").forward(request, response);
                    break;
                }

                case "Eliminar": {
                    cDAO.delete(Integer.parseInt(request.getParameter("id")));
                    request.getRequestDispatcher("ControladorCategoria?menu=Categoria&accion=Categoria").forward(request, response);
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
        if (menu.equals("Categoria")) {
            switch (accion) {
                case "NuevoCategoria": {
                    cDAO.save(request.getParameter("descripcion"));
                    request.getSession().setAttribute("mensaje", "Categoria agregada exitosamente");
                    response.sendRedirect("ControladorCategoria?menu=Categoria&accion=Categoria");
                    break;

                }

                case "Modificar": {
                    cat.setId(Integer.parseInt(request.getParameter("id")));
                    cat.setDescripcion(request.getParameter("descripcion"));
                    cDAO.update(cat);

                  
                    response.sendRedirect("ControladorCategoria?menu=Categoria&accion=Categoria");
                }

            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
