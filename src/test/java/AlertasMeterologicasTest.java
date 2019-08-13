import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import domain.*;
import domain.apisClima.*;
import domain.enums.*;
import domain.frecuenciasDeEventos.FrecuenciaUnicaVez;


public class AlertasMeterologicasTest {
	ProveedorClima mock = new MockAPI(11,18,true);
	Sugeridor sugeridor = new Sugeridor(mock);
	Usuario juan = new Usuario(TipoUsuario.PREMIUM, 0);
	Guardarropa armario = new Guardarropa();
	Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.ALGODON).conColorPrimario(Color.ROJO).conColorSecundario(Color.AMARILLO).conAbrigo(0).crearPrenda();
	Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).conAbrigo(1).crearPrenda();
	Prenda ojotas =  new PrendaBuilder().conTipo(TipoPrenda.Ojotas).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).conAbrigo(-1).crearPrenda();
	Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.ALGODON).conAbrigo(0).crearPrenda();
	Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL).conAbrigo(1).crearPrenda();
	Prenda camperaGucci = new PrendaBuilder().conTipo(TipoPrenda.Campera).conTela(Material.ALGODON).conColorPrimario(Color.NEGRO).conAbrigo(2).crearPrenda();
	Prenda botas = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).conAbrigo(3).crearPrenda();
	Prenda pantalonAbrigo = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL).conAbrigo(3).crearPrenda();
	Prenda buzo =  new PrendaBuilder().conTipo(TipoPrenda.Buzo).conTela(Material.ALGODON).conColorPrimario(Color.ROSA).conColorSecundario(Color.AMARILLO).conAbrigo(2).crearPrenda();
	LocalDateTime fechaActual = LocalDateTime.of(2019,Month.MAY,17, 0,0);
	Evento eventoProximo = new Evento(sugeridor,new FrecuenciaUnicaVez(2019,5,24),"Sin descripcion");//"24-05-2019"
	Evento eventoNoProximo = new Evento(sugeridor,new FrecuenciaUnicaVez(2019,9,24),"Sin descripcion");//"24-05-2019"

	@Before
	public void setUp(){
		juan.agregarGuardarropa(armario);
		juan.cargarPrenda(armario, camisaCorta);
		juan.cargarPrenda(armario,zapatos);
		juan.cargarPrenda(armario,gorra);
		juan.cargarPrenda(armario,camperaGucci);
	}

	@Test
	public void siHayAlertaMeterologicaElEventoQueNoEsProximoNoDeberiaNotificarAlUsuario() {
		assertTrue(mock.alertaMeterologica());
		assertFalse(eventoNoProximo.alertaMeterologicaPara(fechaActual));
	}
	
}
