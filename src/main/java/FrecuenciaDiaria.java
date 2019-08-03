import java.util.Date;
public class FrecuenciaDiaria implements FrecuenciaDeEvento{
	private int limiteDeProximidad = 8;
	public boolean esProximo(Date fechaEvento, Date fechaActual) {
		int horas= fechaEvento.getHours()-fechaActual.getHours();
		if (fechaEvento.getHours()<fechaActual.getHours()){ 
			horas+=24;
		};
		return horas<=limiteDeProximidad;
	}
}
