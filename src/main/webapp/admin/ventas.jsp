

<%@page import="modelo.Cliente"%>
<%@page import="modelo.ClienteDAO"%>
<%@page import="modelo.Venta"%>
<%@page import="modelo.LineaVentaDAO"%>
<%@page import="modelo.LineaVenta"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.VentaDAO"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
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
					class="d-flex justify-content-between">
					<!-- Campo de búsqueda de cliente  -->
					<div class="input-group" style="max-width: 400px">
						<input type="text" id="cliente" name="cliente"
							class="form-control" placeholder="Buscar cliente">
						<div class="input-group-append">
							<button class="btn btn-primary" type="submit">Buscar</button>
							
						</div>
					</div>

					<!-- Campo de búsqueda de fechas -->
					<div class="input-group ml-3" style="max-width: 600px">
						<input type="text" id="fechaDesde" name="fechaDesde"
							class="form-control" placeholder="Desde " readonly>
						<div class="input-group-append ml-3">
							<button class="btn btn-outline-secondary" type="button"
								id="openDatePickerDesde">Desde</button>
						</div>

						<input type="text" id="fechaHasta" name="fechaHasta"
							class="form-control" placeholder="Hasta " readonly>
						<div class="input-group-append ml-3">
							<button class="btn btn-outline-secondary" type="button"
								id="openDatePickerHasta">Hasta</button>
						</div>

					
						<div class="input-group-append ml-3">
							<button class="btn btn-primary" type="submit">Buscar</button>
						</div>
					</div>
				</form>
			</div>
		</div>








		<br>

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
				VentaDAO vDAO = new VentaDAO();
				ClienteDAO cDAO = new ClienteDAO();
				LineaVentaDAO lvDAO = new LineaVentaDAO();
				List<Venta> ventas = vDAO.getAll();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

				for (Venta v : ventas) {
					Cliente c = cDAO.getById(v.getIdCliente());
					ArrayList<LineaVenta> lineas = lvDAO.getAllByVenta(v.getNroVenta());
					v.setLineas(lineas);
				%>
				<tbody>
					<tr>
						<td><%=v.getNroVenta()%></td>
						<td><%=c.getNombre() + ", " + c.getApellido()%></td>
						<td><%=c.getEmail()%></td>
						<td><%=v.getFecha().format(formatter)%></td>
						<td><%=v.getTotal()%></td>
						<td><a href="" class="btn btn-primary">VER DETALLE</a></td>








					</tr>


				</tbody>
				<%
				}
				%>


			</table>



		</div>


	</div>





	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
		integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
		integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
		crossorigin="anonymous"></script>

	<script>
		$(function() {
			$("#fechaDesde").datepicker({
				dateFormat : "dd/mm/yy", // Formato de fecha
				changeMonth : true, // Permite cambiar el mes
				changeYear : true
			// Permite cambiar el año
			});

			$("#fechaHasta").datepicker({
				dateFormat : "dd/mm/yy", // Formato de fecha
				changeMonth : true, // Permite cambiar el mes
				changeYear : true
			// Permite cambiar el año
			});

			$("#openDatePickerDesde").click(function() {
				$("#fechaDesde").datepicker("show");
			});

			$("#openDatePickerHasta").click(function() {
				$("#fechaHasta").datepicker("show");
			});
		});
	</script>



</body>
</html>
