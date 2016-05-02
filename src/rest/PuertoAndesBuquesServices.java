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


import java.sql.Date;
import java.sql.SQLException;

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
import vos.ListaArribosSalidas;
import vos.ListaBuques;
import vos.ListaCargas;
import vos.ListaImportadores;
import vos.Llegan;
import vos.Patio;
import vos.Salen;

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

	@PUT
	@Path("/barco")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBarco(Barco barco) {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		try {
			tm.addBarco(barco);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(barco).build();
	}
	
	@PUT
	@Path("/registrarLlegada")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addLlegada(Llegan llegan) {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		try {
			tm.addLlegada(llegan);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(llegan).build();
	}
	
	@PUT
	@Path("/registrarSalida")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addSalida(Salen salen) {
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		try {
			tm.addSalida(salen);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(salen).build();
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
	
	@GET
	@Path("/consultarArriboSalida1")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response consultarArriboSalida1(@DefaultValue("") @QueryParam("fecha") Date fecha1, 
			@DefaultValue("") @QueryParam("fecha") Date fecha2, 
			@DefaultValue("") @QueryParam("nombreBarco") String nombreBarco, 
			@DefaultValue("") @QueryParam("tipoBarco") String tipoBarco, 
			@DefaultValue("") @QueryParam("tipoCarga") String tipoCarga) 
	{
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		ListaArribosSalidas asb = null;
		try {
			asb = tm.consultarLLegadasSalidas1(fecha1, fecha2, nombreBarco, tipoBarco, tipoCarga);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(asb).build();
	}
	
	@GET
	@Path("/consultarArriboSalida2")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response consultarArriboSalida2(@DefaultValue("") @QueryParam("fecha") Date fecha1, 
			@DefaultValue("") @QueryParam("fecha") Date fecha2, 
			@DefaultValue("") @QueryParam("nombreBarco") String nombreBarco, 
			@DefaultValue("") @QueryParam("tipoBarco") String tipoBarco, 
			@DefaultValue("") @QueryParam("tipoCarga") String tipoCarga) 
	{
		PuertoAndesMaster tm = new PuertoAndesMaster(getPath());
		ListaArribosSalidas asb = null;
		try {
			asb = tm.consultarLLegadasSalidas2(fecha1, fecha2, nombreBarco, tipoBarco, tipoCarga);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(asb).build();
	}
}