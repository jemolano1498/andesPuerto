package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Barco {
	
	@JsonProperty(value="id")
	private String id;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="nombreAgente")
	private String nombreAgente;
	
	@JsonProperty(value="registroCapitania")
	private String registroCapitania;
	
	@JsonProperty(value="id_ruta")
	private String id_ruta;
	
	@JsonProperty(value="estado")
	private String estado;
	
	@JsonProperty(value="capacidad")
	private String capacidad;
	
	@JsonProperty(value="tipo")
	private String tipo;

	public Barco(@JsonProperty(value="id")String id,
			@JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="nombreAgente")String nombreAgente,
			@JsonProperty(value="registroCapitania")String registroCapitania,
			@JsonProperty(value="id_ruta")String id_ruta,
			@JsonProperty(value="estado")String estado,
			@JsonProperty(value="capacidad")String capacidad,
			@JsonProperty(value="tipo")String tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nombreAgente = nombreAgente;
		this.registroCapitania = registroCapitania;
		this.id_ruta = id_ruta;
		this.estado=estado;
		this.capacidad=capacidad;
		this.tipo=tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreAgente() {
		return nombreAgente;
	}

	public void setNombreAgente(String nombreAgente) {
		this.nombreAgente = nombreAgente;
	}

	public String getRegistroCapitania() {
		return registroCapitania;
	}

	public void setRegistroCapitania(String registroCapitania) {
		this.registroCapitania = registroCapitania;
	}

	public String getId_ruta() {
		return id_ruta;
	}

	public void setId_ruta(String id_ruta) {
		this.id_ruta = id_ruta;
	}
}
