import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class RepositorioEventos {
	Set<Evento> eventos = new HashSet<Evento>(); 
	private static class RepositorioEventosHolder {
        private static final RepositorioEventos INSTANCE = new RepositorioEventos();
    }
    public static RepositorioEventos getInstance() {
        return RepositorioEventosHolder.INSTANCE;
    }
	public void agendar(Evento evento) {
		this.eventos.add(evento);
	}
	public Set<Evento> proximos(String fecha) {
		Set<Evento> eventosProximos= new HashSet<Evento>();
		this.eventos.stream().filter(evento->evento.esProximo(fecha));
		return eventosProximos;
	}
}
