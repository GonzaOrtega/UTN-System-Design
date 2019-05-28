import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class Guardarropa {
	Set<Prenda> prendas = new HashSet<Prenda>();
	public double temperatura;
	
	public Guardarropa(ProveedorClima proveedorClima) {
		super();
		this.temperatura = this.getTemperatura(proveedorClima);
	}

	public void cargarPrenda(Prenda unaPrenda){
		prendas.add(unaPrenda);
	}
	
	public Set<Set<Prenda>> pedirAtuendos(){
		Set<Set<Prenda>> atuendos = new HashSet<Set<Prenda>>();
		if(prendas.size()< cantidadPrendasSegunTemp(temperatura)) 
			return atuendos;
		atuendos = Sets.combinations(prendas, (int) cantidadPrendasSegunTemp(temperatura));
		//Aca tiene todas las combinaciones y se encarga de devolver solo las validas
		 atuendos = atuendos.stream().filter(atuendo->this.contienePrendasDeTodasLasCategorias(atuendo)).collect(Collectors.toSet());
		 return atuendos;
	}
	
	private double getTemperatura(ProveedorClima proveedorClima) {
		return proveedorClima.temperatura();
	}
	
	private double cantidadPrendasSegunTemp(double temperatura) {
		if(temperatura>20.0) 
			return 4.0;
		else 
			return 5.0;
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
	public void setTemp(int grados) {
		this.temperatura = grados;
	}

}
