package domain.frecuenciasDeEventos;
import java.time.*;
public class FrecuenciaSemanal implements FrecuenciaDeEvento{
	private int limiteDeProximidad = 2;
	
	public boolean esProximo(LocalDateTime fechaEvento, LocalDateTime fechaActual) {
		
		return this.diferenciaEnDiasEntreDosFechas(fechaEvento,fechaActual)<=limiteDeProximidad;
	}
	public long diferenciaEnDiasEntreDosFechas(LocalDateTime fechaFin,LocalDateTime fechaComienzo) {
		Duration duracion = Duration.between(fechaComienzo,fechaFin);
		long dias= duracion.toDays()%7;
		if (dias<0){ 
			dias+=7;
		};
		return dias;
	}
	
	public boolean sucedeEntreEstasfechas(LocalDateTime fechaComienzo, LocalDateTime fechaFin,LocalDateTime fechaEvento ) {
		long diasEntreComienzoEvento =this.diferenciaEnDiasEntreDosFechas(fechaEvento,fechaComienzo);
		long diasEntreComienzoFin =this.diferenciaEnDiasEntreDosFechas(fechaFin,fechaComienzo);
		return diasEntreComienzoEvento<=diasEntreComienzoFin;
		
	}
}
