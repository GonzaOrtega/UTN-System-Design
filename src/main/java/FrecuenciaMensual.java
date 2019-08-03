import java.util.Date;

public class FrecuenciaMensual implements FrecuenciaDeEvento{
	private int limiteDeProximidad = 5;
	public boolean esProximo(Date fechaEvento, Date fechaActual) {
		int dias= fechaEvento.getDate()-fechaActual.getDate();
		if (fechaEvento.getDate()<fechaActual.getDate()){ 
			dias+=30 ;
		};
		return dias<=limiteDeProximidad;
	}
}
