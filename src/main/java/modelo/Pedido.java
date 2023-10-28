package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {
	  private int idPedido;
	  private LocalDate fecha;
	  private Empleado empleado;
	  private Proveedor proveedor;
	  private ArrayList<LineaPedido> lineasPedido;
	  private int nroPedido;
	  
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public ArrayList<LineaPedido> getLineasPedido() {
		return lineasPedido;
	}
	public void setLineasPedido(ArrayList<LineaPedido> lineasPedido) {
		this.lineasPedido = lineasPedido;
	}
	public int getNroPedido() {
		return nroPedido;
	}
	public void setNroPedido(int nroPedido) {
		this.nroPedido = nroPedido;
	}
	
	
	
	
	  
	  
	  
	  

}
