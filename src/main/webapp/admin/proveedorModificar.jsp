<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Proveedores</title>
  
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
    <%@include file="../estructura/navAdmin.jsp" %>
    <div class="d-flex">
        <br>
        <div class="card col-sm-5">
            
               <div class="card-body">
        <h2>Proveedores</h2>
        
       
        
        <form action="ControladorProveedor?menu=Proveedor&accion=Modificar&id=${proveedor.getIdProveedor()}" method="post">
            
            <div class="form-group row">
                <label for="cuit" class="col-sm-3 col-form-label">Cuit:</label>
            <div class="col-sm-9">
            <input type="text" id="cuit" name="cuit" class="form-control" value="${proveedor.getCuit()}" required>
            </div>
            </div>
            
             <div class="form-group row">
                <label for="razonSocial" class="col-sm-3 col-form-label">Razon Social:</label>
            <div class="col-sm-9">
            <input type="text" id="razonSocial" name="razonSocial" class="form-control" value="${proveedor.getRazonSocial()}" required>
            </div>
            </div>
            
             <div class="form-group row">
                <label for="telefono" class="col-sm-3 col-form-label">Telefono:</label>
            <div class="col-sm-9">
            <input type="text" id="telefono" name="telefono" class="form-control" value="${proveedor.getTelefono()}" required>
            </div>
            </div>
            
             <div class="form-group row">
                <label for="email" class="col-sm-3 col-form-label">Email:</label>
            <div class="col-sm-9">
            <input type="text" id="email" name="email" class="form-control" value="${proveedor.getEmail()}" required>
            </div>
            </div>
            
            <br>
           
            <div class="text-center">
                <a class="btn btn-warning" href="ControladorProveedor?menu=Proveedor&accion=Proveedor">Cancelar</a>
            <input  class="btn btn-success" type="submit" value="Modificar">
                
                </div>
            
        </form>
    </div>
        
            
        </div>
        
        <div class="col-sm-7">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>CUIT</th>
                        <th>RAZON SOCIAL</th>
                        <th>TELEFONO</th>
                        <th>EMAIL</th>
                        <th>ACCIONES</th>
                        
                        
                    </tr>
                    
                    
                </thead>
                
                <c:forEach var="prov" items="${proveedores}">
                    <tbody>
                        <tr>
                            <td>${prov.getCuit()}</td>
                            <td>${prov.getRazonSocial()}</td>
                            <td>${prov.getTelefono()}</td>
                            <td>${prov.getEmail()}</td>
                            <td>
                            <a class="btn btn-warning" href="ControladorProveedor?menu=Proveedor&accion=Editar&id=${prov.getIdProveedor()}">Editar</a>
                            <a class="btn btn-danger" href="ControladorProveedor?menu=Proveedor&accion=Eliminar&id=${prov.getIdProveedor()}">Eliminar</a>
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
