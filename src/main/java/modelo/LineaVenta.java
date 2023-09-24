
package modelo;


public class LineaVenta {
        private int idLineaVenta;
        private int cantProducto;
        private int nroVenta;
        private int idProducto;
        private double precio;
        
        public LineaVenta(){
        };

    public int getIdLineaVenta() {
        return idLineaVenta;
    }

    public void setIdLineaVenta(int idLineaVenta) {
        this.idLineaVenta = idLineaVenta;
    }

    public int getCantProducto() {
        return cantProducto;
    }

    public void setCantProducto(int cantProducto) {
        this.cantProducto = cantProducto;
    }

    public int getNroVenta() {
        return nroVenta;
    }

    public void setNroVenta(int idVenta) {
        this.nroVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
    
    
        
        
    
}
