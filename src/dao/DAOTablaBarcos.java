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
import vos.Carga;
import vos.Llegan;
import vos.Salen;
import vos.VistaArriboSalida;

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
	
	public void registrarBarco(Barco barco) throws SQLException, Exception
	{
		String sql = "INSERT INTO BARCO VALUES ('";
		sql += barco.getId() + "','";
		sql += barco.getNombre() + "','";
		sql += barco.getNombreAgente() + "','";
		sql += barco.getRegistroCapitania() + "','";
		sql += barco.getRegistroCapitania() + "','";
		sql += barco.getId_ruta() + "','";
		sql += barco.getEstado() + "','";
		sql += barco.getCapacidad() + "','";
		sql += barco.getTipo() + "')";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();	
	}
	
	public void registrarBuquesSalen(Salen salen) throws SQLException, Exception
	{
		String sql = "SELECT * FROM BARCO WHERE ESTADO = '3' OR ESTADO = '4' OR ESTADO = '5';";
		
		System.out.println("SQL stmt:" + sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		String ident = null;
		
		while(rs.next())
		{
			ident = (rs.getString("ID"));
		}
		
		if(ident==null || ident != salen.getIdBarco())
		{
			throw new Exception("El barco no puede salir");
		}
		
		sql = "INSERT INTO SALEN VALUES ('";
		sql += salen.getIdBarco() + "','";
		sql += salen.getFechaSalida() + "','";
		sql += salen.gethoraSalida() + "','";
		sql += salen.getIdMuelle() + "','";
		sql += salen.getDestino() + "')";

		System.out.println("SQL stmt:" + sql);

		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		sql = "UPDATE BARCO SET ESTADO = '3' WHERE ID = '";
		sql += salen.getIdBarco() + "';";

		System.out.println("SQL stmt:" + sql);

		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void registrarBuquesLlegan(Llegan llegan) throws SQLException, Exception
	{
		String sql = "SELECT * FROM BARCO WHERE ESTADO = '2';";
		
		System.out.println("SQL stmt:" + sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		String ident = null;
		
		while(rs.next())
		{
			ident = (rs.getString("ID"));
		}
		
		if(ident==null || ident != llegan.getIdBarco())
		{
			throw new Exception("El estado del barco no es correcto");
		}
		
		sql = "INSERT INTO LLEGAN VALUES ('";
		sql += llegan.getIdBarco() + "','";
		sql += llegan.getFechaLlegada() + "','";
		sql += llegan.getHora() + "','";
		sql += llegan.getIdMuelle() + "','";
		sql += llegan.getOrigen() + "')";

		System.out.println("SQL stmt:" + sql);

		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
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
			String	ruta = rs.getString("ID_RUTA");
			String estado = rs.getString("ESTADO");
			String capacidad = rs.getString("CAPACIDAD");
			String tipo = rs.getString("TIPO");
			barco= new Barco(ident,nombre,agente,capitania,ruta, estado, capacidad, tipo);
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
	
	public ArrayList<Barco> darBarcosConDestino (String iddestino) throws SQLException
	{
		ArrayList<Barco> barcos = new ArrayList<Barco>();

		String sql = "SELECT * FROM BARCO ";
		sql += "WHERE (SELECT DESTINO FROM SALEN) = '" + iddestino + "'";
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
			String	ruta = rs.getString("ID_RUTA");
			String estado = rs.getString("ESTADO");
			String capacidad = rs.getString("CAPACIDAD");
			String tipo = rs.getString("TIPO");
			barcos.add(new Barco(ident,nombre,agente,capitania,ruta, estado, capacidad, tipo));
		}
		
		return barcos;
	}
	
	public ArrayList<VistaArriboSalida> consultarArriboSalida1(Date fecha1, Date fecha2, String nombreBarco, String tipoBarco, String tipoCarga) throws SQLException
	{
		ArrayList<VistaArriboSalida> asb = new ArrayList<VistaArriboSalida>();
		String sql = "SELECT DISTINCT NOMBRE BARCO,FECHA_LLEGADA FECHA,HORA_LLEGADA HORA,ESTADO,ID_RUTA, " +
				"TIPO TIPO_BARCO,ID_MUELLE,ORIGEN PUERTO, C.TIPO_CARGA FROM CARGA C JOIN " +
				"(SELECT DISTINCT ID,NOMBRE,FECHA_LLEGADA,HORA_LLEGADA,ESTADO,ID_RUTA,TIPO,ID_MUELLE,ORIGEN " +
				"FROM BARCO JOIN LLEGAN ON BARCO.ID = LLEGAN.ID_BARCO " +
				"UNION SELECT DISTINCT ID, NOMBRE,FECHA_SALIDA,HORA_SALIDA,ESTADO,ID_RUTA,TIPO,ID_MUELLE,DESTINO " + 
				"FROM BARCO JOIN SALEN ON BARCO.ID = SALEN.ID_BARCO) L ON L.ID = C.ID_BARCO ";
		sql += "WHERE FECHA_LLEGADA  BETWEEN '"+ fecha1 + "' AND '"+ fecha2 + "' " + "AND NOMBRE = '" 
				+ nombreBarco + "' AND TIPO = '" + tipoBarco + "' AND C.TIPO_CARGA = '" + tipoCarga + "';";
		System.out.println("SQL stmt:" + sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while (rs.next()) 
		{	
			String	barco = (rs.getString("BARCO"));
			Date fecha = (rs.getDate("FECHA"));
			String	hora = rs.getString("HORA");
			String	estado = (rs.getString("ESTADO"));
			String	ruta = rs.getString("ID_RUTA");
			String tipoDeBarco = rs.getString("TIPO_BARCO");
			String idMuelle = rs.getString("ID_MUELLE");
			String puerto = rs.getString("PUERTO");
			String carga = rs.getString("TIPO_CARGA");
			asb.add(new VistaArriboSalida(barco, fecha, hora, estado, ruta, tipoDeBarco, idMuelle, puerto, carga));
		}
		return asb;
	}
	
	public ArrayList<VistaArriboSalida> consultarArriboSalida2(Date fecha1, Date fecha2, String nombreBarco, String tipoBarco, String tipoCarga) throws SQLException
	{	
		ArrayList<VistaArriboSalida> asb = new ArrayList<VistaArriboSalida>();
		String sql = "SELECT DISTINCT NOMBRE BARCO,FECHA_LLEGADA FECHA,HORA_LLEGADA HORA,ESTADO,ID_RUTA, " +
				"TIPO TIPO_BARCO,ID_MUELLE,ORIGEN PUERTO, C.TIPO_CARGA FROM CARGA C JOIN " +
				"(SELECT DISTINCT ID,NOMBRE,FECHA_LLEGADA,HORA_LLEGADA,ESTADO,ID_RUTA,TIPO,ID_MUELLE,ORIGEN " +
				"FROM BARCO JOIN LLEGAN ON BARCO.ID = LLEGAN.ID_BARCO " +
				"UNION SELECT DISTINCT ID, NOMBRE,FECHA_SALIDA,HORA_SALIDA,ESTADO,ID_RUTA,TIPO,ID_MUELLE,DESTINO " + 
				"FROM BARCO JOIN SALEN ON BARCO.ID = SALEN.ID_BARCO) L ON L.ID = C.ID_BARCO ";
		sql += "WHERE FECHA_LLEGADA  BETWEEN '"+ fecha1 + "' AND '"+ fecha2 + "' " + "AND NOMBRE NOT LIKE '" 
				+ nombreBarco + "' AND TIPO NOT LIKE '" + tipoBarco + "' AND C.TIPO_CARGA NOT LIKE '" + tipoCarga + "';";
		System.out.println("SQL stmt:" + sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while (rs.next()) 
		{	
			String	barco = (rs.getString("BARCO"));
			Date fecha = (rs.getDate("FECHA"));
			String	hora = rs.getString("HORA");
			String	estado = (rs.getString("ESTADO"));
			String	ruta = rs.getString("ID_RUTA");
			String tipoDeBarco = rs.getString("TIPO_BARCO");
			String idMuelle = rs.getString("ID_MUELLE");
			String puerto = rs.getString("PUERTO");
			String carga = rs.getString("TIPO_CARGA");
			asb.add(new VistaArriboSalida(barco, fecha, hora, estado, ruta, tipoDeBarco, idMuelle, puerto, carga));
		}
		return asb;
	}
}