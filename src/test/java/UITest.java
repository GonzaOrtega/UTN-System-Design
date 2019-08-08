import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;
import domain.*;
import domain.apisClima.MockAPI;
import domain.apisClima.ProveedorClima;
import domain.enums.TipoUsuario;
import domain.frecuenciasDeEventos.FrecuenciaUnicaVez;

import org.uqbar.commons.model.exceptions.UserException;

public class UITest {
	QueMePongoModel model = new QueMePongoModel();
	ProveedorClima APIDeMentiritas = new MockAPI(21,23,false);
	Sugeridor sugeridor = new Sugeridor(APIDeMentiritas);
	Evento evento = new Evento(LocalDateTime.of(LocalDate.of(2019, Month.MAY, 24),LocalTime.now()),sugeridor,new FrecuenciaUnicaVez(),"Ir a cenar");
	Evento evento2 = new Evento(LocalDateTime.of(LocalDate.of(2019, Month.AUGUST, 1),LocalTime.now()),sugeridor,new FrecuenciaUnicaVez(),"Ir a cenar");
	Usuario juan = new Usuario(TipoUsuario.PREMIUM,0);
	
	@Before
	public void setUp() {
		juan.agendarEvento(evento);
		juan.agendarEvento(evento2);
	}
	
	@Test (expected = UserException.class)
	public void NoSePuedenIngresarFechasInvalidas() {
		model.setFechaFin(201900529);
		model.setFechaInicio(20190801);
		model.listarEventos();
	}
	
	@Test (expected = UserException.class)
	public void NoSePuedeIngresarComoFechaInicialUnaFechaPosteriorALaFechaFinal() {
		model.setFechaFin(20190520);
		model.setFechaInicio(20190524);
		model.listarEventos();
	}
	
	
}
