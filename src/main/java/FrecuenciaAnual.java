import java.util.Date;

public class FrecuenciaAnual implements FrecuenciaDeEvento{
	int limiteDeProximidad = 30;
	public boolean esProximo(Date fechaEvento, Date fechaActual){
		int dias=(int) ((fechaEvento.getTime()-fechaActual.getTime())/(1000*60*60*24))%365;
		if (fechaEvento.getTime()<fechaActual.getTime()){ 
			dias+=365 ;
		};
		return dias<=limiteDeProximidad;
	};
};
