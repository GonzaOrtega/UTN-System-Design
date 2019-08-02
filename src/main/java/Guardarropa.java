import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import apisClima.ProveedorClima;
import enums.Categoria;
import enums.TipoPrenda;
import exceptions.*;
import enums.*;

public class Guardarropa {
	Set<Prenda> prendas = new HashSet<Prenda>();

	public void cargarPrenda(Prenda unaPrenda){
		prendas.add(unaPrenda);
	}
	public ArrayList<Set<Prenda>> pedirAtuendosSegun(ProveedorClima proveedor){
		Set<Set<Prenda>> elAux = new HashSet<Set<Prenda>>();
		elAux = this.parteNoSuperior(proveedor); //esto esta asi por un tema de Set y List
		ArrayList<Set<Prenda>> atuendosInferior = new ArrayList<Set<Prenda>>(elAux);
//		System.out.println(atuendosInferior);
		Set<Set<Prenda>> atuendoSup = parteSuperior(proveedor);
		ArrayList<Set<Prenda>> listaAtuSuperior = new ArrayList<Set<Prenda>>(atuendoSup);
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
		 return listaReturn;
	}
	
	
	private Set<Prenda> noSuperior(){
		return prendas.stream().filter(p->p.getTipo().categoria != 
				Categoria.SUPERIOR).collect(Collectors.toSet());
	}
	private Set<Set<Prenda>> parteNoSuperior(ProveedorClima clima){
		Set<Prenda> partesInferiores = this.noSuperior();
		Set<Set<Prenda>> powerSetInferiores = Sets.powerSet(partesInferiores); 
		powerSetInferiores = powerSetInferiores.stream()
				.filter(at->this.parteInferiorValida(at, clima))
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
		boolean soloUnaPrendaBase = aux.size() == 1;
		int aux2 = ps.stream().mapToInt(prenda -> prenda.getNivelAbrigo()).sum();
		boolean abrigaBien = this.EstaEnRango(aux2,clima);
		//nuevo para que no devuelva dos camperas o dos buzos
		boolean soloUnaCampera = ps.stream().filter(prenda->prenda.getTipo() == TipoPrenda.Campera).collect(Collectors.toSet()).size()<2;
		boolean soloUnTapado = ps.stream().filter(prenda->prenda.getTipo() == TipoPrenda.Tapado).collect(Collectors.toSet()).size()<2;
		boolean soloUnBuzo= ps.stream().filter(prenda->prenda.getTipo() == TipoPrenda.Buzo).collect(Collectors.toSet()).size()<2;

		return soloUnaPrendaBase && abrigaBien
				&& soloUnaCampera && soloUnTapado && soloUnBuzo;
	}
	private boolean EstaEnRango(int nivelAbrigo,ProveedorClima clima) {
		int nf =this.nivelFrio(clima.temperatura());
		return nivelAbrigo >= nf-1 && nivelAbrigo <= nf+1;
		//Aca establezco un rango, fijense definicion de nivel frio
		//Esto tenemos que charlarlo todos jeje
	}
	
	private boolean parteInferiorValida(Set<Prenda> ps,ProveedorClima clima) {
		return this.contienePrendasDeCategoria(ps, Categoria.INFERIOR)
				&& this.contienePrendasDeCategoria(ps, Categoria.CALZADO)
				&& this.abrigaCorrectamenteInferior(ps,clima);
	}
	private boolean abrigaCorrectamenteInferior(Set<Prenda> prenInf,ProveedorClima clima) {
		double temp = clima.temperatura();
		boolean tienePrenasVeraniegas = prenInf.stream().filter(pre->pre.esDeVerano()).collect(Collectors.toSet()).size()>0;
		return !(temp < 19 && tienePrenasVeraniegas);
	}
	private boolean contienePrendasDeCategoria(Set<Prenda> atuendo, Categoria unaCategoria) {
		Set<Prenda> aux;
		if (unaCategoria != Categoria.INFERIOR)
			return atuendo.stream().anyMatch(prenda->prenda.getTipo().categoria == unaCategoria); 
		
		aux = atuendo.stream().filter(prenda->prenda.getTipo().
				categoria == unaCategoria).collect(Collectors.toSet());
		return aux.size() == 1;//esto se traduce en que solo hay una prenda
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
		if (temperatura < 1)
			return 5;
		if (temperatura < 9)
			return 4;
		if (temperatura < 13)
			return 3;
		if (temperatura < 17)
			return 2;
		if (temperatura < 20)
			return 1; //irias con remera manga larga/corta
		else
			return -1;//irias solo con remera manga corta porque esta abriga 0
	}

}
