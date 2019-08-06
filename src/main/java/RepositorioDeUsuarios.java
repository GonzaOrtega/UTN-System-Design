import java.util.*;
import java.util.stream.Collectors;
import java.time.*;

public class RepositorioDeUsuarios {
	private Set<Usuario> usuarios = new HashSet<Usuario>();
	private RepositorioDeUsuarios(){};
	
	private static class RepositorioDeUsuariosHolder{
		private static final RepositorioDeUsuarios INSTANCE = new RepositorioDeUsuarios();
	}
	public static RepositorioDeUsuarios getInstance() {
		return RepositorioDeUsuariosHolder.INSTANCE;
	}

	public void agregar(Usuario usuario) {
		this.usuarios.add(usuario);
	}
	
	public Set<Usuario> usuarios() {return usuarios;}
	
	public Set<Evento> eventos(){
		return usuarios.stream().flatMap(usuario->usuario.eventos().stream()).collect(Collectors.toSet());
	}
	
	public void obtenerSugerenciasDeEventosProximosA(LocalDateTime fecha) {
		this.usuarios.stream().forEach(usuario->usuario.eventosProximos(fecha).forEach(evento -> evento.sugerir(usuario)));
	}
	
}
