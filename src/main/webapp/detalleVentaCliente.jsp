



<%@page import="modelo.ProductoDAO"%>
<%@page import="modelo.Producto"%>
<%@page import="modelo.Venta"%>
<%@page import="modelo.LineaVenta"%>




<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <% Venta venta=(Venta)request.getAttribute("venta");
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
	<%@include file="../estructura/nav.jsp"%>
	<div class="container">
		<br>
		



		<br>

		<div class="text-center">
			<table class="table table-hover">
				<thead>
					<tr>
						
						<th>CLIENTE</th>
						<th>EMAIL</th>
						<th>FECHA</th>
						<th>IMPORTE</th>
						
						




					</tr>


				</thead>
	
				<tbody>
					<tr>
					
						
						<td><%=venta.getCliente().getNombre() + ", " + venta.getCliente().getApellido()%></td>
						<td><%=venta.getCliente().getEmail()%></td>
						<td><%=venta.getFecha().format(formatter)%></td>
						<td><%=venta.getTotal()%></td>
						
						
					








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
						<th>PRECIO</th>
						
						
						




					</tr>


				</thead>
	            <%
	              ProductoDAO pDAO=new ProductoDAO();
	              for(LineaVenta lv: venta.getLineas()){
	            	  Producto producto=pDAO.getById(lv.getIdProducto());
	            %>
				<tbody>
					<tr>
					 
						<td><%=producto.getDescripcion()%></td>
						<td><%=lv.getCantProducto()%></td>
						<td><%=lv.getPrecio()%></td>

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