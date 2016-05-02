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
			String destino = rs.getString("destino");
			carga= new Carga(id2,tipo_carga,id_barco,id_entrega,id_equipo,id_vehiculo,id_area, tamano, destino);
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

	public ArrayList<Carga> consultarCargasImportador(String origen, String idImportador, String idCarga,
			String destino, String tipo, String fecha) throws SQLException 
	{
		ArrayList<Carga> cargas = new ArrayList<Carga>();
		String sql = "SELECT CARGA.ID, CARGA.TIPO_CARGA, CARGA.ID_BARCO, CARGA.ID_ENTREGA, CARGA.ID_EQUIPO,";
		sql += "CARGA.ID_VEHICULO, CARGA.ID_AREA, CARGA.TAMANO, CARGA.DESTINO ";
		sql +="FROM (ENTREGA JOIN IMPORTADOR ON ENTREGA.ID_IMPORTADOR= IMPORTADOR.ID) JOIN CARGA ON ENTREGA.ID=CARGA.ID_ENTREGA WHERE ";
		sql +="IMPORTADOR.ID = '"+idImportador+"' ";
		int a=0;
		if (!destino.equals(""))
		{
			sql+="AND ";
			sql += "CARGA.DESTINO = (SELECT ID FROM PUERTO WHERE PUERTO.NOMBRE ='"+destino+"') ";
			a++;
		}
		if (!fecha.equals(""))
		{
			if (a>0)
			{
				sql+="AND ";
			}
			sql += "ENTREGA.FECHA = '"+fecha+"' ";
			a++;
		}
		if (!tipo.equals(""))
		{
			if (a>0)
			{
				sql+="AND ";
			}
			sql += "CARGA.TIPO_CARGA = (SELECT ID FROM TIPO_CARGA WHERE TIPO_CARGA.NOMBRE ='"+tipo+"') ";
			a++;
		}
		if (!idCarga.equals(""))
		{
			if (a>0)
			{
				sql+="AND ";
			}
			sql += "CARGA.ID = '"+idCarga+"' ";
			a++;
		}
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
			String destino2 = rs.getString("destino");
			cargas.add(new Carga(id2,tipo_carga,id_barco,id_entrega,id_equipo,id_vehiculo,id_area, tamano, destino2));
		}
		return cargas;


	}

	public ArrayList<Carga> consultarCargas(String origen, String idCarga, String destino, String tipo, String fecha) throws SQLException {
		ArrayList<Carga> cargas = new ArrayList<Carga>();
		String sql = "SELECT CARGA.ID, CARGA.TIPO_CARGA, CARGA.ID_BARCO, CARGA.ID_ENTREGA, CARGA.ID_EQUIPO,";
		sql += "CARGA.ID_VEHICULO, CARGA.ID_AREA, CARGA.TAMANO, CARGA.DESTINO ";
		sql +="FROM (ENTREGA JOIN IMPORTADOR ON ENTREGA.ID_IMPORTADOR= IMPORTADOR.ID) JOIN CARGA ON ENTREGA.ID=CARGA.ID_ENTREGA WHERE ";

		int a=0;
		if (!destino.equals(""))
		{
			sql += "CARGA.DESTINO = (SELECT ID FROM PUERTO WHERE PUERTO.NOMBRE ='"+destino+"') ";
			a++;
		}
		if (!fecha.equals(""))
		{
			if (a>0)
			{
				sql+="AND ";
			}
			sql += "ENTREGA.FECHA = '"+fecha+"' ";
			a++;
		}
		if (!tipo.equals(""))
		{
			if (a>0)
			{
				sql+="AND ";
			}
			sql += "CARGA.TIPO_CARGA = (SELECT ID FROM TIPO_CARGA WHERE TIPO_CARGA.NOMBRE ='"+tipo+"') ";
			a++;
		}
		if (!idCarga.equals(""))
		{
			if (a>0)
			{
				sql+="AND ";
			}
			sql += "CARGA.ID = '"+idCarga+"' ";
			a++;
		}
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
			String destino2 = rs.getString("destino");
			cargas.add(new Carga(id2,tipo_carga,id_barco,id_entrega,id_equipo,id_vehiculo,id_area, tamano, destino2));
		}
		return cargas;
	}


}
