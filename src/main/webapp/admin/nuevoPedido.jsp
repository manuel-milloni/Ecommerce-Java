<%@page import="modelo.TipoProducto"%>
<%@page import="modelo.Producto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.TipoProductoDAO"%>
<%@page import="modelo.ProductoDAO"%>
<%@page import="modelo.Proveedor"%>
<%@page import="modelo.ProveedorDAO"%>
<%@page import="modelo.Carrito"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%

  
 ProveedorDAO provDAO=new ProveedorDAO();
List<Proveedor> proveedores=provDAO.getAll();
request.setAttribute("proveedores" ,proveedores);
  
  ProductoDAO pDAO=new ProductoDAO();
  List<Producto> productos;
  String buscador=(String)request.getAttribute("buscador");
  if(buscador!=null && !buscador.equals("")){
	    productos=pDAO.getByDescripcion(buscador);
	  
	  
  } else {
	   productos=pDAO.getAll();
	  
  }
  ArrayList<Carrito> carritoPedido=(ArrayList<Carrito>)request.getSession().getAttribute("carritoPedido");
  




%>


<!DOCTYPE html>
<html>
<head>
<title>Pedido</title>


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
	<%@include file="../estructura/navAdmin.jsp"%>
	<!-- --------------------------------BUSCADOR-------------------------- -->
	<div class="container text-center">
		<div class="row mt-3">
			<div class="col-12">
				<form action="ControladorPedido?menu=pedido&accion=buscador"
					method="post">
					<div class="input-group" style="max-width: 400px; margin: 0 auto;">
						<input type="text" name="buscador" id="buscador"
							class="form-control" placeholder="Buscar...">
						<button class="btn btn-primary" type="submit">Buscar</button>
					</div>
				</form>
			</div>
		</div>
	</div>



	<div class="d-flex">
		<br>
		<div class="card col-sm-5">

			<div class="card-body">
				<h2>Pedido</h2>

				<% if (request.getAttribute("mensajeExiste") != null) { %>
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					<strong><%= request.getAttribute("mensajeExiste") %></strong>
					<% request.setAttribute("mensajeExiste", null); %>
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
				<% } %>

				<table class="table table-hover">
					<thead>
						<th>ID</th>

						<th>CANTIDAD</th>
						<th>ACCION</th>

					</thead>
					<%
            if (carritoPedido != null) {
				for (Carrito c : carritoPedido) {
					   
					
            
            %>
					<tbody>
						<tr>
							<td><%=c.getIdProducto() %></td>
							<td>
								<form action="" method="post" class="form-inline">
									<input type="hidden" name="id" value="<%=c.getIdProducto()%>"
										class="form-input">
									<div
										class="form-group d-flex align-items-center justify-content-between">
										<a class="btn btn-sm btn-decre"
											href="ControladorCarritoPedido?menu=pedido&accion=decrementar&id=<%=c.getIdProducto()%>"><i
											class="fas fa-minus-square"></i></a> <input type="text"
											name="cantidad" class="form-control text-center"
											value="<%=c.getCantidad()%>" style="width: 100px" readonly>
										<a class="btn btn-sm btn-incre"
											href="ControladorCarritoPedido?menu=pedido&accion=incrementar&id=<%=c.getIdProducto()%>"><i
											class="fas fa-plus-square"></i></a>


									</div>


								</form>


							</td>
							<td><a class="btn btn-sm btn-danger"
								href="ControladorCarritoPedido?menu=pedido&accion=eliminar&id=<%=c.getIdProducto()%>">Eliminar</a>
							</td>
						</tr>
                  <%
				}
               }
                  %>
					</tbody>




				</table>




				<form action="ControladorPedido?menu=pedido&accion=registrar"
					method="post">
					
				

					<div class="form-group row">
						<label for="tipoProducto" class="col-sm-3 col-form-label">Proveedor:</label>

						<div class="col-sm-9">
							<select id="prove" name="prove"
								class="form-control" required>
								<c:forEach var="prov" items="${proveedores}">
									<option value="${prov.getIdProveedor()}"
										${prov.getRazonSocial() eq opcion ? 'selected' : ''}>${prov.getRazonSocial()}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<br>
					<div class="text-center">
						<input type="submit" value="Confirmar Pedido">


					</div>

				</form>
			</div>


		</div>

		<div class="col-sm-7">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>DESCRIPCION</th>

						<th>TIPO</th>
						<th>STOCK</th>
						<th>IMAGEN</th>
						<th>ACCIONES</th>


					</tr>


				</thead>

				<%
                   
                   TipoProductoDAO tpDAO=new TipoProductoDAO();
                  
                   for(Producto p: productos){
                	   TipoProducto tp=tpDAO.getById(p.getIdTipo());
                    
                          
                         %>
				<tbody>
					<tr>

						<td><%=p.getIdProducto()%></td>
						<td><%=p.getDescripcion()%></td>

						<td><%=tp.getDescripcion()%></td>
						<td><%=p.getStock()%></td>
						<td><img src="<%= p.getImagen() %>" alt="Imagen producto"
							class="card-img-top" width="200" height="150"></td>
						<td><a class="btn btn-warning"
							href="ControladorCarritoPedido?menu=pedido&accion=agregar&id=<%= p.getIdProducto()%>">Agregar</a>

						</td>


					</tr>
					<%
				}
               
                
                %>


				</tbody>


				








			</table>



		</div>






	</div>


<%@include file="../estructura/footer.jsp"%>
</body>
</html>

