package vos;

import org.codehaus.jackson.annotate.*;


public class Exportador {

	//// Atributos

	/**
	 * Id del Importador
	 */
	@JsonProperty(value="id")
	private int id;

	/**
	 * Nombre del importador
	 */
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="rut")
	private String rut;
	
	@JsonProperty(value="id_puerto")
	private String id_puerto;
	
	@JsonProperty(value="id_factura")
	private String id_factura;
	
	public Exportador(@JsonProperty(value="id")int id, @JsonProperty(value="nombre")String name,
			@JsonProperty(value="rut") String register, @JsonProperty(value="id_puerto") 
			String id_puerto, @JsonProperty(value="id_factura") String id_factura) {
		super();
		this.id = id;
		this.nombre = name;
		this.rut = register;
		this.id_puerto = id_puerto;
		this.id_factura = id_factura;
	}
	
	public String getId_puerto() {
		return id_puerto;
	}


	public void setId_puerto(String id_puerto) {
		this.id_puerto = id_puerto;
	}


	public String getId_factura() {
		return id_factura;
	}


	public void setId_factura(String id_factura) {
		this.id_factura = id_factura;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}


}
