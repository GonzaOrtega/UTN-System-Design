package domain.frecuenciasDeEventos;
import java.util.Date;
import java.time.*;

public class FrecuenciaUnicaVez implements FrecuenciaDeEvento{
	static int limiteDeProximidad = 7;
	
	public boolean esProximo(LocalDateTime fechaEvento, LocalDateTime fechaActual) {
		int dias = fechaEvento.getDayOfYear()-fechaActual.getDayOfYear();
		return dias<=limiteDeProximidad && dias>=0;
	}
}

