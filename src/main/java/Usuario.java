import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.*;
import enums.TipoSugerencias;
import enums.TipoUsuario;
import java.util.HashSet;
import exceptions.*;
import enums.Categoria;
import enums.TipoSensaciones;

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
		//RepositorioDeUsuarios.getInstance().agregar(this);
	}
	
	
	public Set<Evento> eventos() { return eventos; }


	public Set<Evento> eventosProximos(LocalDateTime fecha) {
		return this.eventos().stream().filter(evento->evento.esProximo(fecha)).collect(Collectors.toSet());
	}
	
	public Set<Evento> eventosProximosConAlertaMeterologica(LocalDateTime fecha){
		return this.eventosProximos(fecha)
										.stream()
										.filter(evento -> evento.alertaMeterologica())
										.collect(Collectors.toSet());
	}
	
	public void agregarGuardarropa(Guardarropa unGuardarropa) {
		this.validacionSegunTipoUsuario(unGuardarropa.prendas.size()-1); 
		this.guardarropas.add(unGuardarropa);
	}
	
	public void cargarPrenda(Guardarropa unGuardarropa,Prenda unaPrenda) {
		if(this.yaSeCargoLaPrenda(unaPrenda)) {
			throw new YaSeEncuentraCargadaException("WARNING: la prenda ingresada ya se encuentra cargada");
		}
		this.validacionSegunTipoUsuario(unGuardarropa.prendas.size());		
		unGuardarropa.cargarPrenda(unaPrenda);
	}

	public boolean yaSeCargoLaPrenda(Prenda unaPrenda) {
		return guardarropas.stream().anyMatch(guardarropa->guardarropa.prendas.contains(unaPrenda));
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
		if (ultimaSugerencia != null) ultimaSugerencia
										.setEstado(TipoSugerencias.PENDIENTE);
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
		this.medios.stream().forEach(medio->medio.notificarNuevasSugerencias());
		System.out.println("NUEVAS SUGERENCIAS!!!");
	}
	
	public void notificarAlertaMeterologica() {
		this.medios.stream().forEach(medio->medio.notificarAlertaMeterologica());
		System.out.println("ALERTA METEROLOGICA!!!");
	}
	
	public void agendarEvento(Evento unEvento) {
		this.eventos.add(unEvento);
	}
	public void calificar(Categoria parteCuerpo,TipoSensaciones sensacion) {
		this.calificaciones.add(new Calificacion(parteCuerpo,sensacion));
	}
}
