import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PrendaTest {

	Usuario juan = new Usuario(198,"JFQ8");
	Guardarropa armario = new Guardarropa();
	Prenda camisaRosa = new Prenda(Color.ROSA,Color.VERDE, TipoPrenda.CamisaMangaLarga, Material.ALGODON);
	Prenda remeraAzul = new Prenda(Color.AZUL,Color.VERDE, TipoPrenda.Remera, Material.ALGODON);
	
	@Before
	public void setUp() throws Exception {
		juan.agregarGuardarropa(armario);
	}

	@Test
	public void testPrenda() {
		//fail("Not yet implemented");
	}

	@Test
	public void testYaSeCargoLaPrendaSegun() {
		juan.cargarPrenda(armario, camisaRosa);
		assertTrue(camisaRosa.yaSeCargoLaPrendaSegun(juan));
	}
	
//	@Test
//	public void testMaterialDeTipoPrenda() {
//		//assertEquals(camisaRosa.materialDeTipoPrenda(), Material.ALGODON);
//	}

}
