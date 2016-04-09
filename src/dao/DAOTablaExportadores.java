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
			exportadores.add(new Exportador(id, nombre, rut));
		}
		return exportadores;
	}

	public void addExportador(Exportador video) throws SQLException, Exception 
	{
		String sql = "INSERT INTO EXPORTADOR VALUES (";
		sql += video.getId() + ",'";
		sql += video.getNombre() + "','";
		sql += video.getRut() + "')";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	



}
