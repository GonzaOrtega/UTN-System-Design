import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.HashSet;

public class Usuario {
	
	public HashSet<Guardarropa> guardarropas = new HashSet<Guardarropa>();
	private int id;
	private String alias;
 	
	public Usuario(int unId,String unAlias) {
		id = unId;
		alias = unAlias;
	}

	public  Set<Set<Prenda>> pedirAtuendo() {
		return guardarropas
				.stream()
				.map(guardarrropa->guardarrropa.devolverAtuendos())
				.flatMap(guardarropa->guardarropa.stream())
				.collect(Collectors.toSet());
	}
	
	
	public void agregarGuardarropa(Guardarropa unGuardarropa) {
		this.guardarropas.add(unGuardarropa);
	}
	
	public void cargarPrenda(Guardarropa unGuardarropa,Color colorPri,Color colorSec,TipoPrenda type,Material telita) {
		Prenda unaPrenda = new Prenda(colorPri,colorSec,type,telita);
		unGuardarropa.agregarPrenda(unaPrenda);
	}
	
}
