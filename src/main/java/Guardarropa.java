import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class Guardarropa {
	Set<Prenda> prendas = new HashSet<Prenda>();
	int temperatura = 24; // Cambiar esto cuando tengamos la API del clima jeje
	public void cargarPrenda(Prenda unaPrenda){
		prendas.add(unaPrenda);
	}
	
	public Set<Set<Prenda>> pedirAtuendos(){
		Set<Set<Prenda>> atuendos = new HashSet<Set<Prenda>>();
		if(prendas.size()<cantidadPrendasSegunTemp(temperatura)) 
			return atuendos;
		atuendos = Sets.combinations(prendas, cantidadPrendasSegunTemp(temperatura));
		//Aca tiene todas las combinaciones y se encarga de devolver solo las validas
		 atuendos = atuendos.stream().filter(atuendo->this.contienePrendasDeTodasLasCategorias(atuendo)).collect(Collectors.toSet());
		 return atuendos;
	} 
	private int cantidadPrendasSegunTemp(int temperatura) {
		if(temperatura>20) 
			return 4;
		else 
			return 5;
	}
	
	private boolean contienePrendasDeCategoria(Set<Prenda> atuendo, Categoria unaCategoria) {
		return atuendo.stream().anyMatch(prenda->prenda.getTipo().categoria == unaCategoria);
	}
	
	private boolean contienePrendasDeTodasLasCategorias(Set<Prenda> atuendo) {
		if (atuendo.size()==4) {
		return this.contienePrendasDeCategoria(atuendo, Categoria.SUPERIOR)
				&& this.contienePrendasDeCategoria(atuendo, Categoria.INFERIOR)
				&& this.contienePrendasDeCategoria(atuendo, Categoria.ACCESORIO)
				&& this.contienePrendasDeCategoria(atuendo, Categoria.CALZADO);
			}
		else 
			return this.contienePrendasDeCategoria(atuendo, Categoria.SUPERIOR)
					&& this.contienePrendasDeCategoria(atuendo, Categoria.INFERIOR)
					&& this.contienePrendasDeCategoria(atuendo, Categoria.ACCESORIO)
					&& this.contienePrendasDeCategoria(atuendo, Categoria.CALZADO)
					&& this.contienePrendasDeCategoria(atuendo, Categoria.ABRIGO);
		}//Se puede delegar y evitar la repeticion pero soy re cabeza yo -Fran
	
	public int cantidadDePrendasGuardadas() {
		return this.prendas.size();
	}

}
