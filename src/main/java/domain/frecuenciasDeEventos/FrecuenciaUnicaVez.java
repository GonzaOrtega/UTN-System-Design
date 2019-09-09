package domain.frecuenciasDeEventos;
import java.time.*;

import domain.enums.TipoFrecuencia;

public class FrecuenciaUnicaVez implements FrecuenciaDeEvento{
	static int limiteDeProximidad = 7;
	LocalDateTime fechaEvento;
	public FrecuenciaUnicaVez(int anio,int mes,int dia) {
		fechaEvento = LocalDateTime.of(anio,mes,dia,0,0,0);
	}
	
	public FrecuenciaUnicaVez(LocalDateTime fechaEvento) {
		this.fechaEvento = fechaEvento;
	}
	
	public TipoFrecuencia getFrecuencia() {return TipoFrecuencia.Unico;}

	public boolean esProximo(LocalDateTime fechaActual) {
		Duration duracion = Duration.between(fechaActual,fechaEvento);
		return duracion.toDays()<=limiteDeProximidad && duracion.toDays()>=0;
	}
	
	public LocalDateTime cualEsLaFechaProxima(LocalDateTime fechaComienzo){
		return fechaEvento;
	}
	public boolean yaSucedio(LocalDateTime fechaActual){
		return fechaEvento.isBefore(fechaActual);
	}
}

