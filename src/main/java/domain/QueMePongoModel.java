package domain;
import java.time.LocalDate;
import java.util.*;

import org.uqbar.commons.model.annotations.Observable;

@Observable
public class QueMePongoModel {
	private int fecha;
	private int otraFecha;
	private Set<Evento> eventos;
	
	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public int getOtraFecha() {
		return otraFecha;
	}

	public void setOtraFecha(int otraFecha) {
		this.otraFecha = otraFecha;
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

	public LocalDate fecha(int fechaEnNro) {
		return LocalDate.of(fechaEnNro/10000,(fechaEnNro%10000)/100,fechaEnNro/1000000);
	}
	
}
