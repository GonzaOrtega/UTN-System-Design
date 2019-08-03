import java.util.Date;
public class FrecuenciaSemanal implements FrecuenciaDeEvento{
	private int limiteDeProximidad = 2;
	public boolean esProximo(Date fechaEvento, Date fechaActual) {
		int dias=fechaEvento.getDay()-fechaActual.getDay();
		if (fechaEvento.getDay()<fechaActual.getDay()){ 
			dias+=7;
		};
		return dias<=limiteDeProximidad;
	}
}
