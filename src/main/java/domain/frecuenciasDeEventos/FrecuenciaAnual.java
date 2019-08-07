package domain.frecuenciasDeEventos;
import java.time.*;

public class FrecuenciaAnual implements FrecuenciaDeEvento{
	int limiteDeProximidad = 30;
	
	public boolean esProximo(LocalDateTime fechaEvento, LocalDateTime fechaActual){
		return this.diferenciaEnDiasEntreDosFechas(fechaEvento,fechaActual)<=limiteDeProximidad;
	}
	public long diferenciaEnDiasEntreDosFechas(LocalDateTime fechaFin,LocalDateTime fechaComienzo) {
		int dias = fechaFin.getDayOfYear()-fechaComienzo.getDayOfYear();
		if (fechaFin.getDayOfYear()<fechaComienzo.getDayOfYear()){ 
			dias+=365 ;
		}
		return dias;
	}
	
	public boolean sucedeEntreEstasfechas(LocalDateTime fechaComienzo, LocalDateTime fechaFin,LocalDateTime fechaEvento ) {
		long diasEntreComienzoEvento =this.diferenciaEnDiasEntreDosFechas(fechaEvento,fechaComienzo);
		long diasEntreComienzoFin =this.diferenciaEnDiasEntreDosFechas(fechaFin,fechaComienzo);
		return diasEntreComienzoEvento<=diasEntreComienzoFin;
	}
};
