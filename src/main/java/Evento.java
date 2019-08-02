import java.util.Date;
import java.util.Set;

public class Evento {
	private Date fecha;
	private Usuario usuario;
	private Sugeridor sugeridor;
	static int limiteDeProximidad = 7;
	//int contador=0;// usado solo de forma provisoria para el JobsEventosTest
	
	public Evento(Date unaFecha, Usuario unUsuario,Sugeridor unSugeridor) {
			this.fecha=unaFecha;
			this.usuario = unUsuario;
			this.sugeridor=unSugeridor;
	}
	
	public void sugerir() {
		Set<Set<Prenda>> atuendos = sugeridor.sugerirPrendasPara(usuario);
		atuendos.forEach(atuendo->usuario.agregarSugerencia(new Sugerencia(atuendo,this)));
		usuario.notificarSugerenciasNuevas();
		//contador++;//Usado en forma provisoria para el JobsEventosTest
	}
	
	public boolean esProximo(Date fechaActual) {
		//Date fechaActualDate = this.PasarAFormatoAdecuado(fechaActual);
		int dias=(int) ((fecha.getTime()-fechaActual.getTime())/(1000*60*60*24));
		return dias<=limiteDeProximidad;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
}
