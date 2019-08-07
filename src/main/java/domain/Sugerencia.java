package domain;
import java.util.Set;

import domain.enums.TipoSugerencias;
import domain.exceptions.*;

public class Sugerencia {
	private Set<Prenda> atuendo;
	private TipoSugerencias estado= TipoSugerencias.PENDIENTE;
	private Evento evento;
	//private String comentario= null;
	
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

}
