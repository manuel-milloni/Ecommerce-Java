package modelo;

import java.sql.*;
import config.Conexion;
import java.util.ArrayList;

public class LineaPedidoDAO {
	private Connection conexion;
	private ResultSet rs;
	private PreparedStatement ps;

	public LineaPedidoDAO() {

	}

	public void save(LineaPedido lp) {
		String sql = "INSERT INTO linea_de_pedido(cantProducto, nroPedido, idProducto) VALUES(?,?,?);";
		try {
			Conexion con = new Conexion();
			conexion = con.getConexion();
			ps = conexion.prepareStatement(sql);
			ps.setInt(1, lp.getCantProducto());
			ps.setInt(2, lp.getNroPedido());
			ps.setInt(3, lp.getIdProducto());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			System.out.println(e.toString());

		}

	}

	public ArrayList<LineaPedido> getAllByPedido(int id) {
		String sql = "SELECT * FROM linea_de_pedido WHERE idPedido=?;";
		ArrayList<LineaPedido> lineas = new ArrayList<>();
		try {
			Conexion con = new Conexion();
			conexion = con.getConexion();
			ps = conexion.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				LineaPedido lp = new LineaPedido();
				lp.setIdLineaPedido(rs.getInt("idLineaPedido"));
				lp.setCantProducto(rs.getInt("cantProducto"));

				lp.setNroPedido(rs.getInt("NroPedido"));
				lp.setIdProducto(rs.getInt("idProducto"));
				lineas.add(lp);

			}

		} catch (SQLException e) {
			System.out.println(e.toString());

		}
		return lineas;

	}

}
