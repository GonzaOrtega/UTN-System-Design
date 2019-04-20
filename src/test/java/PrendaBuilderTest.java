import static org.junit.Assert.*;

//import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
public class PrendaBuilderTest {

	@Test
	public void testNoCargarPrendasConParametrosNulos() {
		try {
			new PrendaBuilder().crearPrenda();
			fail();
		}catch(TieneParametrosNulosException exception) {
			assertThat(exception.getMessage(), is("WARNING: no se pudo crear la prenda ya que tiene parametros sin instanciar."));
		}	
	}
	@Test
	public void testTelaNoValidaParaElTipo() {
		try {
			PrendaBuilder prendaBuilder = new PrendaBuilder();
			prendaBuilder = prendaBuilder.conTipo(TipoPrenda.Remera);
			prendaBuilder.conTela(Material.CUERO);
			fail();
		}catch(MaterialNoPermitidoException exception) {
			Material tela= Material.CUERO;
			assertThat(exception.getMessage(), is("WARNING: el material " + tela + " no es valido para el tipo de prenda ingresado."));
		}
	}
	@Test
	public void testCargarTipoDespuesTela() {
		PrendaBuilder prendaBuilder = new PrendaBuilder();
		try {
			prendaBuilder = prendaBuilder.conTela(Material.CUERO);
			fail();
		}catch(MaterialNoPermitidoException exception) {
			assertThat(exception.getMessage(), is("WARNING: debe definir primero el tipo."));
		}
	}
	@Test
	public void testCargarPrendaConParametrosNoNulos(){
		PrendaBuilder prendaBuilder = new PrendaBuilder();
		prendaBuilder = prendaBuilder.conColorPrimario(Color.AMARILLO).conTipo(TipoPrenda.Remera).conTela(Material.ALGODON).conColorSecundario(Color.AZUL);
		assertTrue(prendaBuilder.crearPrenda() != null);
		assertTrue(prendaBuilder.crearPrenda().colorSecundario!=null);
	}
}
