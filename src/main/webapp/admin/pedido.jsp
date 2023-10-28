

<%@page import="modelo.Pedido"%>
<%@page import="modelo.PedidoDAO"%>
<%@page import="modelo.EmpleadoDAO"%>
<%@page import="modelo.Empleado"%>
<%@page import="modelo.LineaPedido"%>
<%@page import="modelo.LineaPedidoDAO"%>
<%@page import="modelo.ProveedorDAO"%>
<%@page import="modelo.Proveedor"%>




<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   PedidoDAO pDAO=new PedidoDAO();
   EmpleadoDAO eDAO= new EmpleadoDAO();
   LineaPedidoDAO lpDAO=new LineaPedidoDAO();
   ProveedorDAO provDAO=new ProveedorDAO();
   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

   String buscador=(String) request.getAttribute("buscador");
  
   List<Pedido> pedidos;
   if(buscador!=null && !buscador.equals("")){
	   pedidos=pDAO.getAll();
	    
	   
   } else {
	    pedidos=pDAO.getAll();
	   
   }
   
   
   if(!pedidos.isEmpty()){
	   for(Pedido pedido: pedidos){
		   Empleado empleado = eDAO.getById(pedido.getEmpleado().getId());
		   pedido.setEmpleado(empleado);
		   Proveedor proveedor=provDAO.getById(pedido.getProveedor().getIdProveedor());
		   pedido.setProveedor(proveedor);
		   ArrayList<LineaPedido> lineas = lpDAO.getAllByPedido(pedido.getIdPedido());
		  pedido.setLineasPedido(lineas); 
		   
		   
	   }
	   
	   
   }

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
		
        <!-- -----------------------------------BUSCADOR -----------------------------------  -->
<div class="container">
    <div class="text-center">
        <form action="ControladorVenta?menu=venta&accion=buscar" method="post"
            class="d-flex justify-content-between align-items-center">
            <!-- Campo de búsqueda de cliente -->
            <div class="input-group" style="max-width: 400px">
                <input type="text" id="cliente" name="cliente"
                    class="form-control" placeholder="Buscar por proveedor">
                <div class="input-group-append">
                    <button class="btn btn-primary" type="submit">Buscar</button>
                </div>
            </div>
        </form>
    </div>
  <div class="text-center" style="margin-top: -55px;">
    <a class="btn btn-primary" href="ControladorPedido?menu=pedido&accion=nuevoPedido">Realizar Pedido</a>
</div>

</div>


		<br>

		<div class="text-center">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>NRO PEDIDO</th>
						<th>FECHA</th>
						<th>EMPLEADO</th>
						<th>PROVEEDOR</th>
						
						<th>ACCION</th>




					</tr>


				</thead>
				<%
		

				for (Pedido pedido : pedidos) {
				
				%>
				<tbody>
					<tr>
						<td><%=pedido.getIdPedido()%></td>
						<td><%=pedido.getFecha().format(formatter)%></td>
						<td><%=pedido.getEmpleado().getNombre() + ", " + pedido.getEmpleado().getApellido()%></td>
						<td><%=pedido.getProveedor().getRazonSocial()%></td>
						
						
						<td><a href="" class="btn btn-primary">VER DETALLE</a></td>








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
