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

import tm.PuertoAndesMaster;
import vos.Bodega;
import vos.Cobertizo;
import vos.ListaAreas;
import vos.ListaCargas;
import vos.ListaExportadores;
import vos.Patio;
import vos.Silo;


@Path("areas")
public class PuertoAndesAreasServices {

	@Context
	private ServletContext context;


	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAreas() 
	{
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		ListaAreas areas;
		try 
		{
			areas = tm.darAreas();
		} 
		catch (Exception e) 
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(areas).build();
	}
	@PUT
	@Path("/bodega")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBodega(Bodega bodega) {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		try {
			tm.addBodega(bodega);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(bodega).build();
	}
	
	@PUT
	@Path("/patio")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPatio(Patio patio) {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		try {
			tm.addPatio(patio);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(patio).build();
	}
	
	@PUT
	@Path("/silo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addSilo(Silo silo) {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		try {
			tm.addSilo(silo);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(silo).build();
	}
	
	@GET
	@Path("/deshabilitarArea")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deshabilitarBuque(@DefaultValue("") @QueryParam("area") String idArea) {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		ListaCargas carg = null;
		
		try {
			carg=tm.deshabilitarArea(idArea);
			
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(carg).build();
	}

}
