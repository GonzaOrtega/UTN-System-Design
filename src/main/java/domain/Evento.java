package domain;
import java.time.*;
import java.util.Date;
import java.util.Set;
import domain.enums.*;
import org.uqbar.commons.model.annotations.Observable;

import domain.frecuenciasDeEventos.*;

@Observable
public class Evento {
	private LocalDateTime fecha;
	private Sugeridor sugeridor;
	private FrecuenciaDeEvento frecuencia;
	private String descripcion;
	private SugerenciasListas estadoSugerencias;
	//private int contador=0;// usado solo de forma provisoria para el JobsEventosTest
	
	public Evento(LocalDateTime unaFecha,Sugeridor unSugeridor, FrecuenciaDeEvento unaFrecuencia, String unaDescripcion) {
		this.fecha=unaFecha;
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
		if(this.esProximo(LocalDateTime.now()))
			return SugerenciasListas.YES;
		else return SugerenciasListas.NO;
		
	}
	
	public boolean esProximo(LocalDateTime fechaActual) {
		return frecuencia.esProximo(this.fecha, fechaActual);
	}
	
	public boolean alertaMeterologica() {return sugeridor.proveedorDeClima().alertaMeterologica();}
	
	public boolean sucedeEntreEstasfechas(LocalDateTime fechaComienzo,LocalDateTime fechaFin) {
		return frecuencia.sucedeEntreEstasfechas(fechaComienzo,fechaFin,fecha);
	}
	
	public LocalDate getFecha() {
		return LocalDate.of(fecha.getYear(), fecha.getMonth(), fecha.getDayOfMonth());
	}

	
	public TipoFrecuencia getFrecuencia() {
		return frecuencia.getFrecuencia();
	}
	
}
