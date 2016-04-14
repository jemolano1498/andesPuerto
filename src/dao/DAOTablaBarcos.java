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
import vos.Carga;
import vos.Cobertizo;
import vos.Exportador;
import vos.Importador;
import vos.ListaCargas;
import vos.Patio;
import vos.Silo;

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
	
	public Barco registrarBuquesLLegan(String name) throws SQLException, Exception 
	{
		
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
		String estado = rs.getString("ESTADO");
		String capacidad = rs.getString("CAPACIDAD");
		barco= new Barco(ident,nombre,agente,capitania,puerto,ruta,muelle, estado, capacidad);
		return barco;
		
	}

	public Barco buscarBarco (String id) throws SQLException, Exception 
	{
		Barco barco =null;
		String sql = "SELECT * FROM BARCO WHERE ID ='" + id + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(id==null)
		{
			throw new Exception("El barco no esta registrado");
		}

		while (rs.next())
		{
			String	ident = (rs.getString("ID"));
			String	nombre = (rs.getString("NOMBRE"));
			String	agente = rs.getString("NOMBRE_AGENTE");
			String	capitania = (rs.getString("REGISTRO_CAPITANIA"));
			String	puerto = (rs.getString("ID_PUERTO"));
			String	ruta = rs.getString("ID_RUTA");
			String	muelle = rs.getString("ID_MUELLE");
			String estado = rs.getString("ESTADO");
			String capacidad = rs.getString("CAPACIDAD");
			barco= new Barco(ident,nombre,agente,capitania,puerto,ruta,muelle, estado, capacidad);
		}
		
		return barco;
		
	}
	public void asignarCargaABarco (String idBarco, String carga) throws SQLException
	{
		String sql = "UPDATE BARCO ";
		sql += "SET CAPACIDAD ='"+ carga +"', ESTADO = '1' ";
		sql += "WHERE ID ='"+ idBarco +"'";
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void descargarABarco (String idBarco, String carga) throws SQLException
	{
		String sql = "UPDATE BARCO ";
		sql += "SET CAPACIDAD ='"+ carga +"', ESTADO = '6' ";
		sql += "WHERE ID ='"+ idBarco +"'";
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void dejarDisponible (String idBarco) throws SQLException
	{
		String sql = "UPDATE BARCO ";
		sql += "SET ESTADO = '4' ";
		sql += "WHERE ID ='"+ idBarco +"'";
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void deshabilitarBarco (String idBarco, String estado) throws SQLException
	{
		String sql = "UPDATE BARCO ";
		sql += "SET ESTADO = '"+ estado +"' ";
		sql += "WHERE ID ='"+ idBarco +"'";
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public boolean noTieneDestinoPuertoAndes (String idBarco) throws SQLException
	{
		boolean a = true;
		String sql = "SELECT COUNT(*) AS CUENTA FROM ";
		sql +="CARGA JOIN BARCO ";
		sql +="ON CARGA.ID_BARCO=BARCO.ID ";
		sql +="WHERE DESTINO = '1' AND ID_BARCO='" +idBarco + "'";
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs =prepStmt.executeQuery();
		while (rs.next())
		{
			int	ident = Integer.parseInt((rs.getString("CUENTA")));
			if (ident>0)
			{
				a=false;
			}
		}
		return a;
	}
	
	public ArrayList<Carga> darCargasBarco (String idBuque) throws SQLException
	{
		ArrayList<Carga> cargas = new ArrayList<Carga>();

		String sql = "SELECT CARGA.ID, CARGA.TIPO_CARGA, CARGA.ID_BARCO, CARGA.ID_ENTREGA, ";
		sql += "CARGA.ID_EQUIPO, CARGA.ID_VEHICULO, CARGA.ID_AREA, CARGA.TAMANO, CARGA.DESTINO FROM ";
		sql += "CARGA JOIN BARCO ";
		sql += "ON CARGA.ID_BARCO = BARCO.ID ";
		sql += "WHERE BARCO.ID = '"+ idBuque +"'";
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
	
	public ArrayList<Barco> darBarcosConDestino (String destino) throws SQLException
	{
		ArrayList<Barco> barcos = new ArrayList<Barco>();

		String sql = "SELECT * FROM BARCO ";
		sql += "WHERE ID_PUERTO=" + destino;
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String	ident = (rs.getString("ID"));
			String	nombre = (rs.getString("NOMBRE"));
			String	agente = rs.getString("NOMBRE_AGENTE");
			String	capitania = (rs.getString("REGISTRO_CAPITANIA"));
			String	puerto = (rs.getString("ID_PUERTO"));
			String	ruta = rs.getString("ID_RUTA");
			String	muelle = rs.getString("ID_MUELLE");
			String estado = rs.getString("ESTADO");
			String capacidad = rs.getString("CAPACIDAD");
			barcos.add(new Barco(ident,nombre,agente,capitania,puerto,ruta,muelle, estado, capacidad));
		}
		
		return barcos;
	}

}
