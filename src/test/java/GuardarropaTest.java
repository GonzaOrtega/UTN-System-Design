import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class GuardarropaTest {

	OpenWeatherMapAPI weatherAPI = new OpenWeatherMapAPI();
	Sugeridor sugeridor = new Sugeridor();
	Usuario juan = new Usuario(TipoUsuario.PREMIUM, 0);
	Guardarropa armario = new Guardarropa(weatherAPI);
	Guardarropa otroArmario = new Guardarropa(weatherAPI);
	Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.ALGODON).conColorPrimario(Color.ROJO).conColorSecundario(Color.AMARILLO).crearPrenda();
	Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).crearPrenda();
	Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.ALGODON).crearPrenda();
	Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL).crearPrenda();
	Prenda camperaGucci = new PrendaBuilder().conTipo(TipoPrenda.Campera).conTela(Material.ALGODON).conColorPrimario(Color.NEGRO).crearPrenda();
	
	@Before
	public void setUp(){
		juan.agregarGuardarropa(armario);
		juan.agregarGuardarropa(otroArmario);
		juan.cargarPrenda(armario, camisaCorta);
		juan.cargarPrenda(armario,zapatos);
		juan.cargarPrenda(armario,gorra);
		juan.cargarPrenda(armario,camperaGucci);
	}


	@Test (expected = YaSeEncuentraCargadaException.class)
	public void entreArmariosNoSePuedeCompartirPrenda() {
		juan.cargarPrenda(otroArmario,camisaCorta);
	}

	@Test
	public void elArmarioNoDebeDevolverAtuendosSiNoTieneUnaPrendaPorCadaCategoria() {
		assertTrue(armario.pedirAtuendos().isEmpty());
	}
	
	@Test (expected = YaSeEncuentraCargadaException.class)
	public void noPuedenHaberPrendasRepetidasEnUnArmario() {
		juan.cargarPrenda(armario, camisaCorta);
		juan.cargarPrenda(armario, camisaCorta);

	}
	
	@Test
	public void siJuanSolicitaSusAtuendosSeObtendraUnoSoloConDistintasPrendas() {
		HashSet<Prenda> atuendo = new HashSet<Prenda>(Arrays.asList(jean,gorra,zapatos,camisaCorta));
		HashSet<HashSet<Prenda>> atuendosEsperados = new HashSet<HashSet<Prenda>>(Arrays.asList(atuendo));
		juan.cargarPrenda(armario, jean);
		armario.setTemp(25);  // Problema con la temperatura
		assertEquals(sugeridor.sugerirPrendasPara(juan),atuendosEsperados);
	}
	@Test
	public void siJuanSolicitaSusAtuendosSeObtendraUnoSoloConDistintasPrendasAhoraConFrio() {
		HashSet<Prenda> atuendo = new HashSet<Prenda>(Arrays.asList(jean,gorra,zapatos,camisaCorta,camperaGucci));
		HashSet<HashSet<Prenda>> atuendosEsperados = new HashSet<HashSet<Prenda>>(Arrays.asList(atuendo));
		juan.cargarPrenda(armario, jean);
		armario.setTemp(10);
		assertEquals(sugeridor.sugerirPrendasPara(juan),atuendosEsperados);
	}
	
	
}
