package frecuenciasDeEventos;
import java.time.*;
public class FrecuenciaSemanal implements FrecuenciaDeEvento{
	private int limiteDeProximidad = 2;
	
	public boolean esProximo(LocalDateTime fechaEvento, LocalDateTime fechaActual) {
		int dias=fechaEvento.getDayOfMonth()-fechaActual.getDayOfMonth();
		if (fechaEvento.getDayOfMonth()<fechaActual.getDayOfMonth()){ 
			dias+=7;
		};
		return dias<=limiteDeProximidad;
	}
}
