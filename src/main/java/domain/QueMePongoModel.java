package domain;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import org.uqbar.commons.model.annotations.Observable;

@Observable
public class QueMePongoModel {
	private int fechaInicio;
	private int fechaFin;
	private Set<Evento> eventos = new HashSet<Evento>();
	
	// Getters y setters

	public int getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(int fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public int getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(int fechaFinal) {
		this.fechaFin = fechaFinal;
	}

	public Set<Evento> getEventos() { return eventos; }

	public void setEventos(Set<Evento> eventos) { this.eventos = eventos; }

	// ***********************************
	
	public LocalDateTime fecha(int fechaEnNro) {
		return LocalDateTime.of(LocalDate.of(fechaEnNro/10000,(fechaEnNro%10000)/100,(fechaEnNro%10000)%100),LocalTime.of(0,0,0));
	}
	
	public void listarEventosPrueba(){
		RepositorioDeUsuarios.getInstance().init();
		this.eventos = RepositorioDeUsuarios.getInstance().eventos();
	}
	
	public void listarEventos() {
		RepositorioDeUsuarios.getInstance().init();

		this.eventos = RepositorioDeUsuarios.getInstance()
				.eventos()
				.stream()
				.filter(evento->evento.sucedeEntreEstasfechas(this.fecha(fechaInicio),this.fecha(fechaFin)))
				.collect(Collectors.toSet());
		System.out.println("Fecha inicio: "+this.fecha(fechaInicio));
		System.out.println("Fecha fin: "+this.fecha(fechaFin));
		System.out.println(this.eventos.size());
	}
	
}
