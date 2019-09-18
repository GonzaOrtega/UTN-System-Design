package domain;

import java.util.*;
import java.time.*;
import domain.enums.*;
import domain.exceptions.*;
import java.util.stream.*;
import domain.SuperClase;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.CascadeType;

@Entity
public class Usuario extends SuperClase implements WithGlobalEntityManager{
	
	// ---------------------------- Atributos -------------------------------

	@Enumerated
	private TipoUsuario tipo;
	
	private int  maximoDePrendas;
	
	@ManyToMany (cascade = CascadeType.PERSIST)
	private Set<Guardarropa> guardarropas = new HashSet<Guardarropa>();
	
	@OneToMany (cascade = CascadeType.PERSIST)
	@JoinColumn(name="id_usuario")
	private Set<Sugerencia> sugerencias = new HashSet<Sugerencia>();
	
	// TODO: revisar
	@OneToOne
	private Sugerencia ultimaSugerencia = null;
	
	
	@ManyToMany (cascade = CascadeType.PERSIST)
	private Set<MedioDeNotificacion> medios = new HashSet<MedioDeNotificacion>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Evento> eventos = new HashSet<Evento>();
	
	@OneToMany (cascade = CascadeType.PERSIST) @JoinColumn(name="id_Usuario") 
	private List<Calificacion> calificaciones = new ArrayList<Calificacion>();
	
	// ------------------ Getters, setters y constructores ------------------
	
	private Usuario() {}
	
	public Usuario(TipoUsuario tipo, int maximoDePrendas) {
		this.tipo = tipo;
		this.maximoDePrendas = maximoDePrendas;
		RepositorioDeUsuarios.getInstance().agregar(this);
	}
	
	public Set<Evento> eventosProximos(LocalDateTime fecha) {
		
		return this.eventos().stream().filter(evento->evento.esProximo(fecha)&& !this.tengoSugerenciaDeEsteEvento(evento)).collect(Collectors.toSet());
	}

	public Set<Sugerencia> getSugerencias() {
		return sugerencias;
	}
	
	public Set<Guardarropa> getGuardarropas() {
		//Set<Guardarropa> guardarropasQ = entityManager().createQuery("Select u.Guardarropa from Usuario , Guardarropa.class).getResultList().stream().collect(Collectors.toSet());
		return guardarropas;
	}
	
	public List<Calificacion> getCalificaciones(){
		return this.calificaciones;
	}
	
	public TipoUsuario getTipo() {
		return tipo;
	}
	
	public Set<Evento> eventos() { 
/*		List<Evento> listaEventos = entityManager()
				.createQuery("from Evento order by Id", Evento.class)
				.getResultList();
		Set<Evento> setEventos = new HashSet<Evento>(listaEventos);
		return setEventos;*/
		return eventos;
		}
	
	public Set<Evento> eventosSugeridos(){
		return this.eventos().stream().filter(evento->this.tengoSugerenciaDeEsteEvento(evento) && !evento.yaSucedio()).collect(Collectors.toSet());
	}
	
	public Set<Set<Prenda>> atuendosSugeridosDe(Evento unEvento){
		return this.sugerencias.stream()
							   .filter(sugerencia->sugerencia.mismoEvento(unEvento))
							   .map(sugerencia->sugerencia.getAtuendo()).collect(Collectors.toSet());
	}
	
	private Set<Sugerencia> getSugerenciasConEventosFinalizados(){
		return sugerencias.stream().filter(sugerencia-> sugerencia.getEvento().yaSucedio()).collect(Collectors.toSet());
	}
	
	public Set<Evento> eventosConCambioDeClima(){
		return this.eventos.stream().filter(evento->this.atuendosNoSeEncuentranAptosPara(evento)).collect(Collectors.toSet());
	}
	
	// ------------------------------ Metodos -------------------------------

