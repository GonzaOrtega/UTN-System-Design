import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class GuardarropaTest {

	Usuario juan = new Usuario(198,"JFQ8");
	Guardarropa armario = new Guardarropa();
	Guardarropa otroArmario = new Guardarropa();
	Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.ALGODON).conColorPrimario(Color.ROJO).conColorSecundario(Color.AMARILLO).crearPrenda();
	Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).crearPrenda();
	Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.ALGODON).crearPrenda();
	Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL).crearPrenda();
	HashSet<Prenda> atuendo = new HashSet<Prenda>(Arrays.asList(jean,gorra,zapatos,camisaCorta));
	HashSet<HashSet<Prenda>> atuendosEsperados = new HashSet<HashSet<Prenda>>(Arrays.asList(atuendo));
	
	@Before
	public void setUp(){
		juan.agregarGuardarropa(armario);
		juan.agregarGuardarropa(otroArmario);
		juan.cargarPrenda(armario, camisaCorta);
		juan.cargarPrenda(armario,zapatos);
		juan.cargarPrenda(armario,gorra);
	}


	@Test (expected = YaSeEncuentraCargadaException.class)
	public void entreArmariosNoSePuedeCompartirPrenda() {
		juan.cargarPrenda(otroArmario,camisaCorta);
	}

	@Test
	public void elArmarioNoDebeDevolverAtuendosSiNoTieneUnaPrendaPorCadaCategoria() {
		assertTrue(armario.devolverAtuendos().isEmpty());
	}
	
	@Test (expected = YaSeEncuentraCargadaException.class)
	public void noPuedenHaberPrendasRepetidasEnUnArmario() {
		juan.cargarPrenda(armario, camisaCorta);
		juan.cargarPrenda(armario, camisaCorta);

	}
	
	@Test
	public void siJuanSolicitaSusAtuendosSeObtendraUnoSoloConDistintasPrendas() {
		juan.cargarPrenda(armario, jean);
		assertEquals(juan.pedirAtuendo(),atuendosEsperados);
	}
	
	
}
