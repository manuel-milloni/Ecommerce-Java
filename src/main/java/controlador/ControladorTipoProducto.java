
package controlador;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.TipoProducto;
import modelo.TipoProductoDAO;

@WebServlet("/ControladorTipoProducto")
public class ControladorTipoProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TipoProductoDAO tpDAO = new TipoProductoDAO();
	List<TipoProducto> tiposProducto = new ArrayList<>();
	TipoProducto tp = new TipoProducto();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String menu = request.getParameter("menu");
		String accion = request.getParameter("accion");
		if (menu.equals("TipoProducto")) {
			switch (accion) {

			case "TipoProducto": {
				if (request.getSession().getAttribute("authEmpleado") != null) {
					tiposProducto = tpDAO.getAll();
					request.setAttribute("tiposProducto", tiposProducto);
					request.getRequestDispatcher("admin/tipoProducto.jsp").forward(request, response);
					break;

				} else {
					request.getRequestDispatcher("Admin").forward(request, response);
					break;

				}

			}

			case "Editar": {
				tp = tpDAO.getById(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("TipoProducto", tp);
				request.getRequestDispatcher("ControladorTipoProducto?menu=TipoProducto&accion=tipoproductomodificar")
						.forward(request, response);
				break;

			}

			case "tipoproductomodificar": {
				tiposProducto = tpDAO.getAll();
				request.setAttribute("tiposProducto", tiposProducto);

				request.getRequestDispatcher("admin/tipoProductoModificar.jsp").forward(request, response);
				break;
			}

			case "Eliminar": {

				if (tpDAO.delete(Integer.parseInt(request.getParameter("id")))) {
					request.getSession().setAttribute("mensajeExito", "Tipo Producto eliminado exitosamente");
					request.getRequestDispatcher("ControladorTipoProducto?menu=TipoProducto&accion=TipoProducto")
							.forward(request, response);
					break;
				} else {

					request.getSession().setAttribute("mensajeError", "Error al eliminar Tipo Producto");
					request.getRequestDispatcher("ControladorTipoProducto?menu=TipoProducto&accion=TipoProducto")
							.forward(request, response);
					break;

				}

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
		if (menu.equals("TipoProducto")) {
			switch (accion) {
			case "NuevoTipoProducto": {
				tpDAO.save(request.getParameter("descripcion"));
				request.getSession().setAttribute("mensaje", "El tipo de producto se ha agregado exitosamente");
				response.sendRedirect("ControladorTipoProducto?menu=TipoProducto&accion=TipoProducto");
				break;

			}
			case "modificar": {
				tp.setIdTipo(Integer.parseInt(request.getParameter("id")));
				tp.setDescripcion(request.getParameter("descripcion"));
				tpDAO.update(tp);
				response.sendRedirect("ControladorTipoProducto?menu=TipoProducto&accion=TipoProducto");
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
