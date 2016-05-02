package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Llegan {
	
	@JsonProperty(value="idBarco")
	private String idBarco;
	
	@JsonProperty(value="fechaLlegada")
	private String fechaLlegada;
	
	@JsonProperty(value="hora")
	private String hora;
	
	@JsonProperty(value="idMuelle")
	private String idMuelle;
	
	@JsonProperty(value="origen")
	private String origen;
	
	public Llegan(@JsonProperty(value="idBarco")String idBarco,
			@JsonProperty(value="fechaLlegada")String fechaLlegada,
			@JsonProperty(value="hora")String hora,
			@JsonProperty(value="idMuelle")String idMuelle,
			@JsonProperty(value="origen")String origen) {
		super();
		this.idBarco = idBarco;
		this.fechaLlegada = fechaLlegada;
		this.hora= hora;
		this.idMuelle = idMuelle;
		this.origen = origen;
	}

	public String getIdBarco() {
		return idBarco;
	}

	public void setIdBarco(String idBarco) {
		this.idBarco = idBarco;
	}

	public String getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(String fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getIdMuelle() {
		return idMuelle;
	}

	public void setIdMuelle(String idMuelle) {
		this.idMuelle = idMuelle;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}
}