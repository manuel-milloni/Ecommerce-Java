<%@page import="modelo.Empleado"%>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">Home</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
       
          <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Controlador?menu=Producto&accion=Producto">Productos</a>
        </li>
        
        
        <%
           Empleado emp=(Empleado)request.getSession().getAttribute("authEmpleado");
           if(emp.getIdRol()==1){%>
            <li class="nav-item">
          <a class="nav-link" href="ControladorEmpleado?menu=Empleado&accion=Empleado">Empleados</a>
        </li>
            
          <%  
            } else {%>
                 <li class="nav-item">
                <a class="nav-link disabled" aria-disabled="true" href="ControladorEmpleado?menu=Empleado&accion=Empleado">Empleados</a>
                </li>
            <%
            }
        
        
        
        %>
        
        
        <li class="nav-item">
          <a class="nav-link" href="ControladorVenta?menu=venta&accion=venta">Ventas</a>
        </li>
        
        <li class="nav-item">
          <a class="nav-link" href="ControladorCliente?menu=Cliente&accion=Cliente">Clientes</a>
        </li>
        
        <li class="nav-item">
          <a class="nav-link" href="#">Pedidos</a>
        </li>
        
        <li class="nav-item">
          <a class="nav-link" href="ControladorProveedor?menu=Proveedor&accion=Proveedor">Proveedores</a>
        </li>
        
        
        
        <li class="nav-item">
          <a class="nav-link" href="ControladorTipoProducto?menu=TipoProducto&accion=TipoProducto">Tipos productos</a>
        </li>
        
         <li class="nav-item">
          <a class="nav-link" href="ControladorCategoria?menu=Categoria&accion=Categoria">Categorias</a>
        </li>
        
  
    
      </ul>
        
   
      <ul class="navbar-nav">
          
       
                 <li class="nav-item">
          <a class="nav-link" href="#"><%= emp.getEmail() %></a>
        </li>
      
          

          
          
          <li class="nav-item">
          <a class="nav-link" href="#">Carrito</a>
        </li>
        

          
        <li class="nav-item">
          <a class="nav-link" href="Admin?accion=logout">Logout</a>
        </li>
      </ul>
    
    </div>
  </div>
</nav>
