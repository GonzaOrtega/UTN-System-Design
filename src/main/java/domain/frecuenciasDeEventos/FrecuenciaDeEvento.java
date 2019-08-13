package domain.frecuenciasDeEventos;
import java.time.*;
import domain.enums.*;

public interface FrecuenciaDeEvento {
	public TipoFrecuencia getFrecuencia();
	public boolean esProximo(LocalDateTime fechaActual);
	public boolean sucedeEntreEstasFechas(LocalDateTime fechaComienzo, LocalDateTime fechaFin);
	public LocalDateTime cualEsLaFechaProxima(LocalDateTime fechaComienzo);
}
