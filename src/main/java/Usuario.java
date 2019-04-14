import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.HashSet;

public class Usuario {
	
	HashSet<Guardarropa> guardarropas = new HashSet<Guardarropa>();
	public int id;
	public String alias;
 			
	public  HashSet<HashSet<Prenda>> pedirAtuendo() {
		return guardarropas.stream().flatMap(g-> g.devolverAtuendos()).collect(Collectors.toSet());
	}
	
	public void crearGuardarropa() {
		Guardarropa unGuardarropa = new Guardarropa();
		guardarropas.add(unGuardarropa);
	}
	public void cargarPrenda(Guardarropa unGuardarropa,Prenda.Color colorPri,Prenda.Color colorSec,Tipo type,Prenda.Material telita) {
		Prenda unaPrenda = new Prenda(colorPri,colorSec,type,telita);
		unGuardarropa.agregarPrenda(unaPrenda);
	}
	
}
