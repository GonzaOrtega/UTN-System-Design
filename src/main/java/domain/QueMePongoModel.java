package domain;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import org.uqbar.commons.model.annotations.Observable;

import domain.apisClima.MockAPI;
import domain.apisClima.ProveedorClima;
import domain.frecuenciasDeEventos.FrecuenciaUnicaVez;

@Observable
public class QueMePongoModel {
	private int fechaInicio;
	private int fechaFin;
	ProveedorClima APIDeMentiritas = new MockAPI(21,23,false);
	Sugeridor sugeridor = new Sugeridor(APIDeMentiritas);
	private Evento evento = new Evento(LocalDateTime.of(LocalDate.of(2019, Month.MAY, 24),LocalTime.now()),sugeridor,new FrecuenciaUnicaVez());
	private Evento p = new Evento(LocalDateTime.of(LocalDate.of(2019, Month.MAY,23),LocalTime.now()),sugeridor,new FrecuenciaUnicaVez());
	private Set<Evento> eventos= new HashSet<Evento>();
	public QueMePongoModel() {
		eventos .add(evento);
	};
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

	public Set<Evento> getEventos(){
		return eventos;
	}

	public void setEventos(Set<Evento> eventos) {
		this.eventos= eventos;
	}

	public LocalDateTime fecha(int fechaEnNro) {
		return LocalDateTime.of(fechaEnNro/10000,(fechaEnNro%10000)/100,fechaEnNro/1000000,0,0,0);
	}
	public void listarEventos() {
		/*eventos= RepositorioDeUsuarios.getInstance()
					.eventos()
					.stream()
					.filter(evento->evento.sucedeEntreEstasfechas(this.fecha(fechaInicio),this.fecha(fechaFin)))
					.collect(Collectors.toSet());*/
		eventos.add(p);
	}
	
}
