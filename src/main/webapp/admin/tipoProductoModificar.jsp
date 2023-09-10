<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tipo producto</title>
  
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
    <%@include file="../estructura/navAdmin.jsp" %>
    <div class="d-flex">
        <br>
        <div class="card col-sm-5">
            
               <div class="card-body">
        <h2>Tipo producto</h2>
        
        <% if (request.getAttribute("mensaje") != null) { %>
            <div id="messageBox" class="message-box">
                <p><%= request.getAttribute("mensaje") %></p>
                <a href="ControladorTipoProducto?menu=NuevoTipoProducto" class="close-button">&times;</a>
            </div>
            
        <% } %>
        
        <form action="ControladorTipoProducto?menu=TipoProducto&accion=modificar&id=${TipoProducto.getIdTipo()}" method="post">
            
            <div class="form-group row">
                <label for="descripcion" class="col-sm-3 col-form-label">Descripción:</label>
            <div class="col-sm-9">
            <input type="text" id="descripcion" name="descripcion" class="form-control" value="${TipoProducto.getDescripcion()}" required>
            </div>
            </div>
            <br>
            <div class="text-center">
                <a class="btn btn-warning" href="ControladorTipoProducto?menu=TipoProducto&accion=TipoProducto">Cancelar</a>
            <input  class="btn btn-success" type="submit" value="Modificar">
                
                </div>
            
        </form>
    </div>
        
            
        </div>
        
        <div class="col-sm-7">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>DESCRIPCION</th>
                        <th>ACCIONES</th>
                        
                        
                    </tr>
                    
                    
                </thead>
                
                <c:forEach var="tp" items="${tiposProducto}">
                    <tbody>
                        <tr>
                            <td>${tp.getIdTipo()}</td>
                            <td>${tp.getDescripcion()}</td>
                            <td>
                            <a class="btn btn-warning" href="ControladorTipoProducto?menu=TipoProducto&accion=Editar&id=${tp.getIdTipo()}">Editar</a>
                            <a class="btn btn-danger" href="ControladorTipoProducto?menu=TipoProducto&accion=Eliminar&id=${tp.getIdTipo()}">Eliminar</a>
                        </td>
                            
                            
                        </tr>
                        
                        
                    </tbody>
                    
                    
                </c:forEach>
                
                
                
            </table>
            
            
            
        </div>
        
        
        
        
     
        
    </div>
    
        
         <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
</body>
</html>