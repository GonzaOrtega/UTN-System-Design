package domain.frecuenciasDeEventos;
import java.time.*;

import domain.enums.TipoFrecuencia;

public class FrecuenciaMensual implements FrecuenciaDeEvento{
	private int limiteDeProximidad = 5;
	int diaDelMes;
	public FrecuenciaMensual(int dia) {
		this.diaDelMes = dia;
	}
	public TipoFrecuencia getFrecuencia() {return TipoFrecuencia.Mensual;}
	public boolean esProximo(LocalDateTime fechaActual) {
		return this.diferenciaEntreDosDias(diaDelMes,fechaActual.getDayOfMonth())<=limiteDeProximidad;
	}
	public long diferenciaEntreDosDias(int diaFin,int diaComienzo) {
		int dias= diaFin-diaComienzo;
		if (diaFin<diaComienzo){ 
			dias+=30 ;
		};
		return dias;
	}
	
	public boolean sucedeEntreEstasFechas(LocalDateTime fechaComienzo, LocalDateTime fechaFin) {
		long diasEntreComienzoEvento =this.diferenciaEntreDosDias(diaDelMes,fechaComienzo.getDayOfMonth());
		long diasEntreComienzoFin =this.diferenciaEntreDosDias(fechaFin.getDayOfMonth(),fechaComienzo.getDayOfMonth());
		return diasEntreComienzoEvento<=diasEntreComienzoFin;		
	}
	public LocalDateTime cualEsLaFechaProxima(LocalDateTime fechaComienzo){
		long diasEntreComienzoEvento =this.diferenciaEntreDosDias(diaDelMes,fechaComienzo.getDayOfMonth());
		return fechaComienzo.plusDays(diasEntreComienzoEvento);
	}
}
