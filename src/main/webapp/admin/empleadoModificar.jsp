<%@page import="modelo.Rol"%>
<%@page import="modelo.RolDAO"%>
<%@page import="modelo.Empleado"%>
<%@page import="java.util.List"%>
<%@page import="modelo.EmpleadoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Empleados</title>
  
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
     <%@include file="../estructura/navAdmin.jsp" %>
    <div class="d-flex">
        <br>
        <div class="card col-sm-4">
            
               <div class="card-body">
        <h2>Empleados</h2>
        
   
        
        <form action="ControladorEmpleado?menu=Empleado&accion=Modificar&id=${empleado.getId()}" method="post">
            
            <div class="form-group row">
                <label for="nombre" class="col-sm-3 col-form-label">Nombre:</label>
            <div class="col-sm-9">
            <input type="text" id="nombre" name="nombre" class="form-control" value="${empleado.getNombre()}" required>
            </div>
            </div>
            
             <div class="form-group row">
                <label for="apellido" class="col-sm-3 col-form-label">Apellido:</label>
            <div class="col-sm-9">
            <input type="text" id="apellido" name="apellido" class="form-control" value="${empleado.getApellido()}" required>
            </div>
            </div>
            
             <div class="form-group row">
                <label for="telefono" class="col-sm-3 col-form-label">Telefono:</label>
            <div class="col-sm-9">
            <input type="text" id="telefono" name="telefono" class="form-control" value="${empleado.getTelefono()}" required>
            </div>
            </div>
            
             <div class="form-group row">
                <label for="email" class="col-sm-3 col-form-label">Email:</label>
            <div class="col-sm-9">
            <input type="email" id="email" name="email" class="form-control" value="${empleado.getEmail()}" required>
            </div>
            </div>
            
            <div class="form-group row">
                <label for="direccion" class="col-sm-3 col-form-label">Direccion:</label>
            <div class="col-sm-9">
            <input type="text" id="direccion" name="direccion" class="form-control" value="${empleado.getDireccion()}" required>
            </div>
            </div>
            
            <div class="form-group row">
                <label for="dni" class="col-sm-3 col-form-label">Dni:</label>
            <div class="col-sm-9">
            <input type="text" id="dni" name="dni" class="form-control" value="${empleado.getDni()}" required>
            </div>
            </div>
            
            <div class="form-group row">
                <label for="rol" class="col-sm-3 col-form-label">Rol:</label>
    
                <div class="col-sm-9">
                    <select id="rol" name="rol" class="form-control" required>
                    <c:forEach var="rol" items="${roles}">
                        <option value="${rol.getId()}" ${rol.getDescripcion() eq opcion ? 'selected' : ''}>${rol.getDescripcion()}</option>
                    </c:forEach>
                    </select>
                </div>
            </div>
            
         
            
            <br>
           
            <div class="text-center">
                <a class="btn btn-warning" href="ControladorEmpleado?menu=Empleado&accion=Empleado">Cancelar</a>
            <input  class="btn btn-success" type="submit" value="Modificar">
                
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
                        <th>DNI</th>
                        <th>ROL</th>
                        <th>ACCIONES</th>
                        
                        
                    </tr>
                    
                    
                </thead>
                
                <% 
                    EmpleadoDAO eDAO=new EmpleadoDAO();
                    List<Empleado> empleados=eDAO.getAll();
                    RolDAO rDAO=new RolDAO();
                    Rol rol=new Rol();
                    for(Empleado e: empleados){
                     rol=rDAO.getById(e.getIdRol());
                     %> <tbody>
                        <tr>
                            <td><%= e.getNombre() %></td>
                            <td><%=e.getApellido()%></td>
                            <td><%=e.getTelefono()%></td>
                            <td><%=e.getEmail()%></td>
                            <td><%=e.getDireccion()%></td>
                            <td><%=e.getDni()%></td>
                            
                          
                            
                            <td><%=rol.getDescripcion()%></td>
                            <td>
                            <a class="btn btn-warning" href="ControladorEmpleado?menu=Empleado&accion=Editar&id=<%=e.getId()%>">Editar</a>
                            <a class="btn btn-danger" href="ControladorEmpleado?menu=Empleado&accion=Eliminar&id=<%=e.getId()%>">Eliminar</a>
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
