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
import java.util.Date;

import vos.Importador;

public class DAOTablaImportadores {

	private ArrayList<Object> recursos;

	private Connection conn;

	public DAOTablaImportadores() 
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

	public ArrayList<Importador> darImportadores() throws SQLException, Exception 
	{
		ArrayList<Importador> importadores = new ArrayList<Importador>();

		String sql = "SELECT * FROM IMPORTADOR";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("ID"));
			String nombre = rs.getString("NOMBRE");
			String tipo = (rs.getString("ID_TIPO"));
			String registro = rs.getString("REGISTRO_DE_ADUANA");
			String tipo2 = (rs.getString("TIPO_HABITUAL"));
			String tipo3 = (rs.getString("ID_PUERTO"));
			importadores.add(new Importador(id, nombre, tipo, registro, tipo2, tipo3));
		}
		return importadores;
	}

	//ARREGLAR
	public void addImportador(Importador video) throws SQLException, Exception {

		String sql = "INSERT INTO IMPORTADOR VALUES (";
		sql += video.getId() + ",'";
		sql += video.getNombre() + "','";
		sql += video.getTipo() + "','";
		sql += video.getRegistro() + "','";
		sql += video.getTipo2() + "')";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	public void asignarEntregaAImportador(String idImportador) throws Exception 
	{
		Date actual = new Date();
		String sql = "UPDATE ENTREGA ";
		sql += "SET FECHA = '" + actual.toString() +"', ID_IMPORTADOR = '" +idImportador+"';";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

}
