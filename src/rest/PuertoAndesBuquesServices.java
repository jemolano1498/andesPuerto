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
import vos.Carga;
import vos.Importador;
import vos.ListaBuques;
import vos.ListaCargas;
import vos.ListaImportadores;

@Path("buques")
public class PuertoAndesBuquesServices {

	@Context
	private ServletContext context;

	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}

	@GET
	@Path("/salida/{name}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response RegistrarSalida(@javax.ws.rs.PathParam("name") String name) {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		Barco barco;
		try {
			if (name == null || name.length() == 0)
				throw new Exception("ID del buque no valido");
			barco=tm.AsignarSalida(name);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(barco).build();
	}
	
	@GET
	@Path("/deshabilitarBarco")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deshabilitarBuque(@DefaultValue("") @QueryParam("barco") String idBarco,
			@DefaultValue("") @QueryParam("estado") String tipoDeshab) {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		ListaCargas carg = null;
		String estado = "";
		if (tipoDeshab.contains("av")||tipoDeshab.contains("da"))
		{
			estado="7";
		}
		else if (tipoDeshab.contains("mant"))
		{
			estado="8";
		}
		else
		{
			estado="9";
		}
		System.out.println(idBarco + " y " +estado);
		try {
			carg=tm.deshabilitarBuque(idBarco, estado);
			
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(carg).build();
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
	
}
