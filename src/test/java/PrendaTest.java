import static org.junit.Assert.*;
import org.junit.Test;

public class PrendaTest {

	@Test (expected = TieneParametrosNulosException.class)
	public void noSePuedeCrearPrendasConParametrosNulos() {
		new PrendaBuilder().conTipo(TipoPrenda.Calza).crearPrenda();
	}
	
	@Test (expected = MaterialNoPermitidoException.class)
	public void noSePuedenCrearPrendasConMaterialesInconsistentes() {
		new PrendaBuilder().conTipo(TipoPrenda.Remera).conColorPrimario(Color.AZUL).conTela(Material.CUERO).crearPrenda();
	}
	
	@Test
	public void resultaValidoLaCreacionDePrendasSinColorSecundario(){
		Prenda remeraRosa = new PrendaBuilder().conColorPrimario(Color.ROSA).conTipo(TipoPrenda.Remera).conTela(Material.ALGODON).crearPrenda();
		assertEquals(remeraRosa.colorSecundario,null);
	}
	
	@Test (expected = TieneParametrosNulosException.class)
	public void noSePuedeAsignarLaTelaSinSaberElTipo(){
		new PrendaBuilder().conColorPrimario(Color.ROSA).conTela(Material.ALGODON).conTipo(TipoPrenda.Remera).crearPrenda();
	}
	
}
