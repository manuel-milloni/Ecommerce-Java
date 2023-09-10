

<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="modelo.TipoProductoDAO"%>
<%@page import="modelo.TipoProducto"%>
<%@page import="modelo.ProductoDAO"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Carrito"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
ArrayList<Carrito> carrito_lista =(ArrayList<Carrito>) session.getAttribute("carrito-lista");
List<Carrito> carritoProducto=null;
double total=0;
if(carrito_lista !=null){
      ProductoDAO pDAO=new ProductoDAO();
     
      
      
      carritoProducto=pDAO.getCarritoProductos(carrito_lista);
      total=pDAO.getTotal(carrito_lista);
      request.setAttribute("carrito_lista", carrito_lista);
      NumberFormat formatoDecimales = NumberFormat.getCurrencyInstance(Locale.US);
      formatoDecimales.setMaximumFractionDigits(2);
      String totalFormateado = formatoDecimales.format(total);
      request.setAttribute("total", totalFormateado);
     
      
      
    
    } else {
       request.setAttribute("total", total);
    }
    
 
   





%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrito</title>
        <%@include file="estructura/head.jsp" %>
        <style type="text/css">
            .table tbody td{
                    vertical-align: middle;
                
            }
            
            .btn-incre, .btn-decre{
                 box-shadow: none;
                 font-size: 25px;
                
            }
        
        </style>    
    </head>
    <body>
         <%@include file="estructura/nav.jsp" %>
         <div class="container">
             
             <% if (request.getAttribute("mensaje") != null) { %>
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <strong><%= request.getAttribute("mensaje") %></strong>
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                  <% } %>
             <div class="d-flex py-3">
                  
                 <h3>Total:$  ${total}</h3>
                 <a class="mx-3 btn btn-primary" href="ControladorVenta?menu=venta&accion=nuevaVenta">Confirmar pedido</a>
                 
                 
             </div>
             <table class="table table-loght">
                 <thead>
                     <tr>
                         <th scope="col">DESCRIPCION</th>
                         <th scope="col">CATEGORIA</th>
                         <th scope="col">PRECIO</th>
                         <th scope="col">CANTIDAD</th>
                         <th scope="col">ACCION</th>
                         
                     </tr>
                     
                     
                 </thead>
                 <tbody>
                     <% 
                        
                           TipoProductoDAO tpDAO=new TipoProductoDAO();
                           
                           if(carrito_lista != null){
                           for(Carrito c: carritoProducto){
                               TipoProducto tp=new TipoProducto();
                               tp=tpDAO.getById(c.getIdTipo());
                              
                     
                     %>
                           
                            <tr>
                         <td><%= c.getDescripcion() %></td>
                         <td><%= tp.getDescripcion()%></td>
                         <td><%= c.getPrecio() %></td>
                         <td>
                             <form action="" method="post" class="form-inline">
                                 <input type="hidden" name="id" value="<%= c.getIdProducto()%>" calss="form-input">
                                 <div class="form-group d-flex align-items-center justify-content-between">
                                     <a class="btn btn-sm btn-decre" href="ControladorCarrito?menu=carrito&accion=decrementar&id=<%=c.getIdProducto() %>"><i class="fas fa-minus-square"></i></a> 
                                     <input type="text" name="cantidad" class="form-control text-center" value="<%=c.getCantidad() %>" style="width:100px"   readonly>
                                     <a class="btn btn-sm btn-incre" href="ControladorCarrito?menu=carrito&accion=incrementar&id=<%=c.getIdProducto() %>"><i class="fas fa-plus-square"></i></a>
                                    
                                     
                                 </div>
                                 
                                 
                             </form> 
                             
                             
                         </td>
                         <td>
                             <a class="btn btn-sm btn-danger" href="ControladorCarrito?menu=carrito&accion=eliminar&id=<%=c.getIdProducto()%>">Eliminar</a>
                             
                         </td>
                         
                     </tr>
                         
                         <%
                         }
                         
                         }
                     
                     
                     %>
                     
                     
                     
                    
                     
                 </tbody>
                 
                 
             </table>
             
             
         </div>
        
         <%@include file="estructura/footer.jsp" %>
    </body>
</html>
