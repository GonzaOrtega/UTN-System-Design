import java.util.Date;
import java.util.Set;

public class Sugerencia {
	private Set<Prenda> atuendo;
	private boolean aceptado;
	
	public Sugerencia(Set<Prenda> unAtuendo, boolean estado) {
		atuendo=unAtuendo;
		aceptado=estado;
	}
	/*
	Evento evento;
	Evaluacion evaluacion;
	
	public Sugerencia(Set<Set<Prenda>> atuendo, Evento evento, Evaluacion evaluacion) {
		this.atuendo = atuendo;
		this.evento = evento;
		this.evaluacion = evaluacion;
	}
	
	public Evaluacion getEvaluacion() {
		return evaluacion;
	}
	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}
	public Date getFecha() {
		return this.evento.getFecha();
	}
	public Usuario getUsuario() {
		return this.evento.getUsuario();
	}
	public Guardarropa getGuardarropas() {
		return this.evento.getGuardarropas();
	}	*/
	
	
}
