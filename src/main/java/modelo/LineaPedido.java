package modelo;

public class LineaPedido {
	      private int idLineaPedido;
	      private int cantProducto;
	      private int nroPedido;
	      private int idProducto;
	      
	      public LineaPedido() {
	    	  

	      }

		public int getIdLineaPedido() {
			return idLineaPedido;
		}

		public void setIdLineaPedido(int idLineaPedido) {
			this.idLineaPedido = idLineaPedido;
		}

		public int getCantProducto() {
			return cantProducto;
		}

		public void setCantProducto(int cantProducto) {
			this.cantProducto = cantProducto;
		}

		public int getNroPedido() {
			return nroPedido;
		}

		public void setNroPedido(int idPedido) {
			this.nroPedido = idPedido;
		}

		public int getIdProducto() {
			return idProducto;
		}

		public void setIdProducto(int idProducto) {
			this.idProducto = idProducto;
		}
	      
	      
	      

}
