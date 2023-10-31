# Ecommerce-Java
Tp Java Ecommerce

- Listado de integrantes: indicar legajo, apellido y nombres.

Mozzi Feliciano - 47857

Ferullo Matías - 48039 

Milloni Manuel - 40514

- Enunciado general del tp. Uno o dos párrafos describiendo el sistema.

Un local de venta de componentes de PC nos pidió que le desarrollásemos una página web. Esta le permitirá a un cliente ingresar y realizar una compra. A un empleado dar de alta, baja o modificar productos, gestionar su stock, y administrar a los clientes. Además, un administrador podrá dar de alta, baja o modificar empleados, dar de alta, baja o modificar proveedores, realizar pedidos a proveedores y también podrá realizar las funciones que realiza un empleado.

- Imagen con un borrador del modelo de dominio/modelo de datos.


**CHECKLIST**

Regularidad



|**Requerimiento**|**cant. mín. 1 o 2 integ**|**cant. mín. 3 o 4 integ**|**Detalle/Listado de casos incluídos**|
| - | - | - | - |
|**ABMC Simple**|1 x integ|1 x integ|<p>1. ABMC TipoProducto</p><p>&emsp;2. ABMC Proveedor</p><p>&emsp;3. ABMC Rol</p><p>&emsp;4. ABMC Categoría</p>|
|**ABMC Dependiente**|1|2|<p>1. ABMC Cliente {depende de } ABMC Categoría</p><p>2. ABMC Producto {depende de} ABMC Tipo producto</p>|
|**CU NO-ABMC**|1|2|<p>1. CUU Registrar una venta</p><p>2. CUU Realizar un pedido</p>|
|**Listado simple**|1|3(\*)|1\. Listado de los productos|
|**Listado complejo**|0|1(\*)|1\. Listado de los productos por descripción.|

(\*) los grupos de 3 y 4 integrantes deben elegir entre 1 listado complejo o 3 simples para regularizar.


Aprobación Directa



|**Requerimiento**|**cant. mín. 1 o 2 integ**|**cant. mín. 3 o 4 integ**|**Detalle/Listado de casos incluídos**|
| - | - | - | - |
|**ABMC**|Todos|Todos|<p>1. ABMC Empleados</p><p>2. ABMC Pedidos</p><p>3. ABMC Cliente</p><p>4. ABMC Venta</p><p>5. ABMC Productos</p><p>6. ABMC Proveedor</p><p>7. ABMC Linea de venta</p><p>8. ABMC Tipo producto</p><p>9. ABMC Rol</p><p>10. ABMC Categoria</p><p>11. ABMC Linea de pedido</p>|
|**CU “Complejo” (nivel resumen)**|1|2|<p>1. CUR Registrar una venta</p><p>2. CUR Realizar un pedido</p>|
|**Listado complejo**|1|2|<p>1. Listado de los productos por descripción.</p><p>2. Listado de los proveedores por Razón Social</p>|
|**Nivel de acceso**|2|2|<p>1. Empleado</p><p>2. Administrador</p><p>3. Cliente</p>|
|**Manejo de errores**|Obligatorio|Obligatorio|No requiere detalle|
|**Requerimiento extra obligatorio (\*\*)**|0|1|1\. Manejo de archivos|
|**Publicar el sitio**|Obligatorio|Obligatorio|No requiere detalle|

(\*\*) sólo grupos de 3 y 4 integrantes

Requerimientos extra - AD

|**Requerimiento**|**Detalle/Listado de casos incluídos**|
| - | - |
|**Manejo de archivos**|1\. Los productos cuentan con sus imágenes.|
|**Custom exceptions**||
|**Log de errores**||
|**Envío de emails**||
