package domain;
import java.util.Set;
import domain.enums.TipoSugerencias;

public class Sugerencia {
	private Set<Prenda> atuendo;
	private TipoSugerencias estado= TipoSugerencias.PENDIENTE;
	private Evento evento;
	
	public Sugerencia(Set<Prenda> unAtuendo, Evento evento) {
		atuendo=unAtuendo;
		this.estado=estado;
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

}
