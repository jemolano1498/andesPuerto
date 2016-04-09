package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Area 
{
	@JsonProperty(value="id")
	private String id;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="idPuerto")
	private String idPuerto;
	
	@JsonProperty(value="capacidad")
	private String capacidad;
	
	@JsonProperty(value="estado")
	private String estado;
	
	public Area (@JsonProperty(value="id")String id,@JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="idPuerto")String idPuerto, @JsonProperty(value="capacidad")String capacidad,
			@JsonProperty(value="estado")String estado)
	{
		super();
		this.id=id;
		this.nombre=nombre;
		this.idPuerto=idPuerto;
		this.capacidad=capacidad;
		this.estado=estado;
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

	public String getIdPuerto() {
		return idPuerto;
	}

	public void setIdPuerto(String idPuerto) {
		this.idPuerto = idPuerto;
	}

	public String getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	

}
