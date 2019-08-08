package domain.apisClima;

public interface ProveedorClima {	
	public double temperatura();
	public double velocidadViento();
	public boolean lluviasFuertes();
   
	public default boolean alertaDeCalor() {
    	return this.temperatura()>=35;
    }
    public default boolean vientosFuertes() {
    	return this.velocidadViento()>=62;
    }
    public default boolean alertaDeFrio() {
    	return this.temperatura()<=5;
    }
    
    public default boolean alertaMeterologica() {
    	return this.alertaDeCalor() || this.lluviasFuertes() || this.vientosFuertes() || this.alertaDeFrio();
    }
    
}