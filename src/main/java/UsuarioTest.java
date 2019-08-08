import static org.junit.Assert.*;
import java.util.stream.*;
import java.time.*;
import java.util.*;
import org.junit.*;

import com.google.common.collect.Sets;

import domain.apisClima.*;
import domain.frecuenciasDeEventos.*;
import net.sf.oval.constraint.AssertTrue;
import domain.enums.*;
import domain.exceptions.*;
import domain.*;
import java.time.*;

public class UsuarioTest {

	ProveedorClima weatherAPI = new OpenWeatherMapAPI();
	ProveedorClima APIDeMentiritas = new MockAPI(21,23,false);
	Sugeridor sugeridor = new Sugeridor(APIDeMentiritas);
	Usuario juan = new Usuario(TipoUsuario.PREMIUM,0);
	Guardarropa armario = new Guardarropa();
	Guardarropa otroArmario = new Guardarropa();
	Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.ALGODON).conColorPrimario(Color.ROJO).conColorSecundario(Color.AMARILLO).crearPrenda();
	Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).crearPrenda();
	Prenda zapatillas = new PrendaBuilder().conTipo(TipoPrenda.Zapatillas).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).crearPrenda();
	Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.ALGODON).crearPrenda();
	Prenda sombrero= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.ALGODON).crearPrenda();
	Prenda camisaLarga = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaLarga).conColorPrimario(Color.BLANCO).conTela(Material.SATEN).crearPrenda();
	Prenda camisaDeLara = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaLarga).conColorPrimario(Color.BLANCO).conTela(Material.SATEN).crearPrenda();
	Prenda ojotas = new PrendaBuilder().conTipo(TipoPrenda.Ojotas).conTela(Material.CAUCHO).conColorPrimario(Color.NEGRO).crearPrenda();
	Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL).crearPrenda();
	Prenda pantalon = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.BLANCO).crearPrenda();
	Evento eventoConFrecuenciaUnica = new Evento(LocalDateTime.of(LocalDate.of(2019,Month.FEBRUARY,16),LocalTime.now()),sugeridor, new FrecuenciaUnicaVez(),"Sin descripcion");//Fecha "16-02-2019"
	//Evento eventoLoco = new Evento(new Date(119,1,16), juan,sugeridor, new FrecuenciaUnicaVez());//Fecha "16-02-2019"
	
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
	public void siJuanCargaUnJeanASuArmarioDeberiaTenerDoceAtuendos() {
		juan.cargarPrenda(armario, jean);
		assertEquals(armario.pedirAtuendosSegun(APIDeMentiritas,juan).size(), 12);	
	}
	
	@Test
	public void losAtuendosTienenUnoDeCadaTipo() {
	juan.cargarPrenda(armario, jean);
	assertTrue(armario
		.pedirAtuendosSegun(APIDeMentiritas,juan)
		.stream()
		.allMatch(atuendo->this.atuendoTieneCategoria(atuendo, Categoria.SUPERIOR) 
				&& this.atuendoTieneCategoria(atuendo, Categoria.INFERIOR)  
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
		assertTrue(otroArmario.prendas().size() == 1);
	}
	
	@Test
	public void siUnUsuarioClasificaUnaSugerenciaYLuegoLoDeshaceQuedaPendiente() {
		Set<Prenda> atuendo = new HashSet<Prenda>();
		atuendo.add(jean);
		atuendo.add(camisaCorta);
		atuendo.add(gorra);
		atuendo.add(zapatos);
		Sugerencia sugerencia = new Sugerencia(atuendo,eventoConFrecuenciaUnica);
		juan.agregarSugerencia(sugerencia);
		juan.clasificarUnaSugerencia(sugerencia, TipoSugerencias.ACEPTADA);
		juan.deshacerUltimaOperacionDeSugerencia();
		assertEquals(sugerencia.getEstado(),TipoSugerencias.PENDIENTE);
	}
	
	@Test
	public void dosUsuariosCompartenUnGuardarropa() {
		Usuario lara = new Usuario(TipoUsuario.PREMIUM,0);
		lara.agregarGuardarropa(armario);
		assertEquals(lara.getGuardarropas(), juan.getGuardarropas());
	}
	
//	public boolean interseccionAMano(ArrayList<Set<Prenda>> atuendo1, ArrayList<Set<Prenda>> atuendo2) {
//		boolean hayInterseccion = false;
//		int i=0, j=0;
////		for(i =0; i < atuendo1.size();i++) {
//			for(j=0; j< atuendo2.size();j++) {
//				hayInterseccion = atuendo1.contains(atuendo2.get(j));
//			}
////		}
//		
//		return hayInterseccion;
//	}
	
	public Set<Set<Prenda>> sugerirMasAceptarTodasLasSugerencias(Usuario usuario) {
		eventoConFrecuenciaUnica.sugerir(usuario);
		usuario.getSugerencias().stream().forEach(sugerencia -> usuario.clasificarUnaSugerencia(sugerencia, TipoSugerencias.ACEPTADA));
		return usuario.getSugerencias().stream().map(sugerencia -> sugerencia.getAtuendo()).collect(Collectors.toSet());
	}
	 
	@Test
	public void dosUsuariosCompartenUnGuardarropaYTienenSuficienteRopaParaHacerLosAtuendos() {
		juan.cargarPrenda(armario, jean);
		Set<Set<Prenda>> atuendosDeJuan = this.sugerirMasAceptarTodasLasSugerencias(juan);
		
		Usuario lara = new Usuario(TipoUsuario.PREMIUM,0);
		lara.agregarGuardarropa(armario);
		
		lara.cargarPrenda(armario, pantalon);
		lara.cargarPrenda(armario, camisaDeLara);
		lara.cargarPrenda(armario, sombrero);
		lara.cargarPrenda(armario, zapatillas);
		
		eventoConFrecuenciaUnica.sugerir(lara);
		Set<Set<Prenda>> atuendosDeLara = lara.getSugerencias().stream().map(sugerencia -> sugerencia.getAtuendo()).collect(Collectors.toSet());

		assertFalse(atuendosDeLara.containsAll(atuendosDeJuan));
	}
	
	@Test (expected = NoHayAtuendosDisponiblesException.class)
	public void unoDeLosUsuariosConGuardarropaCompartidoNoPuedeEjecutarSusAtuendosPorFaltaDeRopa() {
		juan.cargarPrenda(armario, jean);
		Usuario lara = new Usuario(TipoUsuario.PREMIUM,0);
		lara.agregarGuardarropa(armario);
		
		this.sugerirMasAceptarTodasLasSugerencias(juan);
		
		sugeridor.sugerirPrendasPara(lara);
		
	}
	
	@Test
	public void siNoSeAceptanLasSugerenciasConGuardarropaCompartidoSeGeneranLasMismasSugerencias() {
		juan.cargarPrenda(armario, jean);
		Usuario lara = new Usuario(TipoUsuario.PREMIUM,0);
		lara.agregarGuardarropa(armario);
		
		eventoConFrecuenciaUnica.sugerir(juan);
		Set<Set<Prenda>> atuendosDeJuan = juan.getSugerencias().stream().map(sugerencia -> sugerencia.getAtuendo()).collect(Collectors.toSet());
		eventoConFrecuenciaUnica.sugerir(lara);
		Set<Set<Prenda>> atuendosDeLara = lara.getSugerencias().stream().map(sugerencia -> sugerencia.getAtuendo()).collect(Collectors.toSet());
		
		assertTrue(atuendosDeJuan.equals(atuendosDeLara));
		
	}
	
}
