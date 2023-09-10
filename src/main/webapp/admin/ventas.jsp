

<%@page import="modelo.Cliente"%>
<%@page import="modelo.ClienteDAO"%>
<%@page import="modelo.Venta"%>
<%@page import="java.util.List"%>
<%@page import="modelo.VentaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <body>
        <%@include file="../estructura/navAdmin.jsp" %>
        <div class="container">
              <div class="text-center">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>NRO VENTA</th>
                        <th>CLIENTE</th>
                        <th>EMAIL</th>
                        <th>FECHA</th>
                        <th>IMPORTE</th>
                        <th>ACCION</th>
                        
                      
                        
                        
                    </tr>
                    
                    
                </thead>
                <% 
                    VentaDAO vDAO=new VentaDAO();
                    ClienteDAO cDAO=new ClienteDAO();
                    List<Venta> ventas=vDAO.getAll();
                    
                    
                    for(Venta v: ventas){
                     Cliente c=cDAO.getById(v.getIdCliente());
                     %> <tbody>
                        <tr>
                            <td><%= v.getNroVenta() %></td>
                            <td><%=c.getNombre()+", "+c.getApellido()%></td>
                            <td><%=c.getEmail()%></td>
                            <td><%=v.getFecha()%></td>
                            <td>$ importe</td>
                            <td>
                                
                                <a href="" class="btn btn-primary">VER DETALLE</a>
                            </td>
                            
                            
                          
                            
                           
                        
                            
                            
                        </tr>
                        
                        
                    </tbody>
                    <%
                    }

                %>
                
                
                
                
                
                
                
                
                
                
                
                
            </table>
            
            
            
        </div>
        
        
        
        
     
        
    </div>
            
            
            
            
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
    </body>
</html>
