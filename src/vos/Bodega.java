package vos;

import org.codehaus.jackson.annotate.*;


public class Bodega {

	//// Atributos

	
	@JsonProperty(value="id")
	private String id;

	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="ancho")
	private int ancho;
	
	@JsonProperty(value="largo")
	private int largo;
	
	@JsonProperty(value="plataforma")
	private String plataforma;
	
	@JsonProperty(value="separacion")
	private int separacion;
	
	@JsonProperty(value="cuartoFrio")
	private String cuartoFrio;

	
	public Bodega(@JsonProperty(value="id")String id, @JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="ancho")int ancho,@JsonProperty(value="largo")int largo,
			@JsonProperty(value="plataforma")String plataforma,
			@JsonProperty(value="separacion")int separacion,
			@JsonProperty(value="cuartoFrio") String cuartoFrio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ancho = ancho;
		this.largo = largo;
		this.plataforma = plataforma;
		this.separacion = separacion;
		this.cuartoFrio = cuartoFrio;
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


	public int getAncho() {
		return ancho;
	}


	public void setAncho(int ancho) {
		this.ancho = ancho;
	}


	public int getLargo() {
		return largo;
	}


	public void setLargo(int largo) {
		this.largo = largo;
	}


	public String getPlataforma() {
		return plataforma;
	}


	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}


	public int getSeparacion() {
		return separacion;
	}


	public void setSeparacion(int separacion) {
		this.separacion = separacion;
	}


	public String getCuartoFrio() {
		return cuartoFrio;
	}


	public void setCuartoFrio(String cuartoFrio) {
		this.cuartoFrio = cuartoFrio;
	}
	
	


}
