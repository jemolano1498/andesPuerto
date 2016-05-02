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
package rest;


import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import tm.PuertoAndesMaster;
import vos.Barco;
import vos.Bodega;
import vos.Carga;
import vos.Cobertizo;
import vos.Exportador;
import vos.ListaCargas;
import vos.ListaExportadores;
import vos.Patio;
import vos.Silo;


@Path("cargas")
public class PuertoAndesCargasServices {

	@Context
	private ServletContext context;


	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	
	@PUT
	@Path("/registrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCarga(Carga carga) {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		try {
			tm.addCarga(carga);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(carga).build();
	}
	
	@GET
	@Path("/cargarBarco")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response cargarBarco(@DefaultValue("") @QueryParam("barco") String idBarco,
			@DefaultValue("") @QueryParam("carga") String idCarga) {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		Carga a = null;
		try {
			a=tm.cargarBuque(idCarga, idBarco);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(a).build();
	}
	
	
	@GET
	@Path("/descargarBarco")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response descargarBarco(@DefaultValue("") @QueryParam("area") String idArea,
			@DefaultValue("") @QueryParam("carga") String idCarga) {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		System.out.println(idArea +" y " +idCarga);
		Carga a = null;
		try {
			a=tm.descargarBuque(idCarga, idArea);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(a).build();
	}
	
	@GET
	@Path("/consultarCarga")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response consultarMovimientosCarga(@DefaultValue("") @QueryParam("origen") String origen,
			@DefaultValue("") @QueryParam("importador") String idImportador,
			@DefaultValue("") @QueryParam("fecha") String fecha,
			@DefaultValue("") @QueryParam("carga") String idCarga,
			@DefaultValue("") @QueryParam("destino") String destino,
			@DefaultValue("") @QueryParam("tipo") String tipo) {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		ListaCargas a =null;
		try {
			if (!idImportador.equals(""))
			{
				a=tm.darMovimientosCarga(origen, idImportador, idCarga, destino, tipo, fecha, 0);
			}
			else
			{
				a=tm.darMovimientosCarga(origen, idImportador, idCarga, destino, tipo, fecha, 1);
			}
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(a).build();
	}
	
	


}
