import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {

	Usuario juan = new Usuario(198,"JFQ8");
	Guardarropa armario = new Guardarropa();
	Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.ALGODON).conColorPrimario(Color.ROJO).conColorSecundario(Color.AMARILLO).crearPrenda();
	Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).crearPrenda();
	Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.ALGODON).crearPrenda();
	Prenda camisaLarga = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaLarga).conColorPrimario(Color.BLANCO).conTela(Material.SATEN).crearPrenda();
	Prenda ojotas = new PrendaBuilder().conTipo(TipoPrenda.Ojotas).conTela(Material.CAUCHO).conColorPrimario(Color.NEGRO).crearPrenda();
	Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL).crearPrenda();

	@Before
	public void setUp(){
		juan.agregarGuardarropa(armario);
	}

	@Test
	public void juanTendraUnGuardarropa() {
		assertEquals(juan.getGuardarropas().size(), 1);
	}
	
	@Test (expected = YaSeEncuentraCargadaException.class)
	public void noSeEncuentraPermitidoIngresarUnaMismaPrendaAUnArmario()  {
		juan.cargarPrenda(armario,camisaLarga);
		juan.cargarPrenda(armario,camisaLarga);
	}
	
	@Test
	public void juanDebeTenerCincoPrendas() {
		assertEquals(armario.cantidadDePrendasGuardadas(), 5);
	}
	
	@Test
	public void siJuanAgregaUnJeanASusPrendasTendraSeis(){
		armario.agregarPrenda(jean);
		assertEquals(armario.cantidadDePrendasGuardadas(), 6);
	}
	
	@Test
	public void siJuanAgregaUnJeanSuArmarioDeberiaDevolverleCuatroAtuendos() {
		juan.cargarPrenda(armario, jean);
		assertEquals(armario.devolverAtuendos().size(), 4);
	}


}
