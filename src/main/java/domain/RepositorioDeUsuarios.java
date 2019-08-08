package domain;
import java.util.*;
import java.time.*;
import java.util.stream.*;

import domain.apisClima.MockAPI;
import domain.apisClima.ProveedorClima;
import domain.enums.TipoUsuario;
import domain.frecuenciasDeEventos.FrecuenciaDiaria;
import domain.frecuenciasDeEventos.FrecuenciaUnicaVez;

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
	
	public Set<Evento> eventosProximos(){
		return this.eventos().stream().filter(evento->evento.esProximo(LocalDateTime.now())).collect(Collectors.toSet());
	}
	
	public void obtenerSugerenciasDeEventosProximosA(LocalDateTime fecha) {
		this.usuarios.stream().forEach(
				usuario->usuario.eventosProximos(fecha)
								.stream()
								.forEach(evento -> {
									evento.sugerir(usuario);
									usuario.notificarSugerenciasNuevas();
									})
								);
	}
	
	public void notificarAlertaMeterologicaDeEventosSugeridosPara(LocalDateTime dia) {
		this.usuarios.stream().forEach(usuario->{
			usuario.eventosConAlertasMeterologicasPara(dia)
								.stream()
								.forEach(evento -> usuario.notificarAlertaMeterologicaDe(evento));
		});
	}
	
}
