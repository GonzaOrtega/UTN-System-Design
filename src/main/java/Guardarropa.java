import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class Guardarropa {
	
	HashSet<Prenda> prendas = new HashSet<Prenda>();
		
	
	public void agregarPrenda(Prenda unaPrenda){
		prendas.add(unaPrenda);
	}
	
	//HashSet<Prenda> = Atuendo
	//HashSet<HashSet<Prenda>> = HashSet<Atuendo>
	public HashSet<HashSet<Prenda>> devolverAtuendos(){
		return new HashSet(this.prendas);
	}

}
