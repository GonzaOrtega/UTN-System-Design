import static org.junit.Assert.*;
import org.junit.Test;
public class EventoTest {
	@Test
	public void ProximidadEntreFechasDiferentesCercanasDevuelveVerdadero() {
		Evento evento = new Evento("24-05-2019", new Usuario(TipoUsuario.GRATUITO,0));
		assertTrue(evento.esProximo("17-05-2019"));		
	}
	@Test
	public void ProximidadEntreFechasDiferentesLajanasDevuelveFalso() {
		Evento evento = new Evento("24-05-2019",new Usuario(TipoUsuario.GRATUITO,0));
		assertFalse(evento.esProximo("16-05-2019"));		
	}
	//Es Solo para ver como funcionaria cuando se pregunta por la fecha actual
	/*
	@Test
	public void calculoDeDiasEntreFechaDelEventoYFechaActual() {
		Evento evento = new Evento("24-05-2019");
		DateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaActual = new Date();
		int a = evento.esProximo(formato.format(fechaActual));
		assertTrue(a==1);		
	}*/
	@Test(expected= FechaIncorrectaException.class)
	public void instanciacionIncorrectaDelEvento(){
		new Evento("hola",new Usuario(TipoUsuario.GRATUITO,0));
	}
}
