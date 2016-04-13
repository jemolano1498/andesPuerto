package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Carga;

public class DAOCarga 
{
	private ArrayList<Object> recursos;

	private Connection conn;

	public DAOCarga() {
		recursos = new ArrayList<Object>();
	}

	public void cerrarRecursos() {
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}

	public void setConn(Connection con){
		this.conn = con;
	}
	public void setSerializable () throws SQLException
	{
		String sql = "SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		sql = "SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;";

		System.out.println("SQL stmt:" + sql);
		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	public void commitTransaction () throws SQLException
	{
		String sql = "COMMIT;";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	public void rollBackTransaction () throws SQLException
	{
		String sql = "ROLLBACK;";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void RegistrarCarga (Carga carga) throws SQLException, Exception 
	{
		if (carga.getId_barco()!=null)
		{
			String sql = "INSERT INTO CARGA VALUES ('";
			sql += carga.getId() + "','";
			sql += carga.getTipo_carga() + "','";
			sql += "" + "','";
			sql += carga.getId_entrega() + "','";
			sql += carga.getId_equipo() + "','";
			sql += carga.getId_vehiculo() + "','";
			sql += carga.getId_area() + "','";
			sql += carga.getTamano() + "')";
			System.out.println("SQL stmt:" + sql);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		else
		{
			throw new Exception("Carga no valida");
		}
		

		
		
	}
	
	public Carga buscarCarga (String id) throws SQLException, Exception 
	{
		Carga carga =null;
		String sql = "SELECT * FROM CARGA WHERE ID ='" + id + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();


		while (rs.next())
		{
			String	id2 = (rs.getString("id"));
			String	tipo_carga = (rs.getString("tipo_carga"));
			String	id_barco = rs.getString("id_barco");
			String	id_entrega = (rs.getString("id_entrega"));
			String	id_equipo = (rs.getString("id_equipo"));
			String	id_vehiculo = rs.getString("id_vehiculo");
			String	id_area = rs.getString("id_area");
			String tamano = rs.getString("tamano");
			carga= new Carga(id2,tipo_carga,id_barco,id_entrega,id_equipo,id_vehiculo,id_area, tamano);
		}

		
		
		return carga;
	}
	public void asignarCargaABarco (String idCarga, String idBarco) throws SQLException
	{
		String sql = "UPDATE CARGA";
		sql += " SET ID_BARCO ='"+ idBarco+"',ID_AREA=''";
		sql += " WHERE ID ='"+ idCarga +"'";
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	public void asignarCargaAArea (String idCarga, String idArea) throws SQLException
	{
		String sql = "UPDATE CARGA";
		sql += " SET ID_BARCO ='',ID_AREA='"+ idArea+"'";
		sql += " WHERE ID ='"+ idCarga +"'";
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	

}
