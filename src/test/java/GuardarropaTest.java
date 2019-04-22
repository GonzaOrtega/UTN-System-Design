import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GuardarropaTest {

	Usuario juan = new Usuario(198,"JFQ8");
	Guardarropa armario = new Guardarropa();
	PrendaBuilder builderPrenda = new PrendaBuilder();
	Prenda jean;
	
	
	@Before
	public void setUp() throws Exception {
		juan.agregarGuardarropa(armario);
		
		builderPrenda.conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.ALGODON).conColorPrimario(Color.ROJO).conColorSecundario(Color.AMARILLO);
		juan.cargarPrenda(armario, builderPrenda.crearPrenda());
		
		builderPrenda.conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO);
		juan.cargarPrenda(armario, builderPrenda.crearPrenda());
		
		builderPrenda.conTipo(TipoPrenda.Gorra).conTela(Material.MICROFIBRA);
		juan.cargarPrenda(armario, builderPrenda.crearPrenda());
		
		builderPrenda.conTipo(TipoPrenda.CamisaMangaLarga);
		juan.cargarPrenda(armario, builderPrenda.crearPrenda());
		
		builderPrenda.conTipo(TipoPrenda.Ojotas).conTela(Material.CHIFFON);
		juan.cargarPrenda(armario, builderPrenda.crearPrenda());
		
		builderPrenda.conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL);
		jean =  builderPrenda.crearPrenda(); //no lo cargo al guardarropas
		
		
	}
	
	
	@Test
	public void testCantidadDePrendasGuardadas() {	
		assertEquals(armario.cantidadDePrendasGuardadas(), 5);
	}
	
	@Test
	public void testNoHayAtuendos() {
		assertTrue(armario.devolverAtuendos().isEmpty());
	}
	
	@Test
	public void testAgregarPrenda() {
		armario.agregarPrenda(jean);
		assertEquals(armario.cantidadDePrendasGuardadas(), 6);
	}
	
	@Test
	public void testAtuendos() {
		juan.cargarPrenda(armario, jean);
		assertEquals(armario.devolverAtuendos().size(), 4);
	}

}
