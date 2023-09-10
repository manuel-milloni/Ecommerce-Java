<%@page import="modelo.CategoriaDAO"%>
<%@page import="modelo.Categoria"%>
<%@page import="modelo.ClienteDAO"%>
<%@page import="modelo.Cliente"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Clientes</title>
  
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
    <%@include file="../estructura/navAdmin.jsp" %>
    
    <div class="d-flex">
        <br>
        <div class="card col-sm-4">
            
               <div class="card-body">
        <h2>Clientes</h2>
         <% if (request.getSession().getAttribute("mensajeExito") != null) { %>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <strong><%= request.getSession().getAttribute("mensajeExito") %></strong>
        <% request.getSession().setAttribute("mensajeExito", null);  %>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
        <% } %>
        
        <% if (request.getSession().getAttribute("mensajeError") != null) { %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <strong><%= request.getSession().getAttribute("mensajeError") %></strong>
        <% request.getSession().setAttribute("mensaje", null); %>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
        <% } %>
        
   
        
        <form action="ControladorCliente?menu=Cliente&accion=NuevoCliente" method="post">
            
            <div class="form-group row">
                <label for="nombre" class="col-sm-3 col-form-label">Nombre:</label>
            <div class="col-sm-9">
                <input type="text" id="nombre" name="nombre" class="form-control" value="${cli.getNombre()}" maxlength="30" required>
            </div>
            </div>
            
             <div class="form-group row">
                <label for="apellido" class="col-sm-3 col-form-label">Apellido:</label>
            <div class="col-sm-9">
            <input type="text" id="apellido" name="apellido" class="form-control" value="${cli.getApellido()}" maxlength="30" required>
            </div>
            </div>
            
             <div class="form-group row">
                <label for="telefono" class="col-sm-3 col-form-label">Telefono:</label>
            <div class="col-sm-9">
            <input type="text" id="telefono" name="telefono" class="form-control" value="${cli.getTelefono()}" maxlength="30" required>
            </div>
            </div>
            
             <div class="form-group row">
                <label for="email" class="col-sm-3 col-form-label">Email:</label>
            <div class="col-sm-9">
            <input type="email" id="email" name="email" class="form-control" value="${cli.getEmail()}" maxlength="50" required>
            </div>
            </div>
            
            <div class="form-group row">
                <label for="direccion" class="col-sm-3 col-form-label">Direccion:</label>
            <div class="col-sm-9">
            <input type="text" id="direccion" name="direccion" class="form-control" value="${cli.getDireccion()}" maxlength="30" required>
            </div>
            </div>
            
            <div class="form-group row">
                <label for="cuit" class="col-sm-3 col-form-label">Cuit:</label>
            <div class="col-sm-9">
            <input type="text" id="cuit" name="cuit" class="form-control" value="${cli.getCuit()}" maxlength="30" required>
            </div>
            </div>
            
            <div class="form-group row">
                <label for="rol" class="col-sm-3 col-form-label">Categoria:</label>
    
                <div class="col-sm-9">
                    <select id="categoria" name="categoria" class="form-control" required>
                      
                        
                        
                        
                    <c:forEach var="cat" items="${categorias}">
                        <option value="${cat.getId()}" ${cat.getDescripcion() eq opcion ? 'selected' : ''}>${cat.getDescripcion()}</option>
                    </c:forEach>
                    </select>
                </div>
            </div>
            
            <div class="form-group row">
                <label for="password" class="col-sm-3 col-form-label">Contraseña:</label>
            <div class="col-sm-9">
            <input type="password" id="password" name="password" class="form-control" value="" maxlength="30" required>
            </div>
            </div>
            
            <div class="form-group row">
                <label for="password_2" class="col-sm-3 col-form-label">Repita Contraseña:</label>
            <div class="col-sm-9">
            <input type="password" id="password_2" name="password_2" class="form-control" value="" maxlength="30" required>
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
                        <th>NOMBRE</th>
                        <th>APELLIDO</th>
                        <th>TELEFONO</th>
                        <th>EMAIL</th>
                        <th>DIRECCION</th>
                        <th>CUIT</th>
                        <th>CATEGORIA</th>
                        <th>ACCIONES</th>
                        
                        
                    </tr>
                    
                    
                </thead>
                <% 
                    ClienteDAO cDAO=new ClienteDAO();
                    List<Cliente> clientes=cDAO.getAll();
                    CategoriaDAO catDAO=new CategoriaDAO();
                    Categoria cat=new Categoria();
                    for(Cliente c: clientes){
                     cat=catDAO.getById(c.getIdCategoria());
                     %> <tbody>
                        <tr>
                            <td><%= c.getNombre() %></td>
                            <td><%=c.getApellido()%></td>
                            <td><%=c.getTelefono()%></td>
                            <td><%=c.getEmail()%></td>
                            <td><%=c.getDireccion()%></td>
                            <td><%=c.getCuit()%></td>
                            
                          
                            
                            <td><%=cat.getDescripcion()%></td>
                            <td>
                            <a class="btn btn-warning" href="ControladorCliente?menu=Cliente&accion=Editar&id=<%=c.getId()%>">Editar</a>
                            <a class="btn btn-danger" href="ControladorCliente?menu=Cliente&accion=Eliminar&id=<%=c.getId()%>">Eliminar</a>
                        </td>
                            
                            
                        </tr>
                        
                        
                    </tbody>
                    <%
                    }

                %>
                
                
                
                
                
                
                
                
                
                
                
                
            </table>
            
            
            
        </div>
        
        
        
        
     
        
    </div>
    
        
         <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
</body>
</html>
