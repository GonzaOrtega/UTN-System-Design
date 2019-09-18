package domain;

import java.util.*;
import java.time.*;
import java.util.stream.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

//TODO revisar e implementar queries (Estoy en eso)
public class RepositorioDeUsuarios implements WithGlobalEntityManager{
	//private Set<Usuario> usuarios = new HashSet<Usuario>();

	// ------------------- Metodos p/ obtener instancia -------------------

	private static class RepositorioDeUsuariosHolder {
		private static final RepositorioDeUsuarios INSTANCE = new RepositorioDeUsuarios();
	}

	public static RepositorioDeUsuarios getInstance() {
		return RepositorioDeUsuariosHolder.INSTANCE;
	}
	
	// ---------------- Getters, setters y constructores -----------------
	
	private RepositorioDeUsuarios() {
	};

	public Set<Usuario> usuarios() {
		List<Usuario> usuaries = entityManager().createQuery("from Usuario order by Id", Usuario.class).getResultList();
		Set<Usuario> usuarios = new HashSet<Usuario>(usuaries);
		return usuarios;
	}
	
	public Set<Evento> eventos() {
		return usuarios().stream().flatMap(usuario -> usuario.eventos().stream()).collect(Collectors.toSet());
	}

	public Set<Evento> eventosProximos() {
		return this.eventos().stream().filter(evento -> evento.esProximo(LocalDateTime.now()))
				.collect(Collectors.toSet());
	}
	
	// ------------------------------ Metodos -------------------------------

	public void sugerir(LocalDateTime fecha) {
		this.usuarios().stream().forEach(usuario -> usuario.eventosProximos(fecha).stream().forEach(evento -> {
			evento.sugerir(usuario);
			usuario.notificarSugerenciasNuevas();
		}));
	}
	
	public void agregar(Usuario usuario) {
		entityManager().persist(usuario);
	}

	public void notificarAUsuariosAfectadosPorCambioDeClima() {
		this.usuarios().stream().forEach(usuario -> usuario.eventosConCambioDeClima().stream()
				.forEach(evento -> usuario.notificarAlertaMeterologicaDe(evento)));
	}
	
	public void lavarTodaLaRopaSucia() {
		this.usuarios().forEach(usuario -> usuario.lavarLaRopa());
	}

}
