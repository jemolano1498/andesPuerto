package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Salen {
	
	@JsonProperty(value="idBarco")
	private String idBarco;
	
	@JsonProperty(value="fechaSalida")
	private String fechaSalida;
	
	@JsonProperty(value="horaSalida")
	private String horaSalida;
	
	@JsonProperty(value="idMuelle")
	private String idMuelle;
	
	@JsonProperty(value="destino")
	private String destino;
	
	public Salen(@JsonProperty(value="idBarco")String idBarco,
			@JsonProperty(value="fechaSalida")String fechaSalida,
			@JsonProperty(value="horaSalida")String horaSalida,
			@JsonProperty(value="idMuelle")String idMuelle,
			@JsonProperty(value="destino")String destino) {
		super();
		this.idBarco = idBarco;
		this.fechaSalida = fechaSalida;
		this.horaSalida= horaSalida;
		this.idMuelle = idMuelle;
		this.destino = destino;
	}

	public String getIdBarco() {
		return idBarco;
	}

	public void setIdBarco(String idBarco) {
		this.idBarco = idBarco;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String gethoraSalida() {
		return horaSalida;
	}

	public void sethoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public String getIdMuelle() {
		return idMuelle;
	}

	public void setIdMuelle(String idMuelle) {
		this.idMuelle = idMuelle;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}
}