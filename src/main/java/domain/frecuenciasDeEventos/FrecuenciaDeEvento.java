package domain.frecuenciasDeEventos;
import java.time.*;
import domain.enums.*;

public interface FrecuenciaDeEvento {
	public TipoFrecuencia getFrecuencia();
	public boolean esProximo(LocalDateTime fechaEvento, LocalDateTime fechaActual);
	public boolean sucedeEntreEstasfechas(LocalDateTime fechaComienzo, LocalDateTime fechaFin,LocalDateTime fechaEvento);
}
