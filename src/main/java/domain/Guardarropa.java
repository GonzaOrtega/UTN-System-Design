package domain;
import java.util.*;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import com.google.common.collect.*;
import domain.apisClima.*;
import domain.enums.*;

@Entity
public class Guardarropa {

	@Id @GeneratedValue
  	private Long id;

	@OneToMany(cascade = CascadeType.PERSIST)@JoinColumn(name="id_Guardarropa")
	private Set<Prenda> prendas = new HashSet<Prenda>();
	
	public Set<Prenda> prendas(){return prendas;}
	
	public void cargarPrenda(Prenda unaPrenda){
		prendas.add(unaPrenda);
	}
	
	//TODO Borrar codigo muerto
	public List<Set<Prenda>> pedirAtuendosSegun(ProveedorClima proveedor,Usuario unUser){
		Set<Set<Prenda>> elAux = new HashSet<Set<Prenda>>();
		elAux = this.parteNoSuperior(proveedor,unUser); //esto esta asi por un tema de Set y List
		ArrayList<Set<Prenda>> atuendosInferior = new ArrayList<Set<Prenda>>(elAux);
//		System.out.println(atuendosInferior);
		Set<Set<Prenda>> atuendoSup = parteSuperior(proveedor,unUser);
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
		return this.prendasNoUsadas().stream().filter(p->p.getTipo().categoria != 
				Categoria.SUPERIOR).collect(Collectors.toSet());
	}

	private Set<Set<Prenda>> parteNoSuperior(ProveedorClima clima,Usuario user){
		Set<Prenda> partesInferiores = this.noSuperior();
		Set<Set<Prenda>> powerSetInferiores = Sets.powerSet(partesInferiores); 
		powerSetInferiores = powerSetInferiores.stream()
				.filter(at->this.parteInferiorValida(at, clima,user))
				.collect(Collectors.toSet());
		return powerSetInferiores;
	}
	private Set<Set<Prenda>> parteSuperior(ProveedorClima clima,Usuario unUsuario){
		Set<Prenda> partesSuperiores = this.soloSuperior();
		Set<Set<Prenda>> powerSetSuperiores = Sets.powerSet(partesSuperiores); 
		powerSetSuperiores = powerSetSuperiores.stream()
				.filter(at->this.parteSuperiorValida(at,clima,unUsuario))
				.collect(Collectors.toSet());
//		System.out.println("PowerSet: ");
//		System.out.println(powerSetSuperiores); //Lo dejo para hacer pruebas despues
		return powerSetSuperiores;
		
	}
	
	private Set<Prenda> soloSuperior(){
		Set<Prenda> ret = this.prendasNoUsadas().stream().filter(p->p.getTipo().categoria == 
				Categoria.SUPERIOR).collect(Collectors.toSet());
		return ret;
	}
	private boolean parteSuperiorValida(Set<Prenda> ps, ProveedorClima clima,Usuario unUser) {
		Set<Prenda> aux = ps.stream().filter(prenda->prenda.getEsBase()).collect(Collectors.toSet());
		boolean soloUnaPrendaBase = aux.size() == 1;
		int aux2 = ps.stream().mapToInt(prenda -> prenda.getNivelAbrigo()).sum();
		boolean abrigaBien = this.EstaEnRango(aux2,clima,unUser,Categoria.SUPERIOR);
		//nuevo para que no devuelva dos camperas o dos buzos
		boolean soloUnaCampera = ps.stream().filter(prenda->prenda.getTipo() == TipoPrenda.Campera).collect(Collectors.toSet()).size()<2;
		boolean soloUnTapado = ps.stream().filter(prenda->prenda.getTipo() == TipoPrenda.Tapado).collect(Collectors.toSet()).size()<2;
		boolean soloUnBuzo= ps.stream().filter(prenda->prenda.getTipo() == TipoPrenda.Buzo).collect(Collectors.toSet()).size()<2;

		return soloUnaPrendaBase && abrigaBien
				&& soloUnaCampera && soloUnTapado && soloUnBuzo;
	}

	public boolean EstaEnRango(int nivelAbrigo,ProveedorClima clima,Usuario unUsuario,Categoria cat) {
		int nf =this.nivelFrio(clima.temperatura());
		nf += calificacionUsuario(unUsuario,cat);//Nuevo, si tiene frio en el pechito(como ya saben quien...)
		//Aumenta o disminuye este rango.
		return nivelAbrigo >= nf-1 && nivelAbrigo <= nf+2;
		//Aca establezco un rango, fijense definicion de nivel frio
	}
	
	private boolean parteInferiorValida(Set<Prenda> ps,ProveedorClima clima,Usuario user) {
		return this.contienePrendasDeCategoria(ps, Categoria.INFERIOR)
				&& this.contienePrendasDeCategoria(ps, Categoria.CALZADO)
				&& this.abrigaCorrectamenteInferior(ps,clima,user);
	}
	private boolean abrigaCorrectamenteInferior(Set<Prenda> prenInf,ProveedorClima clima,Usuario user) {
		boolean abriganBien = prenInf.stream().
				filter(pr->EstaEnRango(pr.getNivelAbrigo(),clima,user,pr.getTipo().categoria)).
				collect(Collectors.toSet()).size()>1;
		//Como prendas inferiores solo puede haber dos, esto se fija si las dos
				//Abrigan bien a la persona
		return abriganBien;
	}
	
	private boolean contienePrendasDeCategoria(Set<Prenda> atuendo, Categoria unaCategoria) {
		Set<Prenda> aux;
		if (unaCategoria != Categoria.INFERIOR)
			return atuendo.stream().anyMatch(prenda->prenda.getTipo().categoria == unaCategoria); 
		
		aux = atuendo.stream().filter(prenda->prenda.getTipo().
				categoria == unaCategoria).collect(Collectors.toSet());
		return aux.size() == 1;//esto se traduce en que solo hay una prenda
	}
	/*
	private boolean contienePrendasDeTodasLasCategorias(Set<Prenda> atuendo) {
		return  this.contienePrendasDeCategoria(atuendo, Categoria.INFERIOR)
				&& this.contienePrendasDeCategoria(atuendo, Categoria.ACCESORIO)
				&& this.contienePrendasDeCategoria(atuendo, Categoria.CALZADO);
		//&& this.contienePrendasDeCategoria(atuendo, Categoria.SUPERIOR)
	}*/
	
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
	
	public Set<Prenda> prendasNoUsadas(){
		return prendas.stream().filter(prenda -> !prenda.isUsada()).collect(Collectors.toSet());
	}
	private int calificacionUsuario(Usuario user,Categoria cat)
	{
		ArrayList<Calificacion> califUser = user.getCalificaciones();
		List<TipoSensaciones> sensaciones = califUser.stream().
				filter(calif->calif.getCategoria() == cat).
				map(calif->calif.getSensacion()).collect(Collectors.toList());
		int nivelFriolento = sensaciones.stream().filter(sensa->sensa == TipoSensaciones.FRIO)
		.collect(Collectors.toList()).size();
		int nivelCaluroso = sensaciones.stream().filter(sensa->sensa == TipoSensaciones.CALOR)
				.collect(Collectors.toList()).size();
		return nivelFriolento - nivelCaluroso;
	}
	public Long getId() {
		return id;
	}
}
