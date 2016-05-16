
package tm;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Properties;

import dao.DAOCarga;
import dao.DAOTablaAreas;
import dao.DAOTablaBarcos;
import dao.DAOTablaExportadores;
import dao.DAOTablaImportadores;
import vos.ListaAreas;
import vos.ListaArribosSalidas;
import vos.Area;
import vos.Barco;
import vos.Bodega;
import vos.Carga;
import vos.Cobertizo;
import vos.Exportador;
import vos.Importador;
import vos.ListaCargas;
import vos.ListaExportadores;
import vos.ListaImportadores;
import vos.Llegan;
import vos.Patio;
import vos.Salen;
import vos.Silo;
import vos.VistaArriboSalida;

/**
 * Fachada en patron singleton de la aplicaci√≥n
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
	
	public void addBarco(Barco barco)throws Exception
	{
		DAOTablaBarcos daoBarcos = new DAOTablaBarcos();
		try 
		{
			this.conn = darConexion();
			daoBarcos.setConn(conn);
			daoBarcos.registrarBarco(barco);
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
	}
	
	public void addLlegada(Llegan llegan)throws Exception
	{
		DAOTablaBarcos daoBarcos = new DAOTablaBarcos();
		try 
		{
			this.conn = darConexion();
			daoBarcos.setConn(conn);
			daoBarcos.registrarBuquesLlegan(llegan);
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
	}	
	
	public void addSalida(Salen salen)throws Exception
	{
		DAOTablaBarcos daoBarcos = new DAOTablaBarcos();
		try 
		{
			this.conn = darConexion();
			daoBarcos.setConn(conn);
			daoBarcos.registrarBuquesSalen(salen);
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

	public Carga cargarBuque(String idCarga, String idBuque) throws Exception 
	{
		DAOTablaBarcos daoBarcos = new DAOTablaBarcos();
		DAOCarga daoCarga = new DAOCarga();
		DAOTablaAreas daoArea = new DAOTablaAreas();
		Carga aRetornar=null;
		try 
		{
			this.conn = darConexion();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(8);
			daoBarcos.setConn(conn);
			daoCarga.setConn(conn);
			daoArea.setConn(conn);
			Barco aCargar = daoBarcos.buscarBarco(idBuque);
			Carga carguita = daoCarga.buscarCarga(idCarga);
			Area aLlenar = daoArea.darArea(carguita.getId_area());

			int CapacidadBarco =Integer.parseInt(aCargar.getCapacidad());
			int tamanioCarga = Integer.parseInt(carguita.getTamano());
			int CapacidadArea =Integer.parseInt(aLlenar.getCapacidad());
			if (tamanioCarga <= CapacidadBarco)
			{
				CapacidadBarco -= tamanioCarga;
				CapacidadArea += tamanioCarga;
				daoBarcos.asignarCargaABarco(aCargar.getId(), CapacidadBarco+"");
				daoCarga.asignarCargaABarco(carguita.getId(), aCargar.getId());
				daoArea.asignarCargaArea(carguita.getId_area(), CapacidadArea+"");
				conn.commit();

			}
			else
			{
				conn.rollback();
				throw new Exception ("la carga no cabe");
			}
			aRetornar=daoCarga.buscarCarga(idCarga);




		} 
		catch (SQLException e) 
		{
			conn.rollback();
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) 
		{
			conn.rollback();
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		finally 
		{
			try 
			{
				daoBarcos.cerrarRecursos();
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
	public Carga descargarBuque(String idCarga, String idArea) throws Exception 
	{
		DAOTablaBarcos daoBarcos = new DAOTablaBarcos();
		DAOCarga daoCarga = new DAOCarga();
		DAOTablaAreas daoArea = new DAOTablaAreas();
		Carga aRetornar=null;

		try 
		{
			this.conn = darConexion();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(4);
			daoBarcos.setConn(conn);
			daoCarga.setConn(conn);
			daoArea.setConn(conn);
			Carga carguita = daoCarga.buscarCarga(idCarga);
			Area aLlenar = daoArea.darArea(idArea);

			if (carguita == null)
			{
				throw new Exception("Carga no esta registrada");
			}

			Barco aDescargar = daoBarcos.buscarBarco(carguita.getId_barco());
			if(carguita.getId_area()!=null)
			{
				throw new Exception("La carga ya est· almacenada en una ·rea" );
			}
			if (aLlenar == null)
			{
				throw new Exception("Area no existe");
			}

			int CapacidadArea =Integer.parseInt(aLlenar.getCapacidad());
			int tamanioCarga = Integer.parseInt(carguita.getTamano());
			int CapacidadBarco =Integer.parseInt(aDescargar.getCapacidad());
			if (tamanioCarga <= CapacidadArea)
			{
				CapacidadArea -= tamanioCarga;
				CapacidadBarco += tamanioCarga;
				daoArea.reservarArea(idArea);
				daoBarcos.descargarABarco(aLlenar.getId(), CapacidadBarco+"");
				daoCarga.asignarCargaAArea(carguita.getId(), aLlenar.getId());
				daoArea.asignarCargaArea(idArea, CapacidadArea+"");
				if (!daoBarcos.noTieneDestinoPuertoAndes(carguita.getId_barco()))
				{
					conn.rollback();
					throw new Exception ("aun hay una carga con destino puertoAndes");
				}

				daoBarcos.dejarDisponible (carguita.getId_barco());
				conn.commit();

			}
			else
			{
				throw new Exception ("la carga no cabe");
			}
			aRetornar = daoCarga.buscarCarga(idCarga);




		} 
		catch (SQLException e) 
		{
			conn.rollback();
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) 
		{
			conn.rollback();
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		finally 
		{
			try 
			{
				daoBarcos.cerrarRecursos();
				daoCarga.cerrarRecursos();
				daoArea.cerrarRecursos();
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

	public ListaCargas deshabilitarBuque (String idBuque, String estado ) throws Exception 
	{
		ListaCargas resp = null;
		ArrayList <Carga> w = new ArrayList<Carga>();
		ArrayList <Carga> w1 = new ArrayList<Carga>();
		DAOTablaBarcos daoBarcos = new DAOTablaBarcos();
		DAOCarga daoCarga = new DAOCarga();
		DAOTablaAreas daoArea = new DAOTablaAreas();
		//		Savepoint save1 = conn.setSavepoint();
		try 
		{
			this.conn = darConexion();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(8);
			daoBarcos.setConn(conn);
			daoCarga.setConn(conn);
			daoArea.setConn(conn);
			if (estado.equals("7")||estado.equals("8"))
			{

				w = daoBarcos.darCargasBarco(idBuque);
				if (w.isEmpty())
				{
					conn.rollback();
					throw new Exception("El barco no tiene cargas");
				}
				Savepoint save1 = conn.setSavepoint();
				for (Carga insideShip : w)
				{
					String destino = insideShip.getDestino();
					ArrayList<Barco> barcosDestino = daoBarcos.darBarcosConDestino(destino);
					boolean pudo =false;
					for (Barco ship : barcosDestino)
					{
						if (Integer.parseInt(ship.getCapacidad())>Integer.parseInt(insideShip.getTamano())&&pudo==false)
						{
							descargarBuque(insideShip.getId(), "1");
							cargarBuque(insideShip.getId(), ship.getId());
							//							conn.commit();
							daoBarcos.deshabilitarBarco(idBuque, estado);
							System.out.println(pudo);
							pudo =true;
						}
					}
					save1 = conn.setSavepoint();
					if (pudo == false)
					{
						ArrayList<Area> areas = daoArea.darAreasPorTipo(insideShip.getTipo_carga());
						for (Area a : areas)
						{
							if (Integer.parseInt(a.getCapacidad())>Integer.parseInt(insideShip.getTamano())&&pudo==false)
							{
								descargarBuque(insideShip.getId(), a.getId());
								pudo =true;
							}
						}
					}
					save1 = conn.setSavepoint();
					if (pudo == false)
					{
						w1.add(insideShip);
						throw new Exception ("la Carga no pudo ser realmacenada");
					}
				}

			}
			else
			{
				daoBarcos.deshabilitarBarco(idBuque, estado);
				w1 = daoBarcos.darCargasBarco(idBuque);
			}





		} 
		catch (SQLException e) 
		{
			conn.rollback();
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) 
		{
			//			conn.rollback(save1);
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		finally 
		{
			try 
			{
				daoBarcos.cerrarRecursos();
				daoCarga.cerrarRecursos();
				daoArea.cerrarRecursos();
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
		resp = new ListaCargas(w1);
		return resp;
	}

	public ListaCargas deshabilitarArea (String idArea) throws Exception 
	{
		ListaCargas resp = null;
		ArrayList <Carga> w = new ArrayList<Carga>();
		ArrayList <Carga> w1 = new ArrayList<Carga>();
		DAOTablaBarcos daoBarcos = new DAOTablaBarcos();
		DAOCarga daoCarga = new DAOCarga();
		DAOTablaAreas daoArea = new DAOTablaAreas();
		//		Savepoint save1 = conn.setSavepoint();
		try 
		{
			this.conn = darConexion();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(8);
			daoBarcos.setConn(conn);
			daoCarga.setConn(conn);
			daoArea.setConn(conn);

			boolean pudo=false;
			Area x = daoArea.darArea(idArea);
			w = daoArea.darCargasArea(idArea);
			if (w.isEmpty())
			{
				conn.rollback();
				throw new Exception("El area no tiene cargas");
			}
			Savepoint save1 = conn.setSavepoint();
			for (Carga insideShip : w)
			{
				ArrayList<Area> areas = daoArea.darAreasPorTipo(insideShip.getTipo_carga());
				for (Area a : areas)
				{
					if (Integer.parseInt(a.getCapacidad())>Integer.parseInt(insideShip.getTamano())&&pudo==false)
					{
						String nuevaCap1 = (Integer.parseInt(x.getCapacidad())-Integer.parseInt(insideShip.getTamano()))+"";
						String nuevaCap2 = (Integer.parseInt(a.getCapacidad())+Integer.parseInt(insideShip.getTamano()))+"";
						daoArea.cambiarAreaAlmacenamiento(idArea, nuevaCap1, nuevaCap2, insideShip.getId_area());
						pudo =true;
						conn.commit();
					}
				}

				save1 = conn.setSavepoint();
				if (pudo == false)
				{

					String destino = insideShip.getDestino();
					ArrayList<Barco> barcosDestino = daoBarcos.darBarcosConDestino(destino);
					for (Barco ship : barcosDestino)
					{
						if (Integer.parseInt(ship.getCapacidad())>Integer.parseInt(insideShip.getTamano())&&pudo==false)
						{
							cargarBuque(insideShip.getId(), ship.getId());
							pudo =true;
						}
					}

				}
				save1 = conn.setSavepoint();
				if (pudo == false)
				{
					w1.add(insideShip);
					throw new Exception ("la Carga no pudo ser realmacenada");
				}
			}

		} 
		catch (SQLException e) 
		{
			conn.rollback();
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) 
		{
			//			conn.rollback(save1);
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		finally 
		{
			try 
			{
				daoBarcos.cerrarRecursos();
				daoCarga.cerrarRecursos();
				daoArea.cerrarRecursos();
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
		resp = new ListaCargas(w1);
		return resp;
	}

	public ListaCargas darMovimientosCarga (String origen,String idImportador,String idCarga,String destino,String tipo, String fecha, int x) throws SQLException
	{
		ArrayList<Carga> cargas=null;
		DAOCarga daoCarga = new DAOCarga();
		try 
		{
			this.conn = darConexion();
			daoCarga.setConn(conn);
			if (x==0)
			{
				cargas = daoCarga.consultarCargasImportador( origen, idImportador, idCarga, destino, tipo, fecha);
			}
			else
			{
				cargas = daoCarga.consultarCargas( origen, idCarga, destino, tipo, fecha);
			}
			

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
		return new ListaCargas(cargas);
	}

	public ListaAreas darInfoArea(String idImportador, String estado, String idArea, String tipo, int x) throws SQLException {
		ArrayList<Area> areas=null;
		DAOTablaAreas daoAreas = new DAOTablaAreas();
		try 
		{
			this.conn = darConexion();
			daoAreas.setConn(conn);
			if (x==0)
			{
				areas = daoAreas.consultarAreas( idImportador, estado, idArea, tipo);
			}
			else
			{
				areas = daoAreas.consultarAreas( idImportador, estado, idArea, tipo);
			}
			

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
	
	public ListaArribosSalidas consultarLLegadasSalidas1(Date fecha1, Date fecha2, String nombreBarco, String tipoBarco, String tipoCarga) throws Exception
	{
		ArrayList<VistaArriboSalida> asb = null;
		
		DAOTablaBarcos daoBarcos = new DAOTablaBarcos();
		try 
		{
			this.conn = darConexion();
			daoBarcos.setConn(conn);
			asb = daoBarcos.consultarArriboSalida1(fecha1, fecha2, nombreBarco, tipoBarco, tipoCarga);

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
		return new ListaArribosSalidas(asb);
	}
	
	public ListaArribosSalidas consultarLLegadasSalidas2(Date fecha1, Date fecha2, String nombreBarco, String tipoBarco, String tipoCarga) throws Exception
	{
		ArrayList<VistaArriboSalida> asb = null;
		
		DAOTablaBarcos daoBarcos = new DAOTablaBarcos();
		try 
		{
			this.conn = darConexion();
			daoBarcos.setConn(conn);
			asb = daoBarcos.consultarArriboSalida2(fecha1, fecha2, nombreBarco, tipoBarco, tipoCarga);

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
		return new ListaArribosSalidas(asb);
	}
	
	public ListaAreas darInfoCargasArea(String idArea1, String idArea2) throws SQLException {
		ArrayList<Area> areas=null;
		DAOTablaAreas daoAreas = new DAOTablaAreas();
		try 
		{
			this.conn = darConexion();
			daoAreas.setConn(conn);
			
				areas = daoAreas.consultarCargasAreas( idArea1, idArea2);
			
			

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
}
