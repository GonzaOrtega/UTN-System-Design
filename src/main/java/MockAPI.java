public class MockAPI implements ProveedorClima{	
	private double temperatura;
	
	public MockAPI(double temp) {
		temperatura = temp;
	}
	
	public double obtenerTemp() {return temperatura;}
	
	public double temperatura() { return this.obtenerTemp(); }
}
