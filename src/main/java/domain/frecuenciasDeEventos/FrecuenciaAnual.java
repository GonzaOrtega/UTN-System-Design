package domain.frecuenciasDeEventos;

import java.time.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import domain.enums.TipoFrecuencia;
@Entity
public class FrecuenciaAnual extends FrecuenciaDeEvento {
//	@Id @GeneratedValue
//	long id;
	int limiteDeProximidad = 30;
	
	LocalDateTime fechaEvento;
	public FrecuenciaAnual() {}
	public FrecuenciaAnual(int mes, int dia) {
		fechaEvento = LocalDateTime.of(119, mes, dia, 0, 0, 0);
	}

	public TipoFrecuencia getFrecuencia() {
		return TipoFrecuencia.Anual;
	}

	public boolean esProximo(LocalDateTime fechaActual) {
		return this.diferenciaEnDiasEntreDosFechas(fechaEvento, fechaActual) <= limiteDeProximidad;
	}

	public long diferenciaEnDiasEntreDosFechas(LocalDateTime fechaFin, LocalDateTime fechaComienzo) {
		int dias = fechaFin.getDayOfYear() - fechaComienzo.getDayOfYear();
		if (fechaFin.getDayOfYear() < fechaComienzo.getDayOfYear()) {
			dias += 365;
		}
		return dias;
	}

	public LocalDateTime cualEsLaFechaProxima(LocalDateTime fechaComienzo) {
		long diasEntreComienzoEvento = this.diferenciaEnDiasEntreDosFechas(fechaEvento, fechaComienzo);
		return fechaComienzo.plusDays(diasEntreComienzoEvento);
	}

	public boolean yaSucedio(LocalDateTime fechaActual) {
		return fechaEvento.isBefore(fechaActual) && fechaEvento.plusDays(1).isAfter(fechaActual);
	}
};
