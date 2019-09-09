package domain;

import java.util.*;
import java.time.*;
import domain.enums.*;
import domain.exceptions.*;
import java.util.stream.*;


public class Usuario {
	private TipoUsuario tipo;
	private int  maximoDePrendas;
	private HashSet<Guardarropa> guardarropas = new HashSet<Guardarropa>();
	private Set<Sugerencia> sugerencias = new HashSet<Sugerencia>();
	private Sugerencia ultimaSugerencia = null;
	private Set<MedioDeNotifiacion> medios = new HashSet<MedioDeNotifiacion>();
	private Set<Evento> eventos = new HashSet<Evento>();
	private ArrayList<Calificacion> calificaciones = new ArrayList<Calificacion>();
	
	public Usuario(TipoUsuario tipo, int maximoDePrendas) {
		this.tipo = tipo;
		this.maximoDePrendas = maximoDePrendas;
		RepositorioDeUsuarios.getInstance().agregar(this);
	}
	
	public Set<Evento> eventos() { return eventos; }

	
	public Set<Evento> eventosProximos(LocalDateTime fecha) {
		
		return this.eventos().stream().filter(evento->evento.esProximo(fecha)&& !this.tengoSugerenciaDeEsteEvento(evento)).collect(Collectors.toSet());
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
	
	public HashSet<Guardarropa> getGuardarropas() {
		return this.guardarropas;
	}
	
	public ArrayList<Calificacion> getCalificaciones(){
		return this.calificaciones;
	}
	
	public TipoUsuario getTipo() {
		return tipo;
	}
	
	//Recibe las nuevas sugerencias
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
	
	public void deshacerUltimaOperacionDeSugerencia() {
		if (ultimaSugerencia != null) {
			ultimaSugerencia.getAtuendo().forEach(prenda -> prenda.setUsada(false));
			ultimaSugerencia.setEstado(TipoSugerencias.PENDIENTE);
		}
	}
	
	public Set<Sugerencia> getSugerencias() {
		return sugerencias;
	}

	public void validacionSegunTipoUsuario(int cantidadDePrendasDelGuardarropas) {
		if(tipo == TipoUsuario.GRATUITO && cantidadDePrendasDelGuardarropas >= maximoDePrendas) {
			throw new SeExcedioElLimiteDeCapacidadDelGuardarropaException("WARNNING: el guardarropa ya contiene el l�mite de su capacidad");
		}
	}
	
	public void notificarSugerenciasNuevas() {
		this.medios.stream().forEach(medio->medio.notificarNuevasSugerencias(this));
	}
	
	public void notificarAlertaMeterologicaDe(Evento unEvento) {
		System.out.println("Usuario "+this+" afectado por cambio de temperatura.");
		this.medios.stream().forEach(medio->medio.notificarAlertaMeterologica(unEvento,this));
	}
	
	public void agendarEvento(Evento unEvento) {
		this.eventos.add(unEvento);
	}
	
	public void calificar(Categoria parteCuerpo,TipoSensaciones sensacion) {
		this.calificaciones.add(new Calificacion(parteCuerpo,sensacion));
	}
	
	// Alertas meterologicas
	public Set<Evento> eventosSugeridos(){
		return this.eventos().stream().filter(evento->this.tengoSugerenciaDeEsteEvento(evento) && !evento.yaSucedio()).collect(Collectors.toSet());
	}
	
	public Set<Set<Prenda>> atuendosSugeridosDe(Evento unEvento){
		return this.sugerencias.stream()
							   .filter(sugerencia->sugerencia.mismoEvento(unEvento) /*&& sugerencia.aceptada()*/)
							   .map(sugerencia->sugerencia.getAtuendo()).collect(Collectors.toSet());
	}
	
	public Set<Evento> eventosConCambioDeClima(){
		return this.eventos.stream().filter(evento->this.atuendosNoSeEncuentranAptosPara(evento)).collect(Collectors.toSet());
	}
	
	public boolean atuendosNoSeEncuentranAptosPara(Evento unEvento) {
		Set<Set<Prenda>> atuendosDelEvento = this.atuendosSugeridosDe(unEvento);
		return atuendosDelEvento.stream().allMatch(atuendo->!unEvento.obtenerAtuendos(this).contains(atuendo));
	}
	
	public void lavarLaRopa() {
		Set<Sugerencia> sugerenciasConEventosFinalizados = this.getSugerenciasConEventosFinalizados();
		sugerenciasConEventosFinalizados.forEach(sugerencia -> sugerencia.setPrendasComoNoUsadas());
	}
	
	private Set<Sugerencia> getSugerenciasConEventosFinalizados(){
		return sugerencias.stream()
				.filter(sugerencia-> sugerencia.getEvento().yaSucedio()).collect(Collectors.toSet());
	}
	
}