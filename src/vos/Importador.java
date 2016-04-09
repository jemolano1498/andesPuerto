package vos;

import org.codehaus.jackson.annotate.*;


public class Importador {

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

	/**
	 * Duración en minutos del video
	 */
	@JsonProperty(value="tipo")
	private String tipo;
	
	@JsonProperty(value="registro")
	private String registro;
	
	@JsonProperty(value="tipo2")
	private String tipo2;
	
	@JsonProperty(value="idPuerto")
	private String idPuerto;

	
	public Importador(@JsonProperty(value="id")int id, @JsonProperty(value="nombre")String name,@JsonProperty(value="tipo") String type,
			@JsonProperty(value="registro") String register,@JsonProperty(value="tipo2") String type2
			,@JsonProperty(value="idPuerto") String idPuerto) {
		super();
		this.id = id;
		this.nombre = name;
		this.tipo = type;
		this.registro = register;
		this.tipo2 = type2;
		this.idPuerto = idPuerto;
	}
	
	
	public String getIdPuerto() {
		return idPuerto;
	}


	public void setIdPuerto(String idPuerto) {
		this.idPuerto = idPuerto;
	}


	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public String getTipo2() {
		return tipo2;
	}

	public void setTipo2(String tipo2) {
		this.tipo2 = tipo2;
	}


}
