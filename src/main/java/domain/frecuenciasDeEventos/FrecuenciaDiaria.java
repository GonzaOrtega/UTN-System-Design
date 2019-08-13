package domain.frecuenciasDeEventos;
import java.time.*;

import domain.enums.TipoFrecuencia;
public class FrecuenciaDiaria implements FrecuenciaDeEvento{
	private int limiteDeProximidad = 8;
	int hora;
	public FrecuenciaDiaria(int hora) {
		this.hora=hora;
	}
	public TipoFrecuencia getFrecuencia() {return TipoFrecuencia.Diario;}
	
	public boolean esProximo(LocalDateTime fechaActual) {
		return this.diferenciaEnHorasEntreDosHoras(hora,fechaActual.getHour())<=limiteDeProximidad;
	}
	public int diferenciaEnHorasEntreDosHoras(int horaFinal, int horaComienzo) {
		int horas= horaFinal-horaComienzo;
		if (horaFinal<horaComienzo){ 
			horas+=24;
		};
		return horas;
	}
	public boolean sucedeEntreEstasFechas(LocalDateTime fechaComienzo, LocalDateTime fechaFin) {
		long horasEntreComienzoEvento =this.diferenciaEnHorasEntreDosHoras(hora,fechaComienzo.getHour());
		
		return fechaComienzo.plusHours(horasEntreComienzoEvento).isBefore(fechaFin) || fechaComienzo.plusHours(horasEntreComienzoEvento).isEqual(fechaFin);
	}
	public LocalDateTime cualEsLaFechaProxima(LocalDateTime fechaComienzo){
		int horasEntreComienzoEvento =this.diferenciaEnHorasEntreDosHoras(hora,fechaComienzo.getHour());
		return fechaComienzo.plusHours(horasEntreComienzoEvento);
	}
}