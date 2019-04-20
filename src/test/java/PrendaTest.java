import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PrendaTest {

	Usuario juan = new Usuario(198,"JFQ8");
	Guardarropa armario = new Guardarropa();
	PrendaBuilder builderCamisaRosa = new PrendaBuilder().conColorPrimario(Color.ROSA).conColorSecundario(Color.VERDE).conTipo(TipoPrenda.CamisaMangaLarga).conTela(Material.ALGODON);
	Prenda camisaRosa = builderCamisaRosa.crearPrenda();
	PrendaBuilder builderRemeraAzul = new PrendaBuilder().conColorPrimario(Color.AZUL).conColorSecundario(Color.VERDE).conTipo(TipoPrenda.Remera).conTela(Material.ALGODON);
	Prenda remeraAzul = builderRemeraAzul.crearPrenda();
	
	@Before
	public void setUp() throws Exception {
		juan.agregarGuardarropa(armario);
	}
	@Test
	public void testYaSeCargoLaPrendaSegun(){
		juan.cargarPrenda(armario, camisaRosa);
		assertTrue(camisaRosa.yaSeCargoLaPrendaSegun(juan));
	}
	@Test
	public void testNoSeCargoPrenda(){
		assertFalse(camisaRosa.yaSeCargoLaPrendaSegun(juan));
	}
}
