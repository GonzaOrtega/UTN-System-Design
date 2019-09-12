package ui;

import java.time.LocalDateTime;

import org.uqbar.commons.model.annotations.Observable;

import domain.Evento;
import domain.enums.SugerenciasListas;
import domain.enums.TipoFrecuencia;
@Observable
public class EventoView {
	private LocalDateTime fechaInicio;
	Evento evento;
	
	public EventoView(Evento evento, LocalDateTime fecha) {
		this.evento = evento;
		this.fechaInicio = fecha;
	}
	public LocalDateTime getFecha() {
		return evento.cualEsLaFechaProxima(fechaInicio);
	}

	public TipoFrecuencia getFrecuencia() {
		return evento.getFrecuencia();
	}

	public SugerenciasListas getSugerenciasListas() {
		return evento.getSugerenciasListas();
	}

	public String getDescripcion() {
		return evento.getDescripcion();
	}
}
