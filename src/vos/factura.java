package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class factura {
	
	@JsonProperty(value="id")
	private String id;
	
	@JsonProperty(value="id_buque")
	private String id_buque;
	
	@JsonProperty(value="id_carga")
	private String id_carga;
	
	@JsonProperty(value="dias_en_puerto")
	private String dias_en_puerto;
	
	@JsonProperty(value="costo")
	private String costo;
	
	@JsonProperty(value="id_operador")
	private String id_operador;
	
	@JsonProperty(value="id_exportador")
	private String id_exportador;
	
	public factura(@JsonProperty(value="id")String id, @JsonProperty(value="id_buque")String name,
			@JsonProperty(value="id_carga") String register, @JsonProperty(value="dias_en_puerto") 
			String dias_en_puerto, @JsonProperty(value="costo") String costo, @JsonProperty(value="id_operador")
			String id_operador, @JsonProperty(value="id_exportador") String id_exportador) {
		super();
		this.id = id;
		this.id_buque = name;
		this.id_carga = register;
		this.dias_en_puerto = dias_en_puerto;
		this.costo = costo;
		this.id_operador = id_operador;
		this.id_exportador = id_exportador;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_buque() {
		return id_buque;
	}

	public void setId_buque(String id_buque) {
		this.id_buque = id_buque;
	}

	public String getId_carga() {
		return id_carga;
	}

	public void setId_carga(String id_carga) {
		this.id_carga = id_carga;
	}

	public String getDias_en_puerto() {
		return dias_en_puerto;
	}

	public void setDias_en_puerto(String dias_en_puerto) {
		this.dias_en_puerto = dias_en_puerto;
	}

	public String getCosto() {
		return costo;
	}
	public String getBono() {
		return costo;
	}

	public void setCosto(String costo) {
		this.costo = costo;
	}

	public String getId_operador() {
		return id_operador;
	}

	public void setId_operador(String id_operador) {
		this.id_operador = id_operador;
	}

	public String getId_exportador() {
		return id_exportador;
	}

	public void setId_exportador(String id_exportador) {
		this.id_exportador = id_exportador;
	}
}
