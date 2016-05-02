package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaAreas {
	@JsonProperty(value="areas")
	private List<Area> areas;
	
	public ListaAreas( @JsonProperty(value="areas")List<Area> areas){
		this.areas = areas;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
}