import java.util.Set;
import java.util.stream.Collectors;

public class Sugeridor {
	private ProveedorClima proveedorDeClima;
	public Sugeridor(ProveedorClima proveedor) {
		proveedorDeClima=proveedor;
	}
		
	public void setProveedorDeClima(ProveedorClima proveedorDeClima) {
		this.proveedorDeClima = proveedorDeClima;
	}

	public Set<Set<Prenda>> sugerirPrendasPara(Usuario unUsuario){
		Set<Set<Prenda>> atuendos = unUsuario
									.getGuardarropas()
									.stream()
									.map(guardarrropa->guardarrropa.pedirAtuendosSegun(proveedorDeClima))
									.flatMap(guardarropa->guardarropa.stream())
									.collect(Collectors.toSet());
		if (atuendos.isEmpty())
			throw new NoHayAtuendosDisponiblesException("WARNING: falta ingresar prendas en los guardarropas para obtener posibles atuendos");
		
		return atuendos;
	}
	
}
