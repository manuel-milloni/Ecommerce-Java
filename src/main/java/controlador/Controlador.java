
package controlador;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.Producto;

import modelo.ProductoDAO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import modelo.TipoProducto;
import modelo.TipoProductoDAO;




@MultipartConfig
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductoDAO pDAO = new ProductoDAO();
    private Producto producto = new Producto();
    private TipoProductoDAO tpDAO = new TipoProductoDAO();
    private List<TipoProducto> tiposProducto = new ArrayList<>();
    private TipoProducto tp = new TipoProducto();
    private Producto p = new Producto();
    private List<Producto> productos = new ArrayList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("Producto")) {
            switch (accion) {
                case "Producto": {
                    if(request.getSession().getAttribute("authEmpleado")!=null){
                        tiposProducto = tpDAO.getAll();
                    productos = pDAO.getAll();
                    request.setAttribute("tiposProducto", tiposProducto);
                    request.setAttribute("productos", productos);
                    request.getRequestDispatcher("admin/producto.jsp").forward(request, response);
                    break;
                    
                    } else {
                      request.getRequestDispatcher("Admin").forward(request, response);
                    
                    }
                    

                }
                
                case "Editar":{
                  int id=Integer.parseInt(request.getParameter("id"));
                  Producto producto=null;
                  producto=pDAO.getById(id);
                  if(producto!=null){
                      tiposProducto = tpDAO.getAll();
                       request.setAttribute("tiposProducto", tiposProducto);
                      request.setAttribute("producto", producto);
                      request.getRequestDispatcher("admin/productoModificar.jsp").forward(request, response);
                  
                  } else  {
                     response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                  
                  }
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
        if (menu.equals("Producto")) {
            switch (accion) {

                case "NuevoProducto": {
                    p.setDescripcion(request.getParameter("descripcion"));
                    p.setPrecio(Double.parseDouble(request.getParameter("precio")));
                    p.setIdTipo(Integer.parseInt(request.getParameter("tipoProducto")));
                    p.setStock(Integer.parseInt(request.getParameter("stock")));

                    Part fotoPart = request.getPart("imagen");
                    if (fotoPart != null) {
                        String nombreFoto = fotoPart.getSubmittedFileName();

                        // Directorio donde se almacenará la foto
                        String directorioFotos = "C:/Users/Manu/Documents/NetBeansProjects/CargarImagen/img";

                        // Crear el archivo destino
                        File archivoDestino = new File(directorioFotos, nombreFoto);

                        // Copiar la foto al directorio destino
                        fotoPart.write(archivoDestino.getAbsolutePath());

                        // Establecer la ruta completa de la foto en el atributo producto.setFoto()
                        String rutaFoto = archivoDestino.getAbsolutePath();
                        p.setImagen(rutaFoto);
                    }

                    pDAO.save(p);
                    response.sendRedirect("Controlador?menu=Producto&accion=Producto");
                    break;

                }
                
                case "modificar":{
                    p.setIdProducto(Integer.parseInt(request.getParameter("id")));
                    p.setDescripcion(request.getParameter("descripcion"));
                    p.setPrecio(Double.parseDouble(request.getParameter("precio")));
                    p.setIdTipo(Integer.parseInt(request.getParameter("tipoProducto")));
                    p.setStock(Integer.parseInt(request.getParameter("stock")));
                    pDAO.update(p);
                    response.sendRedirect("Controlador?menu=Producto&accion=Producto");
                
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
