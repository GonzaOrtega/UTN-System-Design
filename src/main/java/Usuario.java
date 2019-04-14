import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.HashSet;

public class Usuario {
	
	//esto public por test
	public HashSet<Guardarropa> guardarropas = new HashSet<Guardarropa>();
	private int id;
	private String alias;
 	
	public Usuario(int ide,String ali) {
		id = ide;
		alias = ali;
	}
	
	public  HashSet<HashSet<Prenda>> pedirAtuendo() {
	//	return guardarropas.stream().flatMap(g-> g.devolverAtuendos()).collect(Collectors.toSet());
	return new HashSet(new HashSet<Prenda>());
	}
	
	public void crearGuardarropa() {
		Guardarropa unGuardarropa = new Guardarropa();
		guardarropas.add(unGuardarropa);
	}
	public void cargarPrenda(Guardarropa unGuardarropa,Color colorPri,Color colorSec,Tipo type,Material telita) {
		Prenda unaPrenda = new Prenda(colorPri,colorSec,type,telita);
		unGuardarropa.agregarPrenda(unaPrenda);
	}
	
}
