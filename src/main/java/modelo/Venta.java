
package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Venta {
	private int id;
	private LocalDateTime fecha;
	private int idCliente;
	private int nroVenta;
	private ArrayList<LineaVenta> lineas;

	public Venta() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getNroVenta() {
		return nroVenta;
	}

	public void setNroVenta(int nroVenta) {
		this.nroVenta = nroVenta;
	}

	public ArrayList<LineaVenta> getLineas() {
		return lineas;
	}

	public void setLineas(ArrayList<LineaVenta> lineas) {
		this.lineas = lineas;
	}
	
	public double getTotal() {
		double total=0;
		for(LineaVenta lv: lineas) {
			   total=total+(lv.getPrecio()*lv.getCantProducto());
			
			
		}
		
		return total;
		
		
		
	}
	
	

}
