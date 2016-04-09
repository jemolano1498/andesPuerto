package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class CuartoFrio {
	
	@JsonProperty(value="id")
	private String id;

	@JsonProperty(value="area")
	private int area;
	
	@JsonProperty(value="ancho")
	private int ancho;
	
	@JsonProperty(value="largo")
	private int largo;
	
	@JsonProperty(value="alto")
	private int alto;
	
	@JsonProperty(value="utilizacion")
	private int utilizacion;

	public CuartoFrio(@JsonProperty(value="id")String id,
			@JsonProperty(value="area")int area,
			@JsonProperty(value="ancho")int ancho,
			@JsonProperty(value="largo")int largo,
			@JsonProperty(value="alto")int alto,
			@JsonProperty(value="utilizacion")int utilizacion) {
		super();
		this.id = id;
		this.area = area;
		this.ancho = ancho;
		this.largo = largo;
		this.alto = alto;
		this.utilizacion = utilizacion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
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

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getUtilizacion() {
		return utilizacion;
	}

	public void setUtilizacion(int utilizacion) {
		this.utilizacion = utilizacion;
	}
	
	
	

}
