import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class Guardarropa {
	
	//HashSet<Prenda> prendas = new HashSet<Prenda>();
	ArrayList<Prenda> prendas = new ArrayList<Prenda>();
	
	public void agregarPrenda(Prenda unaPrenda){
		prendas.add(unaPrenda);
	}

	
	public void agregarRopasLocas() {//para testear simplemente
		this.agregarPrenda(new Prenda(Color.ROSA,Color.VERDE, new Tipo(Categoria.SUPERIOR), Material.ALGODON));
		this.agregarPrenda(new Prenda(Color.ROSA,Color.AZUL, new Tipo(Categoria.INFERIOR), Material.ALGODON));
		this.agregarPrenda(new Prenda(Color.AZUL,Color.VERDE, new Tipo(Categoria.CALZADO), Material.ALGODON));
		this.agregarPrenda(new Prenda(Color.AZUL,Color.VERDE, new Tipo(Categoria.ACCESORIO), Material.ALGODON));	
	}
		//HashSet<Prenda> = Atuendo
	//HashSet<HashSet<Prenda>> = HashSet<Atuendo>
	public HashSet<HashSet<Prenda>> devolverAtuendos(){
		return new HashSet(this.prendas);
	}

}
