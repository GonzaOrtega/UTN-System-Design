import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class  RepositorioEventos {
	private Set<Evento> eventos = new HashSet<Evento>(); 
	 private RepositorioEventos() {}
	private static class RepositorioEventosHolder{
        private static final RepositorioEventos INSTANCE = new RepositorioEventos();
    }
    public static RepositorioEventos getInstance() {
        return RepositorioEventosHolder.INSTANCE;
    }
	public void agendar(Evento evento) {
		this.eventos.add(evento);
	}
	public Set<Evento> proximos(String fecha) {
		return this.eventos.stream().filter(evento->evento.esProximo(fecha)).collect(Collectors.toSet());
	}
	public Set<Evento> getEventos() {
		return eventos;
	}
	
}
