package domain.frecuenciasDeEventos;
import java.time.*;

public class FrecuenciaUnicaVez implements FrecuenciaDeEvento{
	static int limiteDeProximidad = 7;
	
	public boolean esProximo(LocalDateTime fechaEvento, LocalDateTime fechaActual) {
		Duration duracion = Duration.between(fechaActual,fechaEvento);
		return duracion.toDays()<=limiteDeProximidad && duracion.toDays()>=0;
	}
}

