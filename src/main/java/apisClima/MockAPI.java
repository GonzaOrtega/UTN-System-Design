package apisClima;
public class MockAPI implements ProveedorClima{	
	private double temperatura;
	private double velocidadViento;
	private boolean lluviasFuertes;
	
	//Setters
	public void velocidadViento(double velocidadViento) {
		this.velocidadViento = velocidadViento;
	}

	public MockAPI(double temp) {
		temperatura = temp;
	}
	
	public void setLluviasFuertes(boolean lluviasFuertes) {
		this.lluviasFuertes = lluviasFuertes;
	}
	
	//Getters
	public boolean lluviasFuertes() {return lluviasFuertes;}
	
	public double velocidadViento() {return velocidadViento;}
	
	public double temperatura() {return temperatura;}
	
}
