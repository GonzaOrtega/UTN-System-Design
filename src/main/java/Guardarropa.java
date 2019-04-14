import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

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
		this.agregarPrenda(new Prenda(Color.VIOLETA,Color.AZUL, new Tipo(Categoria.SUPERIOR), Material.ALGODON));
	}
		//HashSet<Prenda> = Atuendo
	//HashSet<HashSet<Prenda>> = HashSet<Atuendo>
	public Set<Set<Prenda>> devolverAtuendos(){
		//return new HashSet(this.prendas);
		 Set<Set<Prenda>> atuendos = Sets.combinations(ImmutableSet.of(this.prendas.get(0),this.prendas.get(1),
				this.prendas.get(2),this.prendas.get(3),this.prendas.get(4)), 4);
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
