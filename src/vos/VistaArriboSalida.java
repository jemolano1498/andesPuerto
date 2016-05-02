package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class VistaArriboSalida {

	@JsonProperty(value="nombreBarco")
	private String barco;
	
	@JsonProperty(value="fecha")
	private Date fecha;
	
	@JsonProperty(value="hora")
	private String hora;
	
	@JsonProperty(value="estado")
	private String estado;
	
	@JsonProperty(value="idRuta")
	private String idRuta;
	
	@JsonProperty(value="tipoBarco")
	private String tipoBarco;
	
	@JsonProperty(value="idMuelle")
	private String idMuelle;
	
	@JsonProperty(value="idPuerto")
	private String idPuerto;
	
	@JsonProperty(value="tipoCarga")
	private String tipoCarga;
	
	public VistaArriboSalida(@JsonProperty(value="barco")String barco,
			@JsonProperty(value="fecha")Date fecha,
			@JsonProperty(value="hora")String hora,
			@JsonProperty(value="estado")String estado,
			@JsonProperty(value="idRuta")String idRuta,
			@JsonProperty(value="tipoBarco")String tipoBarco,
			@JsonProperty(value="idMuelle")String idMuelle,
			@JsonProperty(value="idPuerto")String idPuerto,
			@JsonProperty(value="tipoCarga")String tipoCarga) {
		super();
		this.barco = barco;
		this.fecha = fecha;
		this.hora = hora;
		this.estado = estado;
		this.idRuta = idRuta;
		this.tipoBarco = tipoBarco;
		this.idMuelle = idMuelle;
		this.idPuerto = idPuerto;
		this.tipoCarga = tipoCarga;
	}

	public String getBarco() {
		return barco;
	}

	public void setBarco(String barco) {
		this.barco = barco;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(String idRuta) {
		this.idRuta = idRuta;
	}

	public String getTipoBarco() {
		return tipoBarco;
	}

	public void setTipoBarco(String tipoBarco) {
		this.tipoBarco = tipoBarco;
	}

	public String getIdMuelle() {
		return idMuelle;
	}

	public void setIdMuelle(String idMuelle) {
		this.idMuelle = idMuelle;
	}

	public String getIdPuerto() {
		return idPuerto;
	}

	public void setIdPuerto(String idPuerto) {
		this.idPuerto = idPuerto;
	}

	public String getTipoCarga() {
		return tipoCarga;
	}

	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}
}
