package domain.frecuenciasDeEventos;
import java.time.*;

public class FrecuenciaUnicaVez implements FrecuenciaDeEvento{
	static int limiteDeProximidad = 7;
	
	public boolean esProximo(LocalDateTime fechaEvento, LocalDateTime fechaActual) {
		Duration duracion = Duration.between(fechaActual,fechaEvento);
		return duracion.toDays()<=limiteDeProximidad && duracion.toDays()>=0;
	}
	public boolean sucedeEntreEstasfechas(LocalDateTime fechaComienzo, LocalDateTime fechaFin,LocalDateTime fechaEvento ) {
		long diasEntreComienzoEvento =(Duration.between(fechaComienzo,fechaEvento)).toDays();
		long diasEntreEventoFin =(Duration.between(fechaComienzo,fechaFin)).toDays();
		return diasEntreComienzoEvento<=diasEntreEventoFin;
		
	}
	
}

