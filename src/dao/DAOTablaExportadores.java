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

import vos.Exportador;
import vos.factura;

public class DAOTablaExportadores {

	private ArrayList<Object> recursos;

	private Connection conn;

	public DAOTablaExportadores() 
	{
		recursos = new ArrayList<Object>();
	}

	public void cerrarRecursos() 
	{
		for(Object ob : recursos)
		{
			if(ob instanceof PreparedStatement)
			{
				try 
				{
					((PreparedStatement) ob).close();
				} 
				catch (Exception ex) 
				{
					ex.printStackTrace();
				}
			}
		}
	}

	public void setConn(Connection con)
	{
		this.conn = con;
	}

	public ArrayList<Exportador> darExportadores() throws SQLException, Exception {
		ArrayList<Exportador> exportadores = new ArrayList<Exportador>();

		String sql = "SELECT * FROM EXPORTADOR";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int id = Integer.parseInt(rs.getString("ID"));
			String nombre = rs.getString("NOMBRE");
			String rut = (rs.getString("RUT"));
			String idP = (rs.getString("ID_PUERTO"));
			String idF = (rs.getString("ID_FACTURA"));
			exportadores.add(new Exportador(id, nombre, rut,idP,idF));
		}
		return exportadores;
	}

	public void addExportador(Exportador video) throws SQLException, Exception 
	{
		String sql = "INSERT INTO EXPORTADOR VALUES (";
		sql += video.getId() + ",'";
		sql += video.getNombre() + "','";
		sql += video.getId_puerto() + "','";
		sql += video.getId_factura() + "')";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}

	public factura darCostoFacturaExportadoresConPuertoAndes(String idExportador, String nombre) throws SQLException, Exception {
		factura factura = null;
		String sql = "SELECT F.ID ID_FACTURA, F.ID_BUQUE ID_BUQUE,"
				+ "F.ID_CARGA ID_CARGA,F.DIAS_EN_PUERTO DIAS_EN_PUERTO,"
				+ "F.COSTO COSTO,F.ID_OPERADOR ID_OPERADOR,"
				+ "F.ID_EXPORTADOR ID_EXPORTADOR "
				+ "FROM EXPORTADOR E JOIN FACTURA F ON "
				+ "E.ID = F.ID_EXPORTADOR ";
		sql += "WHERE ID_PUERTO = '1' AND E.ID = '" +idExportador + "' ";
		sql +="AND NOMBRE = '" + nombre + "';";
		System.out.println("SQL stmt:" + sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		while (rs.next()) 
		{
			String id = rs.getString("ID_FACTURA");
			String id_buque = rs.getString("ID_BUQUE");
			String id_carga = (rs.getString("ID_CARGA"));
			String dias = rs.getString("DIAS_EN_PUERTO");
			String costo = rs.getString("COSTO");
			String id_operador = (rs.getString("ID_OPERADOR"));
			String id_exportador = (rs.getString("ID_EXPORTADOR"));
			factura = new factura(id, id_buque, id_carga, dias, costo, id_operador, id_exportador);
		}
		return factura;
	}
}