import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;

public class Usuario {
	
	public HashSet<Guardarropa> guardarropas = new HashSet<Guardarropa>();

	public  Set<Set<Prenda>> pedirAtuendos() {
		Set<Set<Prenda>> atuendos = guardarropas
									.stream()
									.map(guardarrropa->guardarrropa.pedirAtuendos())
									.flatMap(guardarropa->guardarropa.stream())
									.collect(Collectors.toSet());
		if (atuendos.size()==0) {
			throw new NoHayAtuendosDisponiblesException("WARNING: falta ingresar prendas en los guardarropas para obtener posibles atuendos");
		}
		return atuendos;
	}
	
	public void agregarGuardarropa(Guardarropa unGuardarropa) {
		this.guardarropas.add(unGuardarropa);
	}
	
	public void cargarPrenda(Guardarropa unGuardarropa,Prenda unaPrenda) {
		if(this.yaSeCargoLaPrenda(unaPrenda)) {
			throw new YaSeEncuentraCargadaException("WARNING: la prenda ingresada ya se encuentra cargada");
		}
		unGuardarropa.cargarPrenda(unaPrenda);
	}
	public boolean yaSeCargoLaPrenda(Prenda unaPrenda) {
		return guardarropas.stream().anyMatch(guardarropa->guardarropa.prendas.contains(unaPrenda));
	}
	
	public HashSet<Guardarropa> getGuardarropas() {
		return this.guardarropas;
	}
	
	
}
