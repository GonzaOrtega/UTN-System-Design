package domain;

import java.util.*;
import java.time.*;
import java.util.stream.*;
import javax.persistence.EntityManager;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

//TODO revisar e implementar queries (Estoy en eso)
public class RepositorioDeUsuarios implements WithGlobalEntityManager{
	//private Set<Usuario> usuarios = new HashSet<Usuario>();

	private RepositorioDeUsuarios() {
	};

	private static class RepositorioDeUsuariosHolder {
		private static final RepositorioDeUsuarios INSTANCE = new RepositorioDeUsuarios();
	}

	public static RepositorioDeUsuarios getInstance() {
		return RepositorioDeUsuariosHolder.INSTANCE;
	}

	public void agregar(Usuario usuario) {
		//this.usuarios.add(usuario);
		entityManager().persist(usuario);
	}

	public Set<Usuario> getUsuarios() {
		List<Usuario> usuaries = entityManager().createQuery("from Usuario order by Id", Usuario.class).getResultList();
		Set<Usuario> usuarios = new HashSet<Usuario>(usuaries);
		return usuarios;
	}

	public Set<Evento> eventos() {
		return getUsuarios().stream().flatMap(usuario -> usuario.eventos().stream()).collect(Collectors.toSet());
	}

	public Set<Evento> eventosProximos() {
		return this.eventos().stream().filter(evento -> evento.esProximo(LocalDateTime.now()))
				.collect(Collectors.toSet());
	}

	public void obtenerSugerenciasDeEventosProximosA(LocalDateTime fecha) {
		this.getUsuarios().stream().forEach(usuario -> usuario.eventosProximos(fecha).stream().forEach(evento -> {
			evento.sugerir(usuario);
			usuario.notificarSugerenciasNuevas();
		}));
	}

	public void notificarAUsuariosAfectadosPorCambioDeClima() {
		this.getUsuarios().stream().forEach(usuario -> usuario.eventosConCambioDeClima().stream()
				.forEach(evento -> usuario.notificarAlertaMeterologicaDe(evento)));
	}

	public void lavarTodaLaRopaSucia() {
		this.getUsuarios().forEach(usuario -> usuario.lavarLaRopa());
	}

}
