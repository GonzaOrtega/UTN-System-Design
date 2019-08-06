package frecuenciasDeEventos;
import java.time.*;

public class FrecuenciaMensual implements FrecuenciaDeEvento{
	private int limiteDeProximidad = 5;
	public boolean esProximo(LocalDateTime fechaEvento, LocalDateTime fechaActual) {
		int dias= fechaEvento.getDayOfMonth()-fechaActual.getDayOfMonth();
		if (fechaEvento.getDayOfMonth()<fechaActual.getDayOfMonth()){ 
			dias+=30 ;
		};
		return dias<=limiteDeProximidad;
	}
}
