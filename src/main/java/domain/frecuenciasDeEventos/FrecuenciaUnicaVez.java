package domain.frecuenciasDeEventos;
import java.util.Date;
import java.time.*;

public class FrecuenciaUnicaVez implements FrecuenciaDeEvento{
	static int limiteDeProximidad = 7;
	
	public boolean esProximo(LocalDateTime fechaEvento, LocalDateTime fechaActual) {
		int dias = fechaEvento.getDayOfMonth()-fechaActual.getDayOfMonth();
		//int dias=(int) ((fechaEvento.getTime()-fechaActual.getTime())/(1000*60*60*24));
		return dias<=limiteDeProximidad && dias>=0;
	}
}

