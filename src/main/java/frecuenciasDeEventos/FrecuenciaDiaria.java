package frecuenciasDeEventos;
import java.time.*;
public class FrecuenciaDiaria implements FrecuenciaDeEvento{
	private int limiteDeProximidad = 8;
	
	public boolean esProximo(LocalDateTime fechaEvento, LocalDateTime fechaActual) {
		int horas= fechaEvento.getHour()-fechaActual.getHour();
		if (fechaEvento.getHour()<fechaActual.getHour()){ 
			horas+=24;
		};
		return horas<=limiteDeProximidad;
	}
}
