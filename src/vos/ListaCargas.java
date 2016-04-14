package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaCargas {
	@JsonProperty(value="cargas")
	private List<Carga> cargas;
	
	public ListaCargas( @JsonProperty(value="cargas")List<Carga> cargas){
		this.cargas = cargas;
	}

	public void setCargas(List<Carga> cargas) {
		this.cargas = cargas;
	}

	public List<Carga> getCargas() {
		return cargas;
	}



}
