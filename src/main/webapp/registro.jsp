




<%@page import="java.util.List"%>
<%@page import="modelo.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.CategoriaDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
   CategoriaDAO cDAO=new CategoriaDAO();
   List<Categoria> categorias=cDAO.getAll();
   request.setAttribute("categorias", categorias);
   
   




%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <%@include file="estructura/head.jsp" %>
    </head>
    <body>
        
        <%@include file="estructura/nav.jsp" %>    
        <div class="container">
                <% if (request.getSession().getAttribute("mensajeError") != null) { %>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <strong><%= request.getSession().getAttribute("mensajeError") %></strong>
                     <%request.getSession().setAttribute("mensajeError", null);  %>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                 </div>
        <% } %>
            
            <div class="card w-50 mx-auto my-5">
                
                
                
                <div class="card-header text-center">Registro</div>
                <div class="card-body">
                    <form action="ControladorCliente?menu=Cliente&accion=NuevoCliente" method="post">
                        <div class="form-group">
                            <label>Nombre</label>
                            <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Ingrese su Nombre" maxlength="30" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Apellido</label>
                            <input type="text" class="form-control" name="apellido" id="Apellido" placeholder="Ingrese su apellido" maxlength="30" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Telefono</label>
                            <input type="text" class="form-control" name="telefono" id="telefono" placeholder="Ingrese su telefono" maxlength="30" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" class="form-control" name="email" id="email" placeholder="Ingrese su Email" maxlength="50" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Direccion</label>
                            <input type="text" class="form-control" name="direccion" id="direccion" placeholder="Ingrese su direccion" maxlength="30" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Cuit</label>
                            <input type="text" class="form-control" name="cuit" id="cuit" placeholder="Ingrese su cuit" maxlength="30" required>
                        </div>
                        
                        <div class="form-group ">
                            <label for="rol" class="col-sm-3 col-form-label">Categoria:</label>
    
                            
                            <select id="categoria" name="categoria" class="form-control" required>
                      
                        
                        
                        
                            <c:forEach var="cat" items="${categorias}">
                                <option value="${cat.getId()}" ${cat.getDescripcion() eq opcion ? 'selected' : ''}>${cat.getDescripcion()}</option>
                            </c:forEach>
                            </select>
                            
                        </div>
                        
                        <div class="form-group">
                            <label>Contraseña</label>
                            <input type="password" class="form-control" name="password_1" id="password_1" placeholder="Ingrese su contraseña" maxlength="30"  required>
                        </div>
                        
                        <div class="form-group">
                            <label>Repita contraseña</label>
                            <input type="password" class="form-control" name="password_2" id="password_2" placeholder="Ingrese su contraseña" maxlength="30" required>
                        </div>
                        
                        
                        <br>   
                        <div class="d-flex justify-content-between align-items-center">
                            <button type="submit" class="btn btn-primary">Regristrarse</button>
                            <a href="ControladorInicio?menu=inicio&accion=inicio" class="btn btn-danger">Cancelar</a>
                            
                        </div>    
                            
                        
                        
                        
                        
                    </form>
                    
                    
                    
                </div>
                
                
                
            </div>
            
            
        </div>
        
        <%@include file="estructura/footer.jsp" %>
    </body>
</html>

