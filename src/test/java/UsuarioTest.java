import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {

	Sugeridor sugeridor = new Sugeridor();
	Usuario juan = new Usuario(TipoUsuario.PREMIUM,0);
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
		juan.cargarPrenda(armario, camisaCorta);
		juan.cargarPrenda(armario, zapatos);
		juan.cargarPrenda(armario, gorra);
		juan.cargarPrenda(armario, camisaLarga);
		juan.cargarPrenda(armario, ojotas);
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
	public void siSeAgregaUnJeanASusPrendasHabraSeis(){
		armario.cargarPrenda(jean);
		assertEquals(armario.cantidadDePrendasGuardadas(), 6);
	}
	
	@Test
	public void siJuanCargaUnJeanASuArmarioDeberiaTenerCuatroAtuendos() {
		juan.cargarPrenda(armario, jean);
		assertEquals(armario.pedirAtuendos().size(), 4);	
	}
	@Test
	public void losAtuendosTienenUnoDeCadaTipo() {
	juan.cargarPrenda(armario, jean);
	assertTrue(armario
		.pedirAtuendos()
		.stream()
		.allMatch(atuendo->this.atuendoTieneCategoria(atuendo, Categoria.SUPERIOR) 
				&& this.atuendoTieneCategoria(atuendo, Categoria.INFERIOR) 
				&&this.atuendoTieneCategoria(atuendo, Categoria.ACCESORIO) 
				&&this.atuendoTieneCategoria(atuendo, Categoria.CALZADO)));
	}
	public boolean atuendoTieneCategoria(Set<Prenda> unAtuendo, Categoria unaCategoria){
		return unAtuendo
				.stream()
				.anyMatch(prenda->this.prendaDeCategoria(prenda,unaCategoria));
	}
	public boolean prendaDeCategoria(Prenda unaPrenda,Categoria categoria) {
		return unaPrenda.getTipo().categoria == categoria;
	}
	@Test (expected = NoHayAtuendosDisponiblesException.class)
	public void siLaraPideUnAtuendoPeroNoTienePrendasSuficientesLanzaExcepcion(){
		Usuario lara = new Usuario(TipoUsuario.PREMIUM,0);
		sugeridor.sugerirPrendasPara(lara);
	}
	@Test(expected = SeExcedioElLimiteDeCapacidadDelGuardarropaException.class)
	public void siSeIntentaCargarMasCantidadDePrendasDeLaPermitidaLanzaException(){
		Usuario lara = new Usuario(TipoUsuario.GRATUITO,1);
		lara.agregarGuardarropa(otroArmario);
		lara.cargarPrenda(otroArmario, camisaCorta);
		lara.cargarPrenda(otroArmario, zapatos);
	}
	@Test 
	public void siUnUsuarioGratuitoNoSePasaDeLaCantidadPermitidaPuedeCargarPrenda(){
		Usuario lara = new Usuario(TipoUsuario.GRATUITO,1);
		lara.agregarGuardarropa(otroArmario);
		lara.cargarPrenda(otroArmario, camisaCorta);
		assertTrue(otroArmario.prendas.size() == 1);
	}
}
