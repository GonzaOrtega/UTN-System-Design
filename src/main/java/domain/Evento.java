package domain;
import java.time.*;
import java.util.Date;
import java.util.Set;
import domain.enums.*;
import org.uqbar.commons.model.annotations.Observable;

import domain.frecuenciasDeEventos.*;

@Observable
public class Evento {
	private Sugeridor sugeridor;
	private FrecuenciaDeEvento frecuencia;
	private String descripcion;
	private SugerenciasListas estadoSugerencias;
	private LocalDateTime fechaInicio;
	//private int contador=0;// usado solo de forma provisoria para el JobsEventosTest
	
	public Evento(Sugeridor unSugeridor, FrecuenciaDeEvento unaFrecuencia, String unaDescripcion) {
		this.sugeridor=unSugeridor;
		this.frecuencia = unaFrecuencia;
		this.descripcion= unaDescripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void sugerir(Usuario usuario) {
		Set<Set<Prenda>> atuendos = sugeridor.sugerirPrendasPara(usuario);
		atuendos.forEach(atuendo->usuario.agregarSugerencia(new Sugerencia(atuendo,this)));
		//this.setContador(contador+1);//Usado en forma provisoria para el JobsEventosTest
	}
	public SugerenciasListas getSugerenciasListas() {
		if(this.esProximo(LocalDateTime.now()) || this.yaSucedio())
			return SugerenciasListas.YES;
		else return SugerenciasListas.NO;
		
	}
	public boolean yaSucedio(){
		return LocalDateTime.now().isAfter(this.getFecha());
	}
	
	public boolean esProximo(LocalDateTime fechaActual) {
		return frecuencia.esProximo(fechaActual);
	}
	
	public boolean alertaMeterologicaPara(LocalDateTime fecha) {return sugeridor.proveedorDeClima().alertaMeterologica() && this.esProximo(fecha); }
	
	public boolean sucedeEntreEstasfechas(LocalDateTime fechaComienzo,LocalDateTime fechaFin) {
		return frecuencia.sucedeEntreEstasFechas(fechaComienzo,fechaFin);
	}
	public void setFecha(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDateTime getFecha() {
		return frecuencia.cualEsLaFechaProxima(fechaInicio);
	}

	
	public TipoFrecuencia getFrecuencia() {
		return frecuencia.getFrecuencia();
	}
	
}
