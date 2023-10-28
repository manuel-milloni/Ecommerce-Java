<%@page import="modelo.Pedido"%>
<%@page import="modelo.PedidoDAO"%>
<%@page import="modelo.EmpleadoDAO"%>
<%@page import="modelo.Empleado"%>
<%@page import="modelo.LineaPedido"%>
<%@page import="modelo.LineaPedidoDAO"%>
<%@page import="modelo.ProveedorDAO"%>
<%@page import="modelo.Proveedor"%>
<%@page import="modelo.ProductoDAO"%>
<%@page import="modelo.Producto"%>




<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <% Pedido pedido=(Pedido)request.getAttribute("pedido");
					    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					    
					    %>




<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pedidos</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</head>
<body>
	<%@include file="../estructura/navAdmin.jsp"%>
	<div class="container">
		<br>
		



		<br>

		<div class="text-center">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>NRO PEDIDO</th>
						<th>FECHA</th>
						<th>EMPLEADO</th>
						<th>PROVEEDOR</th>
						
						




					</tr>


				</thead>
	
				<tbody>
					<tr>
					
						<td><%=pedido.getIdPedido()%></td>
						<td><%=pedido.getFecha().format(formatter)%></td>
						<td><%=pedido.getEmpleado().getNombre() + ", " + pedido.getEmpleado().getApellido()%></td>
						<td><%=pedido.getProveedor().getRazonSocial()%></td>
						
						
					








					</tr>


				</tbody>
		


			</table>



		</div>
		<div class="text-center">
		    <h1 >Detalle</h1>
		</div> 
		
			<div class="text-center">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>PRODUCTO</th>
						<th>CANTIDAD</th>
						
						
						
						




					</tr>


				</thead>
	            <%
	              ProductoDAO pDAO=new ProductoDAO();
	              for(LineaPedido lp: pedido.getLineasPedido()){
	            	  Producto producto=pDAO.getById(lp.getIdProducto());
	            %>
				<tbody>
					<tr>
					 
						<td><%=producto.getDescripcion()%></td>
						<td><%=lp.getCantProducto()%></td>

					</tr>


				</tbody>
		     <%
	              }
		     %>


			</table>



		</div>
		  


	</div>









</body>
</html>
