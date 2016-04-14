/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 *
 * Materia: Sistemas Transaccionales
 * Ejercicio: VideoAndes
 * Autor: Juan Felipe García - jf.garcia268@uniandes.edu.co
 * -------------------------------------------------------------------
 */
package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Area;
import vos.Bodega;
import vos.Carga;
import vos.Cobertizo;
import vos.Exportador;
import vos.Patio;
import vos.Silo;

public class DAOTablaAreas {

	private ArrayList<Object> recursos;

	private Connection conn;

	public DAOTablaAreas() 
	{
		recursos = new ArrayList<Object>();
	}

	public void cerrarRecursos() 
	{
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}

	public void setConn(Connection con)
	{
		this.conn = con;
	}

	public void addCobertizo(Cobertizo cobertizo) throws SQLException, Exception 
	{
		String sql = "INSERT INTO AREA_ALMACENAMIENTO VALUES ('";
		sql += cobertizo.getId() + "',";
		sql += "'coberticito'" + ")";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();	
		
		
		sql = "INSERT INTO COBERTIZO VALUES ('";
		sql += cobertizo.getId() + "',";
		sql += cobertizo.getDimension() + ",'";
		sql += cobertizo.getTipo() + "','";
		sql += cobertizo.getId() + "')";

		System.out.println("SQL stmt:" + sql);

		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		

	}
	
	public void addBodega (Bodega bodega) throws SQLException, Exception 
	{

		String sql = "INSERT INTO AREA_ALMACENAMIENTO VALUES ('";
		sql += bodega.getId() + "',";
		sql += "'bodeguita'" + ")";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();	
		
		
		sql = "INSERT INTO BODEGA VALUES ('";
		sql += bodega.getId() + "',";
		sql += bodega.getAncho() + ",";
		sql += bodega.getLargo() + ",'";
		sql += bodega.getPlataforma() + "',";
		sql += bodega.getSeparacion() + ",'";
		sql += bodega.getCuartoFrio() + "','";
		sql += bodega.getId() + "')";

		System.out.println("SQL stmt:" + sql);

		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		

	}
	public void addSilo (Silo silo) throws SQLException, Exception 
	{

		String sql = "INSERT INTO AREA_ALMACENAMIENTO VALUES ('";
		sql += silo.getId() + "',";
		sql += "'silito'" + ")";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();	
		
		
		sql = "INSERT INTO SILO VALUES ('";
		sql += silo.getId() + "','";
		sql += silo.getNombre() + "',";
		sql += silo.getCapacidad() + ",'";
		sql += silo.getId() + "')";

		System.out.println("SQL stmt:" + sql);

		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		

	}
	public void addPatio (Patio patio) throws SQLException, Exception 
	{

		String sql = "INSERT INTO AREA_ALMACENAMIENTO VALUES ('";
		sql += patio.getId() + "',";
		sql += "'patiecito'" + ")";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();	
		
		
		sql = "INSERT INTO PATIOS VALUES ('";
		sql += patio.getId() + "',";
		sql += patio.getDimension() + ",'";
		sql += patio.getTipo() + "','";
		sql += patio.getId() + "')";

		System.out.println("SQL stmt:" + sql);

		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		

	}

	public void addCarga(Carga carga) throws SQLException, Exception 
	{
		
		String sql = "INSERT INTO CARGA VALUES ('";
		sql += carga.getId() + "',";
		sql += carga.getTipo_carga() + "',";
		sql += carga.getId_barco() + "',";
		sql += carga.getId_entrega() + "',";
		sql += carga.getId_equipo() + "',";
		sql += carga.getId_vehiculo() + "')";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();	
		
		
	}

	public ArrayList<Area> darAreas() throws SQLException, Exception 
	{
		ArrayList<Area> areas = new ArrayList<Area>();

		String sql = "SELECT * FROM AREA_ALMACENAMIENTO";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String id=rs.getString("ID");
			String nombre=rs.getString("NOMBRE");
			String idPuerto=rs.getString("ID_PUERTO");
			String capacidad=rs.getString("CAPACIDAD");
			String estado=rs.getString("ESTADO");
			String tipo = rs.getString("TIPO");
			areas.add(new Area(id, nombre, idPuerto, capacidad, estado, tipo));
		}
		return areas;
	}
	
	public void reservarArea (String id) throws SQLException
	{
		String sql = "UPDATE AREA_ALMACENAMIENTO SET ";
		sql += "ESTADO = '3' WHERE ID = " + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public Area darArea (String id) throws SQLException
	{
		Area aRetornar = null;
		String sql = "SELECT * FROM AREA_ALMACENAMIENTO WHERE ID = " + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String id2=rs.getString("ID");
			String nombre=rs.getString("NOMBRE");
			String idPuerto=rs.getString("ID_PUERTO");
			String capacidad=rs.getString("CAPACIDAD");
			String estado=rs.getString("ESTADO");
			String tipo = rs.getString("TIPO");
			aRetornar = new Area(id2, nombre, idPuerto, capacidad, estado, tipo);
		}
		return aRetornar;
	}
	
	public void asignarCargaArea (String idArea, String carga) throws SQLException
	{
		String sql = "UPDATE BARCO ";
		sql += "SET CAPACIDAD ='"+ carga +"'";
		sql += "WHERE ID ='"+ idArea +"'";
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public ArrayList<Area> darAreasPorTipo( String tipoCarga) throws SQLException, Exception 
	{
		ArrayList<Area> areas = new ArrayList<Area>();

		String sql = "SELECT * FROM AREA_ALMACENAMIENTO";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String id=rs.getString("ID");
			String nombre=rs.getString("NOMBRE");
			String idPuerto=rs.getString("ID_PUERTO");
			String capacidad=rs.getString("CAPACIDAD");
			String estado=rs.getString("ESTADO");
			String tipo = rs.getString("TIPO");
			areas.add(new Area(id, nombre, idPuerto, capacidad, estado, tipo));
		}
		return areas;
	}
	public void cambiarAreaAlmacenamiento (String idAnterior, String nuevaCap1,
			String nuevaCap2, String idNuevo) throws SQLException
	{
		String sql = "UPDATE AREA_ALMACENAMIENTO SET CAPACIDAD = '";
		sql += nuevaCap1 + "' , ESTADO = '2' WHERE ID = " + idAnterior;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		sql = "UPDATE AREA_ALMACENAMIENTO SET CAPACIDAD = '";
		sql += nuevaCap2 + "' , ESTADO = '1' WHERE ID = " + idNuevo;
		
		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}
	
	public ArrayList<Carga> darCargasArea (String idArea) throws SQLException
	{
		ArrayList<Carga> cargas = new ArrayList<Carga>();

		String sql = "SELECT CARGA.ID, CARGA.TIPO_CARGA, CARGA.ID_BARCO, CARGA.ID_ENTREGA, ";
		sql += "CARGA.ID_EQUIPO, CARGA.ID_VEHICULO, CARGA.ID_AREA, CARGA.TAMANO, CARGA.DESTINO FROM ";
		sql += "CARGA JOIN AREA_ALMACENAMIENTO ";
		sql += "ON CARGA.ID_AREA = AREA_ALMACENAMIENTO.ID ";
		sql += "WHERE AREA_ALMACENAMIENTO.ID = '"+ idArea +"'";
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
			cargas.add(new Carga(id2,tipo_carga,id_barco,id_entrega,id_equipo,id_vehiculo,id_area, tamano, destino));
		}
		
		return cargas;
	}

	



}
