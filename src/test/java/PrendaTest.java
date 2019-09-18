import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import org.junit.Test;

import domain.enums.*;
import domain.exceptions.*;
import domain.*;

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
		assertEquals(remeraRosa.getColorSecundario(),null);
	}
	
	@Test (expected = TieneParametrosNulosException.class)
	public void noSePuedeAsignarLaTelaSinSaberElTipo(){
		new PrendaBuilder().conColorPrimario(Color.ROSA).conTela(Material.ALGODON).conTipo(TipoPrenda.Remera).crearPrenda();
	}
	@Test 
	public void crearPrendaConAtributosNoNulos() {
		Prenda remeraRosa = new PrendaBuilder().conColorPrimario(Color.ROSA).conTipo(TipoPrenda.Remera).conTela(Material.ALGODON).crearPrenda();
		assertTrue((remeraRosa.getColorPrimario()!=null) &&(remeraRosa.getTipo()!=null)&&(remeraRosa.getTela()!=null));
	}
	
	@Test
	public void crearUnaPrendaConFoto() throws NoSePuedeAbrirImagen {
		Foto imagenDeRemeraNegra = new Foto(new File("src/test/java/remeranegra.jpg"));
		Prenda remeraNegra = new PrendaBuilder().conColorPrimario(Color.NEGRO).conTipo(TipoPrenda.Remera).conTela(Material.ALGODON).conFoto(imagenDeRemeraNegra).conAbrigo(0).crearPrenda();
		assertEquals(remeraNegra.getFoto(),imagenDeRemeraNegra);
	}
	
	@Test (expected = NoSePuedeAbrirImagen.class)
	public void noSePodranCrearFotosSiNoSeLeeCorrectamenteElArchivo() throws IOException {
		new Foto(new File("remerarosa.jpg"));
	}
	@Test (expected = TieneParametrosNulosException.class)
	public void noSePodranCrearPrendasSinNivelAbrigo() {
		new PrendaBuilder().conColorPrimario(Color.ROSA).conTela(Material.ALGODON).conTipo(TipoPrenda.Remera).crearPrenda();
}
}
