
<%@page import="modelo.Carrito"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Cliente"%>
<%@page import="modelo.TipoProductoDAO"%>
<%@page import="modelo.TipoProducto"%>
<%@page import="modelo.Producto"%>
<%@page import="java.util.List"%>
<%@page import="modelo.ProductoDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%

    ProductoDAO pDAO = new ProductoDAO();
    String buscador = (String) request.getAttribute("buscador");
    List<Producto> productos;

    if (buscador == null || buscador.equals("")) {
        productos = pDAO.getAll();
    } else {
        productos = pDAO.getByDescripcion(buscador);
    }

    if (productos.isEmpty()) {
        request.setAttribute("mensajeExiste", "No se encontraron productos");

    }

    TipoProducto tp = new TipoProducto();
    TipoProductoDAO tpDAO = new TipoProductoDAO();

    ArrayList<Carrito> carrito_lista = (ArrayList<Carrito>) session.getAttribute("carrito-lista");

    if (carrito_lista != null) {

        request.setAttribute("carrito_lista", carrito_lista);

    }

%>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Ecommerce Java</title>
        <%@include file="estructura/head.jsp" %>
    </head>
    <body>



        <% if (request.getSession().getAttribute("authEmpleado") != null) {
        %><%@include file="estructura/navAdmin.jsp" %>

        <%       } else {%>
        <%@include file="estructura/nav.jsp" %>    
        <%             }    %>




        <h1>Tienda</h1>
        <% if (request.getAttribute("mensajeAgregado") != null) {%>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <strong><%= request.getAttribute("mensajeAgregado")%></strong>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <% } %>


        <% if (request.getAttribute("mensajeExiste") != null) {%>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <strong><%= request.getAttribute("mensajeExiste")%></strong>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <% } %>
        <!-- --------------------------------BUSCADOR-------------------------- -->
        <div class="container text-center">
            <div class="row mt-3">
                <div class="col-12">
                    <form action="ControladorInicio?menu=inicio&accion=buscador" method="post">
                        <div class="input-group" style="max-width: 400px; margin: 0 auto;">
                            <input type="text" name="buscador" id="buscador" class="form-control" placeholder="Buscar...">
                            <button class="btn btn-primary" type="submit">Buscar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <div class="container">
            <div class="card-header my-3">All Products</div>
            <div class="row">
                <%
                    if (!productos.isEmpty()) {
                        for (Producto p : productos) {
                            tp = tpDAO.getById(p.getIdTipo());
                %>
                <div class="col-md-3 my-3">
                    <div class="card w-100" style="width: 18rem;">
                        <img class="card-img-top" src="<%= p.getImagen() %>" alt="Imagen producto" width="200" height="200">
                        <div class="card-body">
                            <h5 class="card-title"> <%= p.getDescripcion()%></h5>
                            <h6 class="price">Precio: $ <%= p.getPrecio()%> </h6>

                            <h6 class="categoria">Categoria: <%= tp.getDescripcion()%>  </h6>

                            <% if (p.getStock() > 0) {%>
                            <div class="mt-3 d-flex justify-content-between">
                                <a href="ControladorCarrito?menu=carrito&accion=agregar&id=<%= p.getIdProducto()%>" class="btn btn-primary">Agregar al carrito</a>

                            </div>    
                            <%
                            } else {%>
                            <div class="mt-3 d-flex justify-content-between">
                                <a href="#" class="btn btn-danger" tabindex="-1" role="button" aria-disabled="true">Agotado</a>

                            </div> 
                            <%
                                          }  %>



                        </div>
                    </div>

                </div>
                <%}

                    }

                %>



            </div>

        </div>



        <%@include file="estructura/footer.jsp" %>
    </body>
</html>
