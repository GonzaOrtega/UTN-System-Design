package domain.frecuenciasDeEventos;

import java.time.*;
import domain.enums.*;

public interface FrecuenciaDeEvento {
	
	public TipoFrecuencia getFrecuencia();

	public boolean esProximo(LocalDateTime fechaActual);

	public default boolean sucedeEntreEstasFechas(LocalDateTime fechaComienzo, LocalDateTime fechaFin) {
		return this.cualEsLaFechaProxima(fechaComienzo).isBefore(fechaFin)
				|| this.cualEsLaFechaProxima(fechaComienzo).isEqual(fechaFin);
	}

	public LocalDateTime cualEsLaFechaProxima(LocalDateTime fechaComienzo);

	public boolean yaSucedio(LocalDateTime fechaActual);
}
