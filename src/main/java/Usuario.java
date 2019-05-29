import java.util.Set;
import java.util.HashSet;

public class Usuario {
	private TipoUsuario tipo;
	private int  maximoDePrendas;
	private HashSet<Guardarropa> guardarropas = new HashSet<Guardarropa>();

	public Usuario(TipoUsuario tipo, int maximoDePrendas) {
		this.tipo = tipo;
		this.maximoDePrendas = maximoDePrendas;
	}
	
	public void agregarGuardarropa(Guardarropa unGuardarropa) {
		//No estoy segura si se deberia testear.
		this.validacionSegunTipoUsuario(unGuardarropa.prendas.size()-1); 
		this.guardarropas.add(unGuardarropa);
	}
	
	public void cargarPrenda(Guardarropa unGuardarropa,Prenda unaPrenda) {
		if(this.yaSeCargoLaPrenda(unaPrenda)) {
			throw new YaSeEncuentraCargadaException("WARNING: la prenda ingresada ya se encuentra cargada");
		}
		this.validacionSegunTipoUsuario(unGuardarropa.prendas.size());		
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
	//Recibe las nuevas sugerencias

	public void validacionSegunTipoUsuario(int cantidadDePrendasDelGuardarropas) {
		if(tipo == TipoUsuario.GRATUITO && cantidadDePrendasDelGuardarropas >= maximoDePrendas) {
			throw new SeExcedioElLimiteDeCapacidadDelGuardarropaException("WARNNING: el guardarropa ya contiene el l�mite de su capacidad");
		}
	}

}
