package domain.frecuenciasDeEventos;
import java.time.*;
public class FrecuenciaSemanal implements FrecuenciaDeEvento{
	private int limiteDeProximidad = 2;
	
	public boolean esProximo(LocalDateTime fechaEvento, LocalDateTime fechaActual) {
		Duration duracion = Duration.between(fechaActual,fechaEvento);
		long dias= duracion.toDays()%7;
		if (dias<0){ 
			dias+=7;
		};
		return dias<=limiteDeProximidad;
	}
}
