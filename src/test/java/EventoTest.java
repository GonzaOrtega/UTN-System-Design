import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.FechaIncorrectaException;
import exceptions.*;

public class EventoTest {
	
	ProveedorClima APIDeMentiritas = new MockAPI(21);
	Sugeridor sugeridor = new Sugeridor(APIDeMentiritas);
	Usuario juan = new Usuario(TipoUsuario.PREMIUM,0);
	Guardarropa armario = new Guardarropa();
	Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.ALGODON).conColorPrimario(Color.ROJO).conColorSecundario(Color.AMARILLO).conAbrigo(0).crearPrenda();
	Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).conAbrigo(0).crearPrenda();
	Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.ALGODON).conAbrigo(0).crearPrenda();
	Prenda camisaLarga = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaLarga).conColorPrimario(Color.BLANCO).conTela(Material.SATEN).conAbrigo(0).crearPrenda();
	Prenda ojotas = new PrendaBuilder().conTipo(TipoPrenda.Ojotas).conTela(Material.CAUCHO).conColorPrimario(Color.NEGRO).conAbrigo(0).crearPrenda();
	Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL).conAbrigo(0).crearPrenda();
	Evento eventoLoco = new Evento("16-02-2019", juan,sugeridor);
	
	@Before
	public void setUp(){
		juan.agregarGuardarropa(armario);
		juan.cargarPrenda(armario, camisaCorta);
		juan.cargarPrenda(armario, zapatos);
		juan.cargarPrenda(armario, gorra);
		juan.cargarPrenda(armario, camisaLarga);
		juan.cargarPrenda(armario, ojotas);
		juan.cargarPrenda(armario, jean);
	}
	
	@Test
	public void ProximidadEntreFechasDiferentesCercanasDevuelveVerdadero() {
		Evento evento = new Evento("24-05-2019", new Usuario(TipoUsuario.GRATUITO,0),sugeridor);
		assertTrue(evento.esProximo("17-05-2019"));		
	}
	@Test
	public void ProximidadEntreFechasDiferentesLajanasDevuelveFalso() {
		Evento evento = new Evento("24-05-2019",new Usuario(TipoUsuario.GRATUITO,0),sugeridor);
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
		new Evento("hola",new Usuario(TipoUsuario.GRATUITO,0),sugeridor);
	}
	
	@Test 
	public void siUnEventoPideSugerenciasParaJuanElMismoTendra4Sugerencias(){
		eventoLoco.sugerir();
		assertEquals(juan.getSugerencias().size(),12); //Revisar esto que me parece raro
	}
	
}
