package domain;
import java.util.Set;
import domain.enums.TipoSugerencias;

public class Sugerencia {
	private Set<Prenda> atuendo;
	private TipoSugerencias estado= TipoSugerencias.PENDIENTE;
	private Evento evento;
	
	public Sugerencia(Set<Prenda> unAtuendo, Evento evento) {
		this.atuendo=unAtuendo;
		this.evento=evento;
	}
	
	public TipoSugerencias getEstado() {
		return estado;
	}
	
	public void setEstado(TipoSugerencias estado) {
		this.estado = estado;
	}

	public Set<Prenda> getAtuendo() {
		return atuendo;
	}

	public Evento getEvento() {
		return evento;
	}	
	
	public boolean mismoEvento(Evento evento) {
		return this.evento== evento;
	}

	public boolean aceptada() {
		return this.estado==TipoSugerencias.ACEPTADA;
	}

}
