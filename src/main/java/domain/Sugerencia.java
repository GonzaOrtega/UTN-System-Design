package domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import domain.enums.TipoSugerencias;

//TODO wtf? abstract persistence test
//TODO buscar mapped superclass
@Entity 
public class Sugerencia{

	@Id
	@GeneratedValue
	private long id;

	@ManyToMany
	private Set<Prenda> atuendo;

	@Enumerated(EnumType.STRING)
	private TipoSugerencias estado = TipoSugerencias.PENDIENTE;

	@ManyToOne
	private Evento evento;

	public Sugerencia(Set<Prenda> unAtuendo, Evento evento) {
		this.atuendo = unAtuendo;
		this.evento = evento;
	}

	public void setPrendasComoNoUsadas() {
		atuendo.forEach(prenda -> prenda.setUsada(false));
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
		return this.evento == evento;
	}

	public boolean aceptada() {
		return this.estado == TipoSugerencias.ACEPTADA;
	}

}
