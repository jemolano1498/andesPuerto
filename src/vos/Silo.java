package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Silo {
	@JsonProperty(value="id")
	private String id;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="capacidad")
	private int capacidad;

	public Silo(@JsonProperty(value="id")String id,
			@JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="capacidad")int capacidad) {
		super();
		this.id = id;
		this.nombre = nombre;
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

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	

}
