package apisClima;
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
}