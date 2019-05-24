import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;

public class Usuario {
	private TipoUsuario tipo;
	int  maximoDePrendas;
	public Usuario(TipoUsuario tipo, int maximoDePrendas) {
		this.tipo = tipo;
		this.maximoDePrendas = maximoDePrendas;
	}
	public HashSet<Guardarropa> guardarropas = new HashSet<Guardarropa>();
	
	public void agregarGuardarropa(Guardarropa unGuardarropa) {
		this.guardarropas.add(unGuardarropa);
	}
	
	public void cargarPrenda(Guardarropa unGuardarropa,Prenda unaPrenda) {
		if(this.yaSeCargoLaPrenda(unaPrenda)) {
			throw new YaSeEncuentraCargadaException("WARNING: la prenda ingresada ya se encuentra cargada");
		}
		if(tipo == TipoUsuario.GRATUITO && unGuardarropa.prendas.size() >= maximoDePrendas) {
			throw new SeExcedioElLimiteDeCapacidadDelGuardarropaException("WARNNING: el guardarropa ya contiene el límite de su capacidad");
		}
		unGuardarropa.cargarPrenda(unaPrenda);
	}
	public boolean yaSeCargoLaPrenda(Prenda unaPrenda) {
		return guardarropas.stream().anyMatch(guardarropa->guardarropa.prendas.contains(unaPrenda));
	}
	
	public HashSet<Guardarropa> getGuardarropas() {
		return this.guardarropas;
	}
	public TipoUsuario getTipo() {
		return tipo;
	}
	
	
}
