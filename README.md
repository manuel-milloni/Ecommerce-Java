# Ecommerce-Java
Tp Java Ecommerce

- Listado de integrantes: indicar legajo, apellido y nombres.

Mozzi Feliciano - 47857

Ferullo Matías - 48039 

Milloni Manuel - 40514

- Enunciado general del tp. Uno o dos párrafos describiendo el sistema.

Una empresa de venta de componentes de computadora nos pidió que le desarrollásemos una página web para su gestión. Esta le permitirá a un empleado dar de alta, baja o modificar productos, gestionar su stock, registrar a un cliente y registrar ventas. Además, un administrador podrá dar de alta, baja o modificar empleados, dar de alta, baja o modificar proveedores, realizar pedidos a proveedores y también podrá realizar las funciones que realiza un empleado.

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
