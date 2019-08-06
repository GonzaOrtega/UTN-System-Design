package domain;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.time.*;

public class JobsUsuarios implements Runnable{
	@Override
	public void run() {
		LocalDateTime fechaActual = LocalDateTime.now();
		RepositorioDeUsuarios.getInstance().obtenerSugerenciasDeEventosProximosA(fechaActual);
		RepositorioDeUsuarios.getInstance().notificarAlertaMeterologica(fechaActual);
	}
	public static void main() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable codigoAEjecutar = new JobsUsuarios();
        int retrasoInicial = 0;
        int periodo = 1;
        scheduler.scheduleAtFixedRate(codigoAEjecutar, retrasoInicial, periodo, TimeUnit.DAYS);//cambiar a TimeUnit.SECONDS para testear
    }
}
