import java.util.Set;
import java.util.stream.Collectors;

public class Sugeridor {
	
	private ProveedorClima proveedorClima;
	
	public  static Set<Set<Prenda>> sugerirPrendasPara(Usuario unUsuario){
		Set<Set<Prenda>> atuendos = unUsuario
									.guardarropas
									.stream()
									.map(guardarrropa->guardarrropa.pedirAtuendos())
									.flatMap(guardarropa->guardarropa.stream())
									.collect(Collectors.toSet());
		if (atuendos.isEmpty())
			throw new NoHayAtuendosDisponiblesException("WARNING: falta ingresar prendas en los guardarropas para obtener posibles atuendos");
		
		return atuendos;
	}
	
}
