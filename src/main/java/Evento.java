import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import exceptions.*;
import exceptions.FechaIncorrectaException;

public class Evento {
	private Date fecha;
	private Usuario usuario;
	private Sugeridor sugeridor;
	static int limiteDeProximidad = 7;
	//int contador=0;// usado solo de forma provisoria para el JobsEventosTest
	
	public Evento(String unaFecha, Usuario unUsuario,Sugeridor unSugeridor) {
			this.fecha = this.PasarAFormatoAdecuado(unaFecha);
			this.usuario = unUsuario;
			this.sugeridor=unSugeridor;
	}
	
	public void sugerir() {
		Set<Set<Prenda>> atuendos = sugeridor.sugerirPrendasPara(usuario);
		atuendos.forEach(atuendo->usuario.agregarSugerencia(new Sugerencia(atuendo,this)));
		//Implementar para que le llegue notificacion al usuario de que tiene nuevos atuendos
		//contador++;//Usado en forma provisoria para el JobsEventosTest
	}
	
	public boolean esProximo(String fechaActual) {
		Date fechaActualDate = this.PasarAFormatoAdecuado(fechaActual);
		int dias=(int) ((fecha.getTime()-fechaActualDate.getTime())/86400000);
		return dias<=limiteDeProximidad;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public Date PasarAFormatoAdecuado(String fecha) {
		try {
		SimpleDateFormat formato = new SimpleDateFormat(Evento.getFotmatoDeFecha());
		return formato.parse(fecha);
		}catch(ParseException exception) {
			throw new FechaIncorrectaException("WARNING: La fecha no coincide con el formato: "+Evento.getFotmatoDeFecha());
		}
	}
	
	public static String getFotmatoDeFecha() {
		return "dd-MM-yyyy";
	}
}
