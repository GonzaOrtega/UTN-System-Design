package domain.frecuenciasDeEventos;
import java.time.*;

public class FrecuenciaMensual implements FrecuenciaDeEvento{
	private int limiteDeProximidad = 5;
	public boolean esProximo(LocalDateTime fechaEvento, LocalDateTime fechaActual) {
		
		return this.diferenciaEnDiasEntreDosFechas(fechaEvento,fechaActual)<=limiteDeProximidad;
	}
	public long diferenciaEnDiasEntreDosFechas(LocalDateTime fechaFin,LocalDateTime fechaComienzo) {
		int dias= fechaFin.getDayOfMonth()-fechaComienzo.getDayOfMonth();
		if (fechaFin.getDayOfMonth()<fechaComienzo.getDayOfMonth()){ 
			dias+=30 ;
		};
		return dias;
	}
	
	public boolean sucedeEntreEstasfechas(LocalDateTime fechaComienzo, LocalDateTime fechaFin,LocalDateTime fechaEvento ) {
		long diasEntreComienzoEvento =this.diferenciaEnDiasEntreDosFechas(fechaEvento,fechaComienzo);
		long diasEntreComienzoFin =this.diferenciaEnDiasEntreDosFechas(fechaFin,fechaComienzo);
		return diasEntreComienzoEvento<=diasEntreComienzoFin;
		
	}
}
