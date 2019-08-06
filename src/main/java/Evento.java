import java.time.*;
import java.util.Date;
import java.util.Set;
import frecuenciasDeEventos.*;

public class Evento {
	private LocalDateTime fecha;
	//private Usuario usuario;
	private Sugeridor sugeridor;
	private FrecuenciaDeEvento frecuencia;
	//int contador=0;// usado solo de forma provisoria para el JobsEventosTest
	
	/*
	public Evento(Date unaFecha, Usuario unUsuario,Sugeridor unSugeridor, FrecuenciaDeEvento unaFrecuencia) {
			this.fecha=unaFecha;
			this.usuario = unUsuario;
			this.sugeridor=unSugeridor;
			this.frecuencia = unaFrecuencia;
	}*/
	
	public Evento(LocalDateTime unaFecha,Sugeridor unSugeridor, FrecuenciaDeEvento unaFrecuencia) {
		this.fecha=unaFecha;
		this.sugeridor=unSugeridor;
		this.frecuencia = unaFrecuencia;
}
	
	public void sugerir(Usuario usuario) {
		Set<Set<Prenda>> atuendos = sugeridor.sugerirPrendasPara(usuario);
		atuendos.forEach(atuendo->usuario.agregarSugerencia(new Sugerencia(atuendo,this)));
		usuario.notificarSugerenciasNuevas();
		System.out.println("Sugerio a:"+usuario);
		//contador++;//Usado en forma provisoria para el JobsEventosTest
	}
	
	public boolean esProximo(LocalDateTime fechaActual) {
		return frecuencia.esProximo(this.fecha, fechaActual);
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}
	
	/*public Usuario getUsuario() {
		return usuario;
	}*/
}
