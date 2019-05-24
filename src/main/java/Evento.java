import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class Evento {
	Date fecha;
	Usuario usuario;
	Guardarropa guardarropas;
	int contador=0;
	static int limiteDeProximidad = 7;
	public Evento(String fecha) {
			this.fecha = this.PasarAFormatoAdecuado(fecha);
	}
	public void sugerir() {
		Set<Set<Prenda>> atuendos = Sugeridor.sugerirPrendasPara(usuario);
		//usuario.haySugerenciasNuevas(this.atuendos);
		contador++;
	}
	public boolean esProximo(String fechaActual) {
		Date fechaActualDate = this.PasarAFormatoAdecuado(fechaActual);
		int dias=(int) ((fecha.getTime()-fechaActualDate.getTime())/86400000);
		return dias<=limiteDeProximidad;
	}
	public Date PasarAFormatoAdecuado(String fecha) {
		try {
		SimpleDateFormat formato = new SimpleDateFormat(this.getFotmatoDeFecha());
		return formato.parse(fecha);
		}catch(ParseException exception) {
			throw new FechaIncorrectaException("WARNING: La fecha no coincide con el formato: "+this.getFotmatoDeFecha());
		}
	}
	public static String getFotmatoDeFecha() {
		return "dd-MM-yyyy";
	}
}
