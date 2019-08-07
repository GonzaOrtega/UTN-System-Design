package domain;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.uqbar.commons.model.annotations.Observable;

@Observable
public class QueMePongoModel {
	private int fechaInicio;
	private int fechaFin;
	private Set<Evento> eventos;
	private String fecha = "hola";
	public String getFecha() {
		return fecha;
	}
	public int getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(int fecha) {
		this.fechaInicio = fecha;
	}

	public int getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(int fecha) {
		this.fechaFin = fecha;
	}

	public Set<Evento> eventos(){
		return RepositorioDeUsuarios.getInstance().eventos();
	}

	public Set<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(Set<Evento> eventos) {
		this.eventos = eventos;
	}

	public LocalDateTime fecha(int fechaEnNro) {
		return LocalDateTime.of(fechaEnNro/10000,(fechaEnNro%10000)/100,fechaEnNro/1000000,0,0,0);
	}
	public void listarEventos() {
		eventos= RepositorioDeUsuarios.getInstance()
					.eventos()
					.stream()
					.filter(evento->evento.sucedeEntreEstasfechas(this.fecha(fechaInicio),this.fecha(fechaFin)))
					.collect(Collectors.toSet());
	}
	
}
