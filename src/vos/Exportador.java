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

	
	public Exportador(@JsonProperty(value="id")int id, @JsonProperty(value="nombre")String name,
			@JsonProperty(value="rut") String register) {
		super();
		this.id = id;
		this.nombre = name;
		this.rut = register;
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
