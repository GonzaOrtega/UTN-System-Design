import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class Guardarropa {
	
	HashSet<Prenda> prendas = new HashSet<Prenda>();
	
	public void agregarPrenda(Prenda unaPrenda){
		prendas.add(unaPrenda);
	}
		
	public HashSet<HashSet<Prenda>> devolverAtuendos() {
		return new HashSet(new HashSet<Prenda>());
	}
}

