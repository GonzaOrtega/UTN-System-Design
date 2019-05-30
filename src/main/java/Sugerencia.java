import java.util.Set;
import exceptions.*;

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
	
}
