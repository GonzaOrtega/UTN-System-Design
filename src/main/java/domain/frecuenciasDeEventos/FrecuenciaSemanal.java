package domain.frecuenciasDeEventos;
import java.time.*;

import domain.enums.TipoFrecuencia;
public class FrecuenciaSemanal implements FrecuenciaDeEvento{
	private int limiteDeProximidad = 2;
	private int diaDeLaSemana;
	
	public FrecuenciaSemanal(int diaDeLaSemana) {
		this.diaDeLaSemana= diaDeLaSemana;
	}
	public TipoFrecuencia getFrecuencia() {return TipoFrecuencia.Semanal;}

	
	public boolean esProximo(LocalDateTime fechaActual) {
		return this.diferenciaEntreDosDias(diaDeLaSemana,fechaActual.getDayOfWeek().getValue())<=limiteDeProximidad;
	}
	public long diferenciaEntreDosDias(int fechaFin,int fechaComienzo) {
		int dias= fechaFin-fechaComienzo;
		if (fechaFin<fechaComienzo){ 
			dias+=7;
		};
		return dias;
	}
	
	public boolean sucedeEntreEstasFechas(LocalDateTime fechaComienzo, LocalDateTime fechaFin) {
		long diasEntreComienzoEvento =this.diferenciaEntreDosDias(diaDeLaSemana,fechaComienzo.getDayOfWeek().getValue());
		long diasEntreComienzoFin =this.diferenciaEntreDosDias(fechaFin.getDayOfWeek().getValue(),fechaComienzo.getDayOfWeek().getValue());
		return diasEntreComienzoEvento<=diasEntreComienzoFin;
	}
	public LocalDateTime cualEsLaFechaProxima(LocalDateTime fechaComienzo){
		long diasEntreComienzoEvento =this.diferenciaEntreDosDias(diaDeLaSemana,fechaComienzo.getDayOfWeek().getValue());
		return fechaComienzo.plusDays(diasEntreComienzoEvento);
	}
}