	public void validacionSegunTipoUsuario(int cantidadDePrendasDelGuardarropas) {
		if(tipo == TipoUsuario.GRATUITO && cantidadDePrendasDelGuardarropas >= maximoDePrendas) {
			throw new SeExcedioElLimiteDeCapacidadDelGuardarropaException("WARNNING: el guardarropa ya contiene el l�mite de su capacidad");
		}
	}
	
	public void calificar(Categoria parteCuerpo,TipoSensaciones sensacion) {
		this.calificaciones.add(new Calificacion(parteCuerpo,sensacion));
	}

	public boolean tengoSugerenciaDeEsteEvento(Evento evento) {
		return sugerencias.stream().anyMatch(sugerencia->sugerencia.getEvento().equals(evento));
	}
	
	public void agregarGuardarropa(Guardarropa unGuardarropa) {
		this.validacionSegunTipoUsuario(unGuardarropa.prendas().size()-1); 
		this.guardarropas.add(unGuardarropa);
	}
	
	public void cargarPrenda(Guardarropa unGuardarropa,Prenda unaPrenda) {
		if(this.yaSeCargoLaPrenda(unaPrenda)) {
			throw new YaSeEncuentraCargadaException("WARNING: la prenda ingresada ya se encuentra cargada");
		}
		this.validacionSegunTipoUsuario(unGuardarropa.prendas().size());		
		unGuardarropa.cargarPrenda(unaPrenda);
	}

	public boolean yaSeCargoLaPrenda(Prenda unaPrenda) {
		return guardarropas.stream().anyMatch(guardarropa->guardarropa.prendas().contains(unaPrenda));
	}
		
	public void agregarSugerencia(Sugerencia sugerenciaNueva){
		sugerencias.add(sugerenciaNueva);
	}
	
	public void clasificarUnaSugerencia(Sugerencia sugerencia, TipoSugerencias tipo) {
		if (!sugerencias.contains(sugerencia)){
			throw new NoPoseeLaSugerenciaException("WARNING: No posee la sugerencia que se esta intentando clasificar");
		}
		if(tipo.equals(TipoSugerencias.ACEPTADA)) {
			sugerencia.getAtuendo().forEach(prenda -> prenda.setUsada(true));
		}
			ultimaSugerencia =sugerencia;
			sugerencia.setEstado(tipo);
	}
	
	public boolean atuendosNoSeEncuentranAptosPara(Evento unEvento) {
		Set<Set<Prenda>> atuendosDelEvento = this.atuendosSugeridosDe(unEvento);
		return atuendosDelEvento.stream().allMatch(atuendo->!unEvento.obtenerAtuendos(this).contains(atuendo));
	}
	
	public void deshacerUltimaOperacionDeSugerencia() {
		if (ultimaSugerencia != null) {
			ultimaSugerencia.getAtuendo().forEach(prenda -> prenda.setUsada(false));
			ultimaSugerencia.setEstado(TipoSugerencias.PENDIENTE);
		}
	}
	
	public void agendarEvento(Evento unEvento) {
		this.eventos.add(unEvento);
	}
	
	public void desagendarEvento(Evento unEvento) {
		this.eventos = eventos.stream().filter(evento -> !evento.equals(unEvento)).collect(Collectors.toSet());
	}
	
	public void lavarLaRopa() {
		Set<Sugerencia> sugerenciasConEventosFinalizados = this.getSugerenciasConEventosFinalizados();
		sugerenciasConEventosFinalizados.forEach(sugerencia -> sugerencia.setPrendasComoNoUsadas());
	}
	
	public void notificarSugerenciasNuevas() {
		this.medios.stream().forEach(medio->medio.notificarNuevasSugerencias(this));
	}
	
	public void notificarAlertaMeterologicaDe(Evento unEvento) {
		System.out.println("Usuario "+this+" afectado por cambio de temperatura.");
		this.medios.stream().forEach(medio->medio.notificarAlertaMeterologica(unEvento,this));
	}
	
}