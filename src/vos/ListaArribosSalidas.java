package vos;

import java.util.ArrayList;
import org.codehaus.jackson.annotate.JsonProperty;

public class ListaArribosSalidas {
	
	@JsonProperty(value="arribosSalidas")
	private ArrayList<VistaArriboSalida> arribosSalidas;
	
	public ListaArribosSalidas( @JsonProperty(value="arribosSalidas")ArrayList<VistaArriboSalida> arribosSalidas){
		this.arribosSalidas = arribosSalidas;
	}

	public ArrayList<VistaArriboSalida> getArribosSalidas() {
		return arribosSalidas;
	}

	public void setArribosSalidas(ArrayList<VistaArriboSalida> arribosSalidas) {
		this.arribosSalidas = arribosSalidas;
	}
}