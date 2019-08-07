package domain.frecuenciasDeEventos;
import java.time.*;

public interface FrecuenciaDeEvento {
	public boolean esProximo(LocalDateTime fechaEvento, LocalDateTime fechaActual);
	public boolean sucedeEntreEstasfechas(LocalDateTime fechaComienzo, LocalDateTime fechaFin,LocalDateTime fechaEvento);
}
