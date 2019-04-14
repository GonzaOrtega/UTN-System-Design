import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class Guardarropa {
	
	//HashSet<Prenda> prendas = new HashSet<Prenda>();
	ArrayList<Prenda> prendas = new ArrayList<Prenda>();
	
	public void agregarPrenda(Prenda unaPrenda){
		prendas.add(unaPrenda);
	}
		
	public HashSet<HashSet<Prenda>> devolverAtuendos() {
	//	return new HashSet(new HashSet<Prenda>());
		HashSet<Prenda> atuendo =  new HashSet<Prenda>();
		atuendo.addAll(prendas.drop(4));
	}
	
	public void agregarRopasLocas() {//para testear simplemente
		this.agregarPrenda(new Prenda(Color.ROSA,Color.VERDE, new Tipo("Remera",Tipo.Categoria.SUPERIOR), Material.ALGODON));
		this.agregarPrenda(new Prenda(Color.ROSA,Color.AZUL, new Tipo("Pantalon",Tipo.Categoria.INFERIOR), Material.ALGODON));
		this.agregarPrenda(new Prenda(Color.AZUL,Color.VERDE, new Tipo("Zapatillas",Tipo.Categoria.CALZADO), Material.ALGODON));
		this.agregarPrenda(new Prenda(Color.AZUL,Color.VERDE, new Tipo("Pañuelo",Tipo.Categoria.ACCESORIO), Material.ALGODON));
	}
}
