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
	
	//****************** INICIALIZACION PARA PRUEBA DE ARENA ************************************
	
	public void init(){
		ProveedorClima APIDeMentiritas = new MockAPI(21,23,false);
		Sugeridor sugeridor = new Sugeridor(APIDeMentiritas);
		Evento evento = new Evento(LocalDateTime.of(LocalDate.of(2019, Month.MAY, 24),LocalTime.now()),sugeridor,new FrecuenciaUnicaVez(),"Ir a cenar");
		Evento eventodos = new Evento(LocalDateTime.of(LocalDate.of(2019, Month.MAY,23),LocalTime.now()),sugeridor,new FrecuenciaUnicaVez(),"Ir a pasear al perro");
		Usuario juan = new Usuario(TipoUsuario.PREMIUM,0);
		juan.agendarEvento(evento);
		juan.agendarEvento(eventodos);
	}
	
	//*******************************************************************************************
	
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
	
	public Set<Usuario> usuariosConEventosConAlertarMeterologicas(LocalDateTime fecha) {
		return this.usuarios().stream().filter(
					usuario-> usuario.eventos()
									 .stream()
									 .anyMatch(evento->evento.alertaMeterologica()))
									   .collect(Collectors.toSet());
	}
	
	public void notificarAlertaMeterologica(LocalDateTime fecha) {
		this.usuariosConEventosConAlertarMeterologicas(fecha)
							.stream()
							.forEach(usuario->usuario.notificarAlertaMeterologica());
	}
	
}
