package domain;
import java.time.*;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import domain.enums.*;
import org.uqbar.commons.model.annotations.Observable;
import domain.frecuenciasDeEventos.*;

@Observable
@Entity@Table(name="Calendario")
public class Evento {
	@Id @GeneratedValue
  	private Long id;
	@Transient
	private String descripcion;
	@Transient
	private Sugeridor sugeridor;
	@Transient
	private FrecuenciaDeEvento frecuencia;
	@Transient
	private SugerenciasListas estadoSugerencias;////////////estoEsSoloParaArena
	@Transient
	private LocalDateTime fechaInicio;//////////////////estoEsSoloParaArena
	
	public Evento() {}///////////////////Solo para la Persistencia
	//private int contador=0;// usado solo de forma provisoria para el JobsEventosTest

	public Evento(Sugeridor unSugeridor, FrecuenciaDeEvento unaFrecuencia, String unaDescripcion) {
		this.sugeridor=unSugeridor;
		this.frecuencia = unaFrecuencia;
		this.descripcion= unaDescripcion;
	}	
	
	
	public Set<Set<Prenda>> obtenerAtuendos(Usuario usuario){
		return sugeridor.sugerirPrendasPara(usuario);
	}
	
	public void sugerir(Usuario usuario) {
		this.obtenerAtuendos(usuario).forEach(atuendo->usuario.agregarSugerencia(new Sugerencia(atuendo,this)));
		//this.setContador(contador+1);//Usado en forma provisoria para el JobsEventosTest
	}
	public boolean yaSucedio(){
		return frecuencia.yaSucedio(LocalDateTime.now());
	}
	
	public boolean esProximo(LocalDateTime fechaActual) {
		return frecuencia.esProximo(fechaActual);
	}
		
	public boolean sucedeEntreEstasfechas(LocalDateTime fechaComienzo,LocalDateTime fechaFin) {
		return frecuencia.sucedeEntreEstasFechas(fechaComienzo,fechaFin);
	}
	
	//////////////////////////////////////GETS_Y_SETS/////////////////////////////////////////////////	
	public TipoFrecuencia getFrecuencia() {
		return frecuencia.getFrecuencia();
	}
	
	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDateTime getFecha(){
		return frecuencia.cualEsLaFechaProxima(fechaInicio);
	}
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public SugerenciasListas getSugerenciasListas() { //Es para Arena te tira si te tiro sugerencias, en caso de ser posibles.
		if(this.esProximo(LocalDateTime.now()) || this.yaSucedio())
			return SugerenciasListas.YES;
		else return SugerenciasListas.NO;
	}
}
