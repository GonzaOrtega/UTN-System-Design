import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JobsEventos implements Runnable{
	@Override
	public void run() {
		RepositorioEventos.getInstance().proximos(new Date()).forEach(evento->evento.sugerir());
		
	}
	public static void main() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable codigoAEjecutar = new JobsEventos();
        int retrasoInicial = 0;
        int periodo = 1;
        scheduler.scheduleAtFixedRate(codigoAEjecutar, retrasoInicial, periodo, TimeUnit.DAYS);//cambiar a TimeUnit.SECONDS para testear
    }
}
