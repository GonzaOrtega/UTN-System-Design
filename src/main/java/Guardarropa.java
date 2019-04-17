import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

public class Guardarropa {
	Set<Prenda> prendas = new HashSet<Prenda>();
	
	public void agregarPrenda(Prenda unaPrenda){
		prendas.add(unaPrenda);
	}
	
	public Set<Set<Prenda>> devolverAtuendos(){
		Set<Set<Prenda>> atuendos = Sets.combinations(prendas, 4);
		//Aca tiene todas las combinaciones y se encarga de devolver solo las validas
		 atuendos = atuendos.stream().filter(x->this.todosDistintos(x)).collect(Collectors.toSet());
		 return atuendos;
	} 
	
	private boolean todosDistintos(Set<Prenda> lista) {
		boolean a = lista.stream().map(y->y.tipo.categoria).collect(Collectors.toSet()).contains(Categoria.SUPERIOR);
		boolean b = lista.stream().map(z->z.tipo.categoria).collect(Collectors.toSet()).contains(Categoria.INFERIOR);
		boolean c = lista.stream().map(y->y.tipo.categoria).collect(Collectors.toSet()).contains(Categoria.CALZADO);
		boolean d = lista.stream().map(y->y.tipo.categoria).collect(Collectors.toSet()).contains(Categoria.ACCESORIO);
		
		return a && b && c && d;
	}

}
