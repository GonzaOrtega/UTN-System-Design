import java.util.Date;
import java.util.Set;

public class Evento {
	private Date fecha;
	private Usuario usuario;
	private Sugeridor sugeridor;
	private FrecuenciaDeEvento frecuencia;
	//int contador=0;// usado solo de forma provisoria para el JobsEventosTest
	
	public Evento(Date unaFecha, Usuario unUsuario,Sugeridor unSugeridor, FrecuenciaDeEvento unaFrecuencia) {
			this.fecha=unaFecha;
			this.usuario = unUsuario;
			this.sugeridor=unSugeridor;
			this.frecuencia = unaFrecuencia;
	}
	
	public void sugerir() {
		Set<Set<Prenda>> atuendos = sugeridor.sugerirPrendasPara(usuario);
		atuendos.forEach(atuendo->usuario.agregarSugerencia(new Sugerencia(atuendo,this)));
		usuario.notificarSugerenciasNuevas();
		//contador++;//Usado en forma provisoria para el JobsEventosTest
	}
	
	public boolean esProximo(Date fechaActual) {
		return frecuencia.esProximo(this.fecha, fechaActual);
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
}
