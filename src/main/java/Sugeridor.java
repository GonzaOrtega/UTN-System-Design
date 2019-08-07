import java.util.Set;
import java.util.stream.Collectors;
import apisClima.ProveedorClima;
import exceptions.NoHayAtuendosDisponiblesException;

public class Sugeridor {
	private  ProveedorClima proveedorDeClima;
	
	public Sugeridor(ProveedorClima proveedor) {
		proveedorDeClima=proveedor;
	}
	
	public void proveedorDeClima(ProveedorClima proveedorDeClima) {
		this.proveedorDeClima = proveedorDeClima;
	}
	
	public ProveedorClima proveedorDeClima() {
		return proveedorDeClima;
	}

	public Set<Set<Prenda>> sugerirPrendasPara(Usuario unUsuario){
		Set<Set<Prenda>> atuendos = unUsuario
									.getGuardarropas()
									.stream()
									.map(guardarrropa->guardarrropa.pedirAtuendosSegun(proveedorDeClima,unUsuario))
									.flatMap(guardarropa->guardarropa.stream())
									.collect(Collectors.toSet());
		if (atuendos.isEmpty())
			throw new NoHayAtuendosDisponiblesException("WARNING: falta ingresar prendas en los guardarropas para obtener posibles atuendos");
		
		return atuendos;
	}
	
}
