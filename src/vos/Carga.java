package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Carga {
	@JsonProperty(value="id")String id;
	@JsonProperty(value="tipo_carga")String tipo_carga;
	@JsonProperty(value="id_barco")String id_barco;
	@JsonProperty(value="id_entrega")String id_entrega;
	@JsonProperty(value="id_equipo")String id_equipo;
	@JsonProperty(value="id_vehiculo")String id_vehiculo;
	
	public Carga(@JsonProperty(value="id")String id,
			@JsonProperty(value="id")String tipo_carga,
			@JsonProperty(value="id")String id_barco,
			@JsonProperty(value="id")String id_entrega,
			@JsonProperty(value="id")String id_equipo,
			@JsonProperty(value="id")String id_vehiculo) {
		super();
		this.id = id;
		this.tipo_carga = tipo_carga;
		this.id_barco = id_barco;
		this.id_entrega = id_entrega;
		this.id_equipo = id_equipo;
		this.id_vehiculo = id_vehiculo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipo_carga() {
		return tipo_carga;
	}

	public void setTipo_carga(String tipo_carga) {
		this.tipo_carga = tipo_carga;
	}

	public String getId_barco() {
		return id_barco;
	}

	public void setId_barco(String id_barco) {
		this.id_barco = id_barco;
	}

	public String getId_entrega() {
		return id_entrega;
	}

	public void setId_entrega(String id_entrega) {
		this.id_entrega = id_entrega;
	}

	public String getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(String id_equipo) {
		this.id_equipo = id_equipo;
	}

	public String getId_vehiculo() {
		return id_vehiculo;
	}

	public void setId_vehiculo(String id_vehiculo) {
		this.id_vehiculo = id_vehiculo;
	}


}
