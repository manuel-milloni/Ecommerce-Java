<%@page import="modelo.Cliente"%>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">Home</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
      
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="index.jsp">Productos</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="ControladorVenta?menu=venta&accion=misPedidos">Mis pedidos</a>
        </li>
   
      </ul>
        
        
        
     
      <ul class="navbar-nav">
          
          <%    //SI EL CLIENTE ESTA LOGUEADO MUESTRO EL EMAIL EN EL NAV
                if(request.getSession().getAttribute("auth")!=null){
                 Cliente c=(Cliente)request.getSession().getAttribute("auth");
                 %>
                 <li class="nav-item">
          <a class="nav-link" href="#"><%= c.getEmail() %></a>
        </li>
          <%
              }  
          %>
          
          
          <li class="nav-item">
              <a class="nav-link" href="ControladorCarrito?menu=carrito&accion=carrito">Carrito<span class="badge badge-danger">${carrito_lista.size()}</span></a>
        </li>
       
      
          
            
        <%   //SI EL CLIENTE NO ESTA LOGUEADO MUESTRO LOGIN, Y SI ESTA LOGUEADO MUESTRO LOGOUT
            if(request.getSession().getAttribute("auth")==null){%>
                <li class="nav-item">
          <a class="nav-link" href="login.jsp">Login</a>
        </li>
      </ul>   
           <% 
            } else {%>
               <li class="nav-item">
                <a class="nav-link" href="ControladorLogin?menu=login&accion=logout">Logout</a>
            </li>
            </ul > 
             <%
             } 
             %>
        
      
        
          
        
    
    </div>
  </div>
</nav>

