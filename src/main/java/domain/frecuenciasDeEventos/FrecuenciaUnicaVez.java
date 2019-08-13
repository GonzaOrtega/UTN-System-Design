package domain.frecuenciasDeEventos;
import java.time.*;

import domain.enums.TipoFrecuencia;

public class FrecuenciaUnicaVez implements FrecuenciaDeEvento{
	static int limiteDeProximidad = 7;
	LocalDateTime fechaEvento;
	public FrecuenciaUnicaVez(int anio,int mes,int dia) {
		fechaEvento = LocalDateTime.of(anio,mes,dia,0,0,0);
	}
	
	public TipoFrecuencia getFrecuencia() {return TipoFrecuencia.Unico;}

	public boolean esProximo(LocalDateTime fechaActual) {
		Duration duracion = Duration.between(fechaActual,fechaEvento);
		return duracion.toDays()<=limiteDeProximidad && duracion.toDays()>=0;
	}
	public boolean sucedeEntreEstasFechas(LocalDateTime fechaComienzo, LocalDateTime fechaFin) {
		long diasEntreComienzoEvento =(Duration.between(fechaComienzo,fechaEvento)).toDays();
		long diasEntreEventoFin =(Duration.between(fechaComienzo,fechaFin)).toDays();
		return diasEntreComienzoEvento<=diasEntreEventoFin;
	}
	public LocalDateTime cualEsLaFechaProxima(LocalDateTime fechaComienzo){
		return fechaEvento;
	}
	
}

