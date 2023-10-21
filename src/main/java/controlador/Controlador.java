
package controlador;

import modelo.Producto;

import modelo.ProductoDAO;

import java.util.ArrayList;

import java.util.List;

import modelo.TipoProducto;
import modelo.TipoProductoDAO;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ProductoDAO pDAO = new ProductoDAO();
	private Producto producto = new Producto();
	private TipoProductoDAO tpDAO = new TipoProductoDAO();
	private List<TipoProducto> tiposProducto = new ArrayList<>();
	private TipoProducto tipoProducto = new TipoProducto();
	private Producto p = new Producto();
	private List<Producto> productos = new ArrayList<>();
	private static final String UPLOAD_DIR = "images";

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String menu = request.getParameter("menu");
		String accion = request.getParameter("accion");
		if (menu.equals("Producto")) {
			switch (accion) {

			case "Producto": {
				if (request.getSession().getAttribute("authEmpleado") != null) {
					tiposProducto = tpDAO.getAll();
					productos = pDAO.getAll();
					request.setAttribute("tiposProducto", tiposProducto);
					request.setAttribute("productos", productos);
					request.getRequestDispatcher("admin/producto.jsp").forward(request, response);
					break;

				} else {
					request.getRequestDispatcher("Admin").forward(request, response);
					break;

				}

			}
			
			  case "Eliminar": {
                  pDAO.delete(Integer.parseInt(request.getParameter("id")));
                  request.getRequestDispatcher("Controlador?menu=Producto&accion=Producto").forward(request, response);
                  break;

              }

			case "Editar": {
				int id = Integer.parseInt(request.getParameter("id"));
				Producto producto = null;
				producto = pDAO.getById(id);
				if (producto != null) {
					tiposProducto = tpDAO.getAll();
				
					request.setAttribute("tiposProducto", tiposProducto);
					request.setAttribute("producto", producto);
				
					request.getRequestDispatcher("admin/productoModificar.jsp").forward(request, response);
					break;

				} else {
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    
				}
				break;

			}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String menu = request.getParameter("menu");
		String accion = request.getParameter("accion");
		if (menu.equals("Producto")) {
			switch (accion) {

			case "NuevoProducto": {
				p.setDescripcion(request.getParameter("descripcion"));
				p.setPrecio(Double.parseDouble(request.getParameter("precio")));
				p.setIdTipo(Integer.parseInt(request.getParameter("tipoProducto")));
				p.setStock(Integer.parseInt(request.getParameter("stock")));

				// Imagen ----------------------------------------------------------------------
				// Obtener ruta absoluta de la App
				String applicationPath = request.getServletContext().getRealPath("");
				System.out.println("Aplication path: " + applicationPath);

				// File path
				String uploadFilePath = applicationPath + UPLOAD_DIR + File.separator;
				System.out.println("File path: " + uploadFilePath);

				// Folder images
				File fileSaveDir = new File(uploadFilePath);

				if (!fileSaveDir.exists()) {
					// Si el direcorio no existe, lo creo
					fileSaveDir.mkdirs();

				}

				String fileName = null;

				for (Part part : request.getParts()) {
					fileName = getFileName(part);
					System.out.println("Filename: " + fileName);
					System.out.println("Write: " + uploadFilePath + fileName);
					if (fileName != null && !fileName.equalsIgnoreCase("")) {
						part.write(uploadFilePath + fileName);

					}

				}

				p.setImagen(UPLOAD_DIR + File.separator + fileName);
				// --
				// --------------------------------------------------------------------------------------------------

				pDAO.save(p);

				response.sendRedirect("Controlador?menu=Producto&accion=Producto");
				break;

			}

			case "modificar": {
				p.setIdProducto(Integer.parseInt(request.getParameter("id")));
				p.setDescripcion(request.getParameter("descripcion"));
				p.setPrecio(Double.parseDouble(request.getParameter("precio")));
				p.setIdTipo(Integer.parseInt(request.getParameter("tipoProducto")));
				p.setStock(Integer.parseInt(request.getParameter("stock")));
				pDAO.update(p);
				response.sendRedirect("Controlador?menu=Producto&accion=Producto");
                break;
			}
			}
		}
	}

	private String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		System.out.println("content-disposition header= " + contentDisp);
		String[] tokens = contentDisp.split(";");
		for (String token : tokens) {
			if (token.trim().startsWith("filename")) {
				return token.substring(token.indexOf("=") + 2, token.length() - 1);
			}
		}
		return "";
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
