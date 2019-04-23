import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GuardarropaTest {

	Usuario juan = new Usuario(198,"JFQ8");
	Guardarropa armario = new Guardarropa();
	Guardarropa otroArmario = new Guardarropa();
	Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.ALGODON).conColorPrimario(Color.ROJO).conColorSecundario(Color.AMARILLO).crearPrenda();
	Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).crearPrenda();
	Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.ALGODON).crearPrenda();
	Prenda camisaLarga = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaLarga).conColorPrimario(Color.BLANCO).conTela(Material.SATEN).crearPrenda();
	Prenda ojotas = new PrendaBuilder().conTipo(TipoPrenda.Ojotas).conTela(Material.CAUCHO).conColorPrimario(Color.NEGRO).crearPrenda();
	Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL).crearPrenda();

	@Before
	public void setUp(){
		juan.agregarGuardarropa(armario);
		juan.agregarGuardarropa(otroArmario);
		juan.cargarPrenda(armario, camisaCorta);
		juan.cargarPrenda(armario,zapatos);
		juan.cargarPrenda(armario,gorra);
		juan.cargarPrenda(armario,camisaLarga);
		juan.cargarPrenda(armario,ojotas);
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
}
