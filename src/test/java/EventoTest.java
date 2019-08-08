import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import domain.apisClima.*;
import domain.enums.*;
import domain.exceptions.*;
import domain.frecuenciasDeEventos.*;
import domain.*;

import java.time.*;

public class EventoTest {
	ProveedorClima APIDeMentiritas = new MockAPI(21,23,false);
	Sugeridor sugeridor = new Sugeridor(APIDeMentiritas);
	Usuario juan = new Usuario(TipoUsuario.PREMIUM,0);
	Guardarropa armario = new Guardarropa();
	Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.ALGODON).conColorPrimario(Color.ROJO).conColorSecundario(Color.AMARILLO).conAbrigo(0).crearPrenda();
	Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).conAbrigo(0).crearPrenda();
	Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.ALGODON).conAbrigo(0).crearPrenda();
	Prenda camisaLarga = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaLarga).conColorPrimario(Color.BLANCO).conTela(Material.SATEN).conAbrigo(0).crearPrenda();
	Prenda ojotas = new PrendaBuilder().conTipo(TipoPrenda.Ojotas).conTela(Material.CAUCHO).conColorPrimario(Color.NEGRO).conAbrigo(0).crearPrenda();
	Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL).conAbrigo(0).crearPrenda();
	Evento eventoLoco = new Evento(LocalDateTime.of(LocalDate.of(2019, Month.FEBRUARY, 16),LocalTime.now()),sugeridor,new FrecuenciaUnicaVez(),"Sin descripcion");//la fecha es:"16-02-2019"
	Evento eventoConFrecuenciaUnica = new Evento(LocalDateTime.of(LocalDate.of(2019, Month.MAY, 24),LocalTime.now()),sugeridor,new FrecuenciaUnicaVez(),"Sin descripcion");//"24-05-2019"
	Evento eventoConFrecuenciaDiaria = new Evento(LocalDateTime.of(LocalDate.of(2019, Month.JANUARY, 16),LocalTime.of(0, 0)),sugeridor,new FrecuenciaDiaria(),"Sin descripcion");//"16-01-2019"
	Evento eventoConFrecuenciaSemanal = new Evento(LocalDateTime.of(LocalDate.of(2019, Month.JANUARY, 16),LocalTime.of(0, 0)),sugeridor,new FrecuenciaSemanal(),"Sin descripcion");//"16-01-2019"
	Evento eventoConFrecuenciaMensual = new Evento(LocalDateTime.of(LocalDate.of(2019, Month.JANUARY, 16),LocalTime.of(0, 0)),sugeridor,new FrecuenciaMensual(),"Sin descripcion");//"16-01-2019"
	Evento eventoConFrecuenciaAnual = new Evento(LocalDateTime.of(LocalDate.of(2019, Month.FEBRUARY, 16),LocalTime.of(0, 0)),sugeridor,new FrecuenciaAnual(),"Sin descripcion");//"16-01-2019"

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
		assertTrue(
				eventoConFrecuenciaUnica.esProximo(
						LocalDateTime.of(
								LocalDate.of(2019, Month.MAY, 17),
								LocalTime.now())
						)
				);
	}
	
	@Test
	public void ProximidadEntreFechasDiferentesLajanasDevuelveFalso() {
		assertFalse(eventoConFrecuenciaUnica.esProximo(
						LocalDateTime.of(
								LocalDate.of(2019, Month.MAY, 16),
								LocalTime.now())
						)
				);
		assertFalse(eventoConFrecuenciaUnica.esProximo(
				LocalDateTime.of(
						LocalDate.of(2018, Month.MAY, 24),
						LocalTime.now())
				)
		);
	}
	
	@Test 
	public void siUnEventoPideSugerenciasParaJuanElMismoTendra12Sugerencias(){
		eventoLoco.sugerir(juan);
		assertEquals(juan.getSugerencias().size(),12); //Revisar esto que me parece raro
	}
	
	@Test 
	public void CrearEventoDiarioCuandoFaltenOchoHorasTieneQueSerProximo(){//independientemente del mes a�o etc
		LocalDateTime _20190215 = LocalDateTime.of(2019, Month.FEBRUARY, 15, 23, 0);
		LocalDateTime _20180115 = LocalDateTime.of(2018, Month.JANUARY,15,23,0);
		LocalDateTime _20190123 = LocalDateTime.of(2019, Month.JANUARY,23,23,0);
		LocalDateTime _20190116 = LocalDateTime.of(2019, Month.JANUARY,16,1,0);
		LocalDateTime _20190116bis = LocalDateTime.of(2019, Month.JANUARY,16,16,0);

		assertTrue(eventoConFrecuenciaDiaria.esProximo(_20190215));
		assertTrue(eventoConFrecuenciaDiaria.esProximo(_20180115)); // 23hs
		assertTrue(eventoConFrecuenciaDiaria.esProximo(_20190123)); // 23hs
		assertFalse(eventoConFrecuenciaDiaria.esProximo(_20190116)); // 1hs
		assertTrue(eventoConFrecuenciaDiaria.esProximo(_20190116bis)); // 16hs
	}
	
	@Test 
	public void CrearEventoSemanalCuandoFaltenDosDiasTieneQueSerProximo(){//independientemente del mes a�o etc
		LocalDateTime _20190213 = LocalDateTime.of(2019, Month.FEBRUARY, 13, 23, 0);
		LocalDateTime _20190807 = LocalDateTime.of(2019, Month.AUGUST, 7, 23, 0);
		LocalDateTime _20180116 = LocalDateTime.of(2018, Month.JANUARY,16,23,0);
		LocalDateTime _20190123 = LocalDateTime.of(2019, Month.JANUARY,23,23,0);
		LocalDateTime _20190114 = LocalDateTime.of(2019, Month.JANUARY,14,1,0);
		LocalDateTime _20190118 = LocalDateTime.of(2019, Month.JANUARY,18,16,0);
		assertTrue(eventoConFrecuenciaSemanal.esProximo(_20190213));
		assertTrue(eventoConFrecuenciaSemanal.esProximo(_20190807));
		assertTrue(eventoConFrecuenciaSemanal.esProximo(_20180116));
		assertTrue(eventoConFrecuenciaSemanal.esProximo(_20190123));
		assertTrue(eventoConFrecuenciaSemanal.esProximo(_20190114));
		assertFalse(eventoConFrecuenciaSemanal.esProximo(_20190118));
	}
	@Test 
	public void CrearEventoMesualCuandoFaltenCincoDiasTieneQueSerProximo(){
		LocalDateTime _20190216 = LocalDateTime.of(2019, Month.FEBRUARY, 16, 23, 0);
		LocalDateTime _20180116 = LocalDateTime.of(2018, Month.JANUARY,16,23,0);
		LocalDateTime _20190111 = LocalDateTime.of(2019, Month.JANUARY,11,1,0);
		LocalDateTime _20190118 = LocalDateTime.of(2019, Month.JANUARY,18,16,0);
	
		assertTrue(eventoConFrecuenciaMensual.esProximo(_20190216));
		assertTrue(eventoConFrecuenciaMensual.esProximo(_20180116));
		assertTrue(eventoConFrecuenciaMensual.esProximo(_20190111));
		assertFalse(eventoConFrecuenciaMensual.esProximo(_20190118));
	}
	
	@Test 
	public void CrearEventoAnualCuandoFaltenTreintaDiasTieneQueSerProximo(){
		LocalDateTime _20180216 = LocalDateTime.of(2018, Month.FEBRUARY,16,23,0);
		LocalDateTime _20190128 = LocalDateTime.of(2019, Month.JANUARY,28,23,0);
		LocalDateTime _20250211 = LocalDateTime.of(2025, Month.FEBRUARY,11,1,0);
		LocalDateTime _20190118 = LocalDateTime.of(2019, Month.FEBRUARY,18,16,0);

		assertTrue(eventoConFrecuenciaAnual.esProximo(_20180216));
		assertTrue(eventoConFrecuenciaAnual.esProximo(_20190128));
		assertTrue(eventoConFrecuenciaAnual.esProximo(_20250211));
		assertFalse(eventoConFrecuenciaAnual.esProximo(_20190118));
	}
	
}
