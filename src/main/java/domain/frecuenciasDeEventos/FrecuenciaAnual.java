package domain.frecuenciasDeEventos;
import java.time.*;

public class FrecuenciaAnual implements FrecuenciaDeEvento{
	int limiteDeProximidad = 30;
	
	public boolean esProximo(LocalDateTime fechaEvento, LocalDateTime fechaActual){
		int dias = fechaEvento.getDayOfYear()-fechaActual.getDayOfYear();
		if (fechaEvento.getDayOfYear()<fechaActual.getDayOfYear()){ 
			dias+=365 ;
		}
		return dias<=limiteDeProximidad;
	}
};
