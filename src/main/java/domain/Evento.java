package domain;
import java.time.*;
import java.util.Date;
import java.util.Set;

import org.uqbar.commons.model.annotations.Observable;

import domain.frecuenciasDeEventos.*;

@Observable
public class Evento {
	private LocalDateTime fecha;
	private Sugeridor sugeridor;
	private FrecuenciaDeEvento frecuencia;
	//private int contador=0;// usado solo de forma provisoria para el JobsEventosTest
	
	public Evento(LocalDateTime unaFecha,Sugeridor unSugeridor, FrecuenciaDeEvento unaFrecuencia) {
		this.fecha=unaFecha;
		this.sugeridor=unSugeridor;
		this.frecuencia = unaFrecuencia;
	}

	public void sugerir(Usuario usuario) {
		Set<Set<Prenda>> atuendos = sugeridor.sugerirPrendasPara(usuario);
		atuendos.forEach(atuendo->usuario.agregarSugerencia(new Sugerencia(atuendo,this)));
		//this.setContador(contador+1);//Usado en forma provisoria para el JobsEventosTest
	}
	
	public boolean esProximo(LocalDateTime fechaActual) {
		return frecuencia.esProximo(this.fecha, fechaActual);
	}
	
	public boolean alertaMeterologica() {return sugeridor.proveedorDeClima().alertaMeterologica();}
	
	public LocalDateTime getFecha() {
		return fecha;
	}
	public boolean sucedeEntreEstasfechas(LocalDateTime fechaComienzo,LocalDateTime fechaFin) {
		return frecuencia.sucedeEntreEstasfechas(fechaComienzo,fechaFin,this.fecha);
	}
	
}
