import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class Guardarropa {
	Set<Prenda> prendas = new HashSet<Prenda>();
	
	public void cargarPrenda(Prenda unaPrenda){
		prendas.add(unaPrenda);
	}
	
	public Set<Set<Prenda>> pedirAtuendos(){
		Set<Set<Prenda>> atuendos = new HashSet<Set<Prenda>>();
		if(prendas.size()<4) return atuendos;
		atuendos = Sets.combinations(prendas, 4);
		//Aca tiene todas las combinaciones y se encarga de devolver solo las validas
		 atuendos = atuendos.stream().filter(atuendo->this.contienePrendasDeTodasLasCategorias(atuendo)).collect(Collectors.toSet());
		 return atuendos;
	} 
	
	private boolean contienePrendasDeCategoria(Set<Prenda> atuendo, Categoria unaCategoria) {
		return atuendo.stream().anyMatch(prenda->prenda.getTipo().categoria == unaCategoria);
	}
	
	private boolean contienePrendasDeTodasLasCategorias(Set<Prenda> atuendo) {
		return this.contienePrendasDeCategoria(atuendo, Categoria.SUPERIOR)
				&&this.contienePrendasDeCategoria(atuendo, Categoria.INFERIOR)
				&&this.contienePrendasDeCategoria(atuendo, Categoria.ACCESORIO)
				&& this.contienePrendasDeCategoria(atuendo, Categoria.CALZADO);
	}
	
	public int cantidadDePrendasGuardadas() {
		return this.prendas.size();
	}

}
