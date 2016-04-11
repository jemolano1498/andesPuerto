
package tm;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import dao.DAOCarga;
import dao.DAOTablaAreas;
import dao.DAOTablaBarcos;
import dao.DAOTablaExportadores;
import dao.DAOTablaImportadores;
import vos.ListaAreas;
import vos.Area;
import vos.Barco;
import vos.Bodega;
import vos.Carga;
import vos.Cobertizo;
import vos.Exportador;
import vos.Importador;
import vos.ListaBuques;
import vos.ListaExportadores;
import vos.ListaImportadores;
import vos.Patio;
import vos.Silo;

/**
 * Fachada en patron singleton de la aplicación
 * @author Juan
 */
public class PuertoAndesMaster {

	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	private  String connectionDataPath;

	private String user;

	private String password;

	private String url;

	private String driver;

	private Connection conn;

	public PuertoAndesMaster(String contextPathP) 
	{
		connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
		initConnectionData();
	}

	private void initConnectionData()
	{
		try 
		{
			File arch = new File(this.connectionDataPath);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(arch);
			prop.load(in);
			in.close();
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("usuario");
			this.password = prop.getProperty("clave");
			this.driver = prop.getProperty("driver");
			Class.forName(driver);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private Connection darConexion() throws SQLException 
	{
		System.out.println("Connecting to: " + url + " With user: " + user);
		return DriverManager.getConnection(url, user, password);
	}

	////////////////////////////////////////
	///////Transacciones////////////////////
	////////////////////////////////////////

	public ListaImportadores darImportadores() throws Exception 
	{
		ArrayList<Importador> importadores;
		DAOTablaImportadores daoImportadores = new DAOTablaImportadores();
		try 
		{
			this.conn = darConexion();
			daoImportadores.setConn(conn);
			importadores = daoImportadores.darImportadores();

		} 
		catch (SQLException e) 
		{
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) 
		{
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		finally 
		{
			try 
			{
				daoImportadores.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} 
			catch (SQLException exception)
			{
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaImportadores(importadores);
	}


	public Barco AsignarSalida(String name) throws Exception 
	{
		DAOTablaBarcos daoBarcos = new DAOTablaBarcos();
		Barco barco;
		try 
		{
			this.conn = darConexion();
			daoBarcos.setConn(conn);
			barco=daoBarcos.registrarBuquesLLegan(name);

		} 
		catch (SQLException e) 
		{
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) 
		{
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally 
		{
			try 
			{
				daoBarcos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} 
			catch (SQLException exception) 
			{
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return barco;
	}
	
	public void addImportador(Importador importador) throws Exception {
		DAOTablaImportadores daoImportadores = new DAOTablaImportadores();
		try 
		{
			this.conn = darConexion();
			daoImportadores.setConn(conn);
			daoImportadores.addImportador(importador);
			conn.commit();

		} 
		catch (SQLException e) 
		{
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) 
		{
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		finally 
		{
			try 
			{
				daoImportadores.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} 
			catch (SQLException exception) 
			{
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void addExportador(Exportador exportador) throws Exception {
		DAOTablaExportadores daoExportadores = new DAOTablaExportadores();
		try 
		{
			this.conn = darConexion();
			daoExportadores.setConn(conn);
			daoExportadores.addExportador(exportador);
			conn.commit();

		} 
		catch (SQLException e) 
		{
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) 
		{
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally 
		{
			try 
			{
				daoExportadores.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} 
			catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}

	public ListaExportadores darExportadores() throws Exception 
	{
		ArrayList<Exportador> exportadores;
		DAOTablaExportadores daoExportadores = new DAOTablaExportadores();
		try 
		{
			this.conn = darConexion();
			daoExportadores.setConn(conn);
			exportadores = daoExportadores.darExportadores();

		} 
		catch (SQLException e) 
		{
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) 
		{
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		finally 
		{
			try 
			{
				daoExportadores.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} 
			catch (SQLException exception) 
			{
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaExportadores(exportadores);
	}

	public void addCobertizo(Cobertizo cobertizo)throws Exception 
	{
		DAOTablaAreas daoAreas = new DAOTablaAreas();
		try 
		{
			this.conn = darConexion();
			daoAreas.setConn(conn);
			daoAreas.addCobertizo(cobertizo);
			conn.commit();

		} 
		catch (SQLException e) 
		{
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) 
		{
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		finally 
		{
			try 
			{
				daoAreas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} 
			catch (SQLException exception) 
			{
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}

	public void addBodega(Bodega bodega)throws Exception 
	{
		DAOTablaAreas daoAreas = new DAOTablaAreas();
		try 
		{
			this.conn = darConexion();
			daoAreas.setConn(conn);
			daoAreas.addBodega(bodega);
			conn.commit();

		} 
		catch (SQLException e) 
		{
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) 
		{
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		finally 
		{
			try 
			{
				daoAreas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} 
			catch (SQLException exception) 
			{
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}

	public void addPatio(Patio patio)throws Exception 
	{
		DAOTablaAreas daoAreas = new DAOTablaAreas();
		try 
		{
			this.conn = darConexion();
			daoAreas.setConn(conn);
			daoAreas.addPatio(patio);
			conn.commit();

		} 
		catch (SQLException e) 
		{
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) 
		{
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally 
		{
			try 
			{
				daoAreas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} 
			catch (SQLException exception) 
			{
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}

	public void addSilo(Silo silo)throws Exception 
	{
		DAOTablaAreas daoAreas = new DAOTablaAreas();
		try 
		{
			this.conn = darConexion();
			daoAreas.setConn(conn);
			daoAreas.addSilo(silo);
			conn.commit();

		} 
		catch (SQLException e) 
		{
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) 
		{
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		finally 
		{
			try 
			{
				daoAreas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} 
			catch (SQLException exception) 
			{
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}

	public void addCarga(Carga carga) throws Exception 
	{
		DAOCarga daoCarga = new DAOCarga();
		try 
		{
			this.conn = darConexion();
			daoCarga.setConn(conn);
			daoCarga.RegistrarCarga(carga);
			conn.commit();

		}
		catch (SQLException e) 
		{
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) 
		{
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		finally
		{
			try 
			{
				daoCarga.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} 
			catch (SQLException exception)
			{
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}

	public ListaAreas darAreas() throws Exception 
	{
		ArrayList<Area> areas;
		DAOTablaAreas daoAreas = new DAOTablaAreas();
		try 
		{
			this.conn = darConexion();
			daoAreas.setConn(conn);
			areas = daoAreas.darAreas();

		} 
		catch (SQLException e) 
		{
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) 
		{
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		finally 
		{
			try 
			{
				daoAreas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} 
			catch (SQLException exception) 
			{
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaAreas(areas);
	}
	
	public void asignarEntregaAImportador(String idImportador) throws Exception 
	{
		DAOTablaExportadores daoExportadores = new DAOTablaExportadores();
		try 
		{
			this.conn = darConexion();
			daoExportadores.setConn(conn);
			asignarEntregaAImportador(idImportador);

		} 
		catch (SQLException e) 
		{
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) 
		{
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		finally 
		{
			try 
			{
				daoExportadores.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} 
			catch (SQLException exception) 
			{
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public Carga reqFunc10(String idCarga, String idBuque) throws Exception 
	{
		DAOTablaBarcos daoBarcos = new DAOTablaBarcos();
		DAOCarga daoCarga = new DAOCarga();
		Carga aRetornar=null;
		try 
		{
			this.conn = darConexion();
			daoBarcos.setConn(conn);
			daoCarga.setConn(conn);
//			daoBarcos.setSerializable();
//			daoCarga.setSerializable();
			Barco aCargar = daoBarcos.buscarBarco(idBuque);
			Carga carguita = daoCarga.buscarCarga(idCarga);
			aRetornar=carguita;
			int CapacidadBarco =Integer.parseInt(aCargar.getCapacidad());
			int tamanioCarga = Integer.parseInt(carguita.getTamano());
			if (tamanioCarga <= CapacidadBarco)
			{
				CapacidadBarco -= tamanioCarga;
				daoBarcos.asignarCargaABarco(aCargar.getId(), CapacidadBarco+"");
				daoCarga.asignarCargaABarco(carguita.getId(), aCargar.getId());
//				asignarEntregaAImportador(carguita.getId_entrega());
				
			}
			else
			{
//				daoBarcos.rollBackTransaction();
//				daoCarga.rollBackTransaction();
				throw new Exception ("la carga no cabe");
			}
			
//			daoBarcos.commitTransaction();
//			daoCarga.commitTransaction();
			
			

		} 
		catch (SQLException e) 
		{
//			daoBarcos.rollBackTransaction();
//			daoCarga.rollBackTransaction();
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) 
		{
//			daoBarcos.rollBackTransaction();
//			daoCarga.rollBackTransaction();
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		finally 
		{
			try 
			{
				daoBarcos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
				daoCarga.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} 
			catch (SQLException exception) 
			{
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return aRetornar;
	}
	
}
