package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Patio {
	@JsonProperty(value="id")
	private String id;
	
	@JsonProperty(value="dimension")
	private int dimension;
	
	@JsonProperty(value="tipo")
	private String tipo;

	public Patio(@JsonProperty(value="id") String id,@JsonProperty(value="dimension") int dimension,@JsonProperty(value="tipo") String tipo) {
		super();
		this.id = id;
		this.dimension = dimension;
		this.tipo = tipo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
