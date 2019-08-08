package domain;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import org.uqbar.commons.model.annotations.Observable;
import org.uqbar.commons.model.exceptions.UserException;

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
			this.fechaInicio=fechaInicio;
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
	
	public void validar() {
		if(Integer.toString(fechaFin).length()>8 || Integer.toString(fechaInicio).length()>8)
			throw new UserException("ERROR: Ingrese una fecha valida.");
		if(fechaFin<fechaInicio)
			throw new UserException("ERROR: Ingrese correctamente las fechas.");
	}
	
	
	public void listarEventos() {
		this.validar();

		this.eventos = RepositorioDeUsuarios.getInstance()
				.eventos()
				.stream()
				.filter(evento->evento.sucedeEntreEstasfechas(this.fecha(fechaInicio),this.fecha(fechaFin)))
				.collect(Collectors.toSet());
		
	}
	
}
