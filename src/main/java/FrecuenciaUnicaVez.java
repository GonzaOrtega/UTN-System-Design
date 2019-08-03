import java.util.Date;

public class FrecuenciaUnicaVez implements FrecuenciaDeEvento{
	static int limiteDeProximidad = 7;
	public boolean esProximo(Date fechaEvento, Date fechaActual) {
		int dias=(int) ((fechaEvento.getTime()-fechaActual.getTime())/(1000*60*60*24));
		return dias<=limiteDeProximidad && dias>=0;
	}
}

