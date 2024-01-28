
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<%@include file="estructura/head.jsp"%>
</head>
<body>

	<%@include file="estructura/nav.jsp"%>
	<div class="container">
		<%
		if (request.getSession().getAttribute("mensaje") != null) {
		%>
		<div class="alert alert-danger alert-dismissible fade show"
			role="alert">
			<strong><%=request.getSession().getAttribute("mensaje")%></strong>
			<%
			request.getSession().setAttribute("mensaje", null);
			%>
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
		<%
		}
		%>

		<div class="card w-50 mx-auto my-5">

			<div class="card-header text-center">Iniciar Sesion</div>
			<div class="card-body">

				<form action="ControladorLogin?menu=login&accion=loginCliente"
					method="post">
					<div class="form-group">
						<label>Email</label> <input type="email" class="form-control"
							name="email" id="email" placeholder="Ingrese su Email" required>
					</div>

					<div class="form-group">
						<label>Contraseña</label> <input type="password"
							class="form-control" name="password" id="password"
							placeholder="Ingrese su contraseña" required>
					</div>
					<br>
					<div class="d-flex justify-content-between align-items-center">
						<button type="submit" class="btn btn-primary">Iniciar
							sesion</button>
						<a href="ControladorLogin?menu=login&accion=registro"
							class="btn btn-primary">Registrarse</a>

					</div>

				</form>

			</div>

		</div>

	</div>

	<%@include file="estructura/footer.jsp"%>
</body>
</html>
