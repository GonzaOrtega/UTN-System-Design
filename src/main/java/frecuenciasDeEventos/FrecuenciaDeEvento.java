package frecuenciasDeEventos;
import java.time.*;

public interface FrecuenciaDeEvento {
	public boolean esProximo(LocalDateTime fechaEvento, LocalDateTime fechaActual);
}
