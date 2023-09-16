<%@page import="modelo.TipoProducto"%>
<%@page import="modelo.Producto"%>
<%@page import="java.util.List"%>
<%@page import="modelo.TipoProductoDAO"%>
<%@page import="modelo.ProductoDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Producto</title>
  
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
    <%@include file="../estructura/navAdmin.jsp" %>
    <div class="d-flex">
        <br>
        <div class="card col-sm-4">
            
               <div class="card-body">
        <h2>Producto</h2>
        
         <% if (request.getSession().getAttribute("mensaje") != null) { %>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <strong><%= request.getSession().getAttribute("mensaje") %></strong>
         <% request.getSession().setAttribute("mensaje", null); %>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
        <% } %>
        
        <form action="Controlador?menu=Producto&accion=NuevoProducto" method="post" enctype="multipart/form-data">
            
            <div class="form-group row">
                <label for="descripcion" class="col-sm-3 col-form-label">Descripción:</label>
            <div class="col-sm-9">
            <input type="text" id="descripcion" name="descripcion" class="form-control" value="" maxlength="30" required>
            </div>
            </div>
            
         <div class="form-group row">
            <label for="precio" class="col-sm-3 col-form-label">Precio:</label>
        <div class="col-sm-9">
        <input type="number" id="precio" name="precio" class="form-control" value="" required step="any">
        </div>
         </div>

            
            <div class="form-group row">
    <label for="tipoProducto" class="col-sm-3 col-form-label">Tipo:</label>
    
    <div class="col-sm-9">
        <select id="tipoProducto" name="tipoProducto" class="form-control" required>
            <c:forEach var="tp" items="${tiposProducto}">
                <option value="${tp.getIdTipo()}" ${tp.getDescripcion() eq opcion ? 'selected' : ''}>${tp.getDescripcion()}</option>
            </c:forEach>
        </select>
    </div>
            </div>
    
  <div class="form-group row">
    <label for="stock" class="col-sm-3 col-form-label">Stock:</label>
    <div class="col-sm-9">
        <input type="number" id="stock" name="stock" class="form-control" value="" required step="1">
    </div>
</div>
    <div class="form-group row">
     <label for="imagen" class="col-sm-3 col-form-label">Imagen:</label>
      <div class="col-sm-9">
        <input type="file" name="filename" >
      </div>
    </div>
    



            <br>
            <div class="text-center">
                <input type="submit" value="Agregar">
           
                
                </div>
            
        </form>
    </div>
        
            
        </div>
        
        <div class="col-sm-8">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>DESCRIPCION</th>
                        <th>PRECIO</th>
                        <th>TIPO</th>
                        <th>STOCK</th>
                        <th>IMAGEN</th>
                        <th>ACCIONES</th>
                        
                        
                    </tr>
                    
                    
                </thead>
                
                <%
                   ProductoDAO pDAO=new ProductoDAO();
                   TipoProductoDAO tpDAO=new TipoProductoDAO();
                   List<Producto> productos=pDAO.getAll();
                   for(Producto p: productos){
                              
                    
                           TipoProducto tp=tpDAO.getById(p.getIdTipo());
                         %>
                        <tbody>
                        <tr>
                            
                            <td><%=p.getIdProducto()%></td>
                            <td><%=p.getDescripcion()%></td>
                            <td><%=p.getPrecio()%></td>
                            <td><%=tp.getDescripcion()%></td>
                            <td><%=p.getStock()%></td>
                            <td>
                                 
                             <img src="<%= p.getImagen() %>" alt="Imagen producto" class="card-img-top" width="200" height="150">



                            </td>
                            <td>
                            <a class="btn btn-warning" href="Controlador?menu=Producto&accion=Editar&id=<%= p.getIdProducto()%>">Editar</a>
                            <a class="btn btn-danger" href="Controlador?menu=Producto&accion=Eliminar&id=<%= p.getIdProducto()%>">Eliminar</a>
                        </td>
                            
                            
                        </tr>
                        
                        
                    </tbody>
                    
                    
                    <%}
                
                %>
                
                
                
                
               
                
                
                
            </table>
            
            
            
        </div>
        
        
        
        
     
        
    </div>
    
        
         <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
</body>
</html>

