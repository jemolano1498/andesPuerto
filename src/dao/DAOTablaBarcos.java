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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Barco;
import vos.Bodega;
import vos.Cobertizo;
import vos.Importador;
import vos.Patio;
import vos.Silo;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 * @author Juan
 */
public class DAOTablaBarcos {


	private ArrayList<Object> recursos;

	private Connection conn;

	public DAOTablaBarcos() {
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

	
	public Barco registrarBuquesLLegan(String name) throws SQLException, Exception {
		
		Barco barco = null;
		String id =null;
		String name2 = null;
		String fecha = null;
		String destino = null;
		
		String sql = "SELECT * FROM LLEGAN WHERE ID_BARCO ='" + name + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			id = (rs.getString("ID_BARCO"));
			fecha = (rs.getString("FECHA_LLEGADA"));
			destino = rs.getString("DESTINO_FINAL");
		}
		
		if(id==null)
		{
			throw new Exception("El barco no ha llegado");
		}
		
		sql = "INSERT INTO SALEN VALUES ('";
		sql += id + "','";
		sql += fecha + "','";
		sql += destino + "')";

		System.out.println("SQL stmt:" + sql);

		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		sql = "SELECT * FROM BARCO WHERE ID ='"+ name + "'";
		System.out.println("SQL stmt:" + sql);

		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		rs = prepStmt.executeQuery();
		rs.next();
		String	ident = (rs.getString("ID"));
		String	nombre = (rs.getString("NOMBRE"));
		String	agente = rs.getString("NOMBRE_AGENTE");
		String	capitania = (rs.getString("REGISTRO_CAPITANIA"));
		String	puerto = (rs.getString("ID_PUERTO"));
		String	ruta = rs.getString("ID_RUTA");
		String	muelle = rs.getString("ID_MUELLE");
		barco= new Barco(ident,nombre,agente,capitania,puerto,ruta,muelle);
		return barco;
		
	}

	

	



}
