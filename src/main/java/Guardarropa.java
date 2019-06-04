import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import exceptions.*;

public class Guardarropa {
	Set<Prenda> prendas = new HashSet<Prenda>();

	public void cargarPrenda(Prenda unaPrenda){
		prendas.add(unaPrenda);
	}
	
	//public Set<Set<Prenda>> pedirAtuendosSegun(ProveedorClima proveedor){
	public ArrayList<Set<Prenda>> pedirAtuendosSegun(ProveedorClima proveedor){

		/*Set<Set<Prenda>> atuendos = new HashSet<Set<Prenda>>();
		if(prendas.size() < 4) 
			return atuendos;
		atuendos = Sets.combinations(prendas,4);
		atuendos = atuendos.stream().filter(atuendo->this.contienePrendasDeTodasLasCategorias(atuendo)).collect(Collectors.toSet());
		return atuendos;
		//Aca tiene todas las combinaciones y se encarga de devolver solo las validas
		 System.out.println(atuendos);*/
		 //--------------------esto de abajo es nuevo
		
		Set<Set<Prenda>> elAux = new HashSet<Set<Prenda>>();
		elAux = this.parteNoSuperior();
		ArrayList<Set<Prenda>> atuendosInferior = new ArrayList<Set<Prenda>>(elAux);
//		System.out.println("Ultimo cambio:");
//		System.out.println(atuendosInferior);
		
		Set<Set<Prenda>> atuendoSup = parteSuperior(proveedor);
		ArrayList<Set<Prenda>> listaAtuSuperior = new ArrayList<Set<Prenda>>(atuendoSup);
//		System.out.println("Ultimo cambio 2:");
//		System.out.println(listaAtuSuperior);
		ArrayList<Set<Prenda>> listaReturn = new ArrayList<Set<Prenda>>();
		for (int i=0;i<atuendosInferior.size();i++)
		{
			for(int j=0;j<listaAtuSuperior.size();j++)
			{
				Set<Prenda> atuInf = new HashSet<Prenda>(atuendosInferior.get(i));
				Set<Prenda> atuSup = new HashSet<Prenda>(listaAtuSuperior.get(j));
				atuInf.addAll(atuSup);
				//duplicates.retainAll(listaAtuSuperior.get(j));
				//atuendosInferior.get(i).addAll(listaAtuSuperior.get(j));
				listaReturn.add(atuInf);
			}
		}
		 
/*		 for( Iterator<Set<Prenda>> it = atuendoSup.iterator(); it.hasNext();) { 
			    HashSet<Prenda> x = (HashSet<Prenda>)it.next();
			   atr = atuendos.stream().map(atu->atu.addAll(x)).collect(Collectors.toSet());
			    System.out.println(atr);
		 }*/
		 
		 
		 return listaReturn;
	}
	

	
	
	
	private Set<Prenda> noSuperior(){
		return prendas.stream().filter(p->p.getTipo().categoria != 
				Categoria.SUPERIOR).collect(Collectors.toSet());
	}
	private Set<Set<Prenda>> parteNoSuperior(){
		Set<Prenda> partesInferiores = this.noSuperior();
		Set<Set<Prenda>> powerSetInferiores = Sets.powerSet(partesInferiores); 
		powerSetInferiores = powerSetInferiores.stream()
				.filter(at->this.parteInferiorValida(at))
				.collect(Collectors.toSet());
		return powerSetInferiores;
	}
	private Set<Set<Prenda>> parteSuperior(ProveedorClima clima){
		Set<Prenda> partesSuperiores = this.soloSuperior();
		Set<Set<Prenda>> powerSetSuperiores = Sets.powerSet(partesSuperiores); 
		powerSetSuperiores = powerSetSuperiores.stream()
				.filter(at->this.parteSuperiorValida(at,clima))
				.collect(Collectors.toSet());
//		System.out.println("PowerSet: ");
//		System.out.println(powerSetSuperiores); //Lo dejo para hacer pruebas despues
		return powerSetSuperiores;
		
	}
	private Set<Prenda> soloSuperior(){
		Set<Prenda> ret = prendas.stream().filter(p->p.getTipo().categoria == 
				Categoria.SUPERIOR).collect(Collectors.toSet());
		return ret;
	}
	private boolean parteSuperiorValida(Set<Prenda> ps, ProveedorClima clima) {
		Set<Prenda> aux = ps.stream().filter(prenda->prenda.getEsBase()).collect(Collectors.toSet());
		boolean a = aux.size() == 1;
		int aux2 = ps.stream().mapToInt(prenda -> prenda.getNivelAbrigo()).sum();
	//	boolean b = this.EstaEnRango(aux2,clima);
	//	return a && b; //YA SE QUE GASTON ODIA ESTO PERO BUENO SOY ASI -FRAN
		return a;
	}
	private boolean EstaEnRango(int nivelAbrigo,ProveedorClima clima) {
		int nf =this.nivelFrio(clima.temperatura());
		return nivelAbrigo >= nf-1 && nivelAbrigo <= nf+1;
		//Aca establezco un rango, fijense definicion de nivel frio
		//Esto tenemos que charlarlo todos jeje
	}
	
	private boolean parteInferiorValida(Set<Prenda> ps) {
		return this.contienePrendasDeCategoria(ps, Categoria.INFERIOR)
				&& this.contienePrendasDeCategoria(ps, Categoria.CALZADO);
	}
	private boolean contienePrendasDeCategoria(Set<Prenda> atuendo, Categoria unaCategoria) {
		return atuendo.stream().anyMatch(prenda->prenda.getTipo().categoria == unaCategoria);
	}
	
	private boolean contienePrendasDeTodasLasCategorias(Set<Prenda> atuendo) {
		return  this.contienePrendasDeCategoria(atuendo, Categoria.INFERIOR)
				&& this.contienePrendasDeCategoria(atuendo, Categoria.ACCESORIO)
				&& this.contienePrendasDeCategoria(atuendo, Categoria.CALZADO);
		//&& this.contienePrendasDeCategoria(atuendo, Categoria.SUPERIOR)
	}
	public int cantidadDePrendasGuardadas() {
		return this.prendas.size();
	}
	private int nivelFrio(double temperatura) {
		if (temperatura < 0)
			return 6;
		if (temperatura < 10)
			return 4;
		if (temperatura < 15)
			return 2;
		if (temperatura < 20)
			return 1; //irias con remera manga larga/corta
		else
			return -1;//irias solo con remera manga corta porque esta abriga 0
	}

}
