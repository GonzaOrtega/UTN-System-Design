package domain.frecuenciasDeEventos;
import java.time.*;
public class FrecuenciaDiaria implements FrecuenciaDeEvento{
	private int limiteDeProximidad = 8;
	
	public boolean esProximo(LocalDateTime fechaEvento, LocalDateTime fechaActual) {
		return this.diferenciaEnHorasEntreDosFechas(fechaEvento, fechaActual)<=limiteDeProximidad;
	}
	public int diferenciaEnHorasEntreDosFechas(LocalDateTime fechaEvento, LocalDateTime fechaActual) {
		int horas= fechaEvento.getHour()-fechaActual.getHour();
		if (fechaEvento.getHour()<fechaActual.getHour()){ 
			horas+=24;
		};
		return horas;
	}
	public boolean sucedeEntreEstasfechas(LocalDateTime fechaComienzo, LocalDateTime fechaFin,LocalDateTime fechaEvento ) {
		int horasEntreComienzoEvento =this.diferenciaEnHorasEntreDosFechas(fechaEvento,fechaComienzo);
		int horasEntreComienzoFin =this.diferenciaEnHorasEntreDosFechas(fechaFin,fechaComienzo);
		return horasEntreComienzoEvento<=horasEntreComienzoFin;
		
	}
}