import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import domain.apisClima.*;
import domain.enums.*;
import domain.exceptions.*;
import domain.*;
public class GuardarropaTest {
	OpenWeatherMapAPI weatherAPI = new OpenWeatherMapAPI();
	ProveedorClima APIDeMentiritas = new MockAPI(21,23,false);
	ProveedorClima APIDeMentiritasDeInvierno = new MockAPI(10,19,false);
	ProveedorClima APIMockInvierno = new MockAPI(10,12,false);
	ProveedorClima APIMockTemplado = new MockAPI(16,16,false);
	Sugeridor sugeridor = new Sugeridor(APIDeMentiritas);
	Usuario juan = new Usuario(TipoUsuario.PREMIUM, 0);
	Guardarropa armario = new Guardarropa();
	Guardarropa otroArmario = new Guardarropa();
	Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.ALGODON).conColorPrimario(Color.ROJO).conColorSecundario(Color.AMARILLO).conAbrigo(0).crearPrenda();
	Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).conAbrigo(1).crearPrenda();
	Prenda ojotas =  new PrendaBuilder().conTipo(TipoPrenda.Ojotas).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).conAbrigo(-1).crearPrenda();
	Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.ALGODON).conAbrigo(0).crearPrenda();
	Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL).conAbrigo(1).crearPrenda();
	Prenda camperaGucci = new PrendaBuilder().conTipo(TipoPrenda.Campera).conTela(Material.ALGODON).conColorPrimario(Color.NEGRO).conAbrigo(2).crearPrenda();
	Prenda botas = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).conAbrigo(3).crearPrenda();
	Prenda pantalonAbrigo = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL).conAbrigo(3).crearPrenda();
	Prenda buzo =  new PrendaBuilder().conTipo(TipoPrenda.Buzo).conTela(Material.ALGODON).conColorPrimario(Color.ROSA).conColorSecundario(Color.AMARILLO).conAbrigo(2).crearPrenda();
	@Before
	public void setUp(){
		juan.agregarGuardarropa(armario);
		juan.agregarGuardarropa(otroArmario);
		juan.cargarPrenda(armario, camisaCorta);
		juan.cargarPrenda(armario,zapatos);
		juan.cargarPrenda(armario,gorra);
		juan.cargarPrenda(armario,camperaGucci);
	}


	@Test (expected = YaSeEncuentraCargadaException.class)
	public void entreArmariosNoSePuedeCompartirPrenda() {
		juan.cargarPrenda(otroArmario,camisaCorta);
	}

	@Test
	public void elArmarioNoDebeDevolverAtuendosSiNoTieneUnaPrendaPorCadaCategoria() {
		assertTrue(armario.pedirAtuendosSegun(APIDeMentiritas,juan).isEmpty());
	}
	
	@Test (expected = YaSeEncuentraCargadaException.class)
	public void noPuedenHaberPrendasRepetidasEnUnArmario() {
		juan.cargarPrenda(armario, camisaCorta);
		juan.cargarPrenda(armario, camisaCorta);

	}
	
	@Test
	public void siJuanSolicitaSusAtuendosSeObtendranDosConOSinAccesorio() {
		HashSet<Prenda> atuendo = new HashSet<Prenda>(Arrays.asList(jean,gorra,zapatos,camisaCorta));
		HashSet<Prenda> atuendo2 = new HashSet<Prenda>(Arrays.asList(jean,zapatos,camisaCorta));
//		camisaCorta.setEsBase(true);//esto hay que cambiar despues
		juan.cargarPrenda(armario, jean);
		assertTrue(armario.pedirAtuendosSegun(APIDeMentiritas,juan).contains(atuendo)
				&& armario.pedirAtuendosSegun(APIDeMentiritas,juan).contains(atuendo2));
	}
	@Test
	public void siJuanCalificaFrioEnLosPiesNoDarleOjotas() {
		HashSet<Prenda> atuendo = new HashSet<Prenda>(Arrays.asList(jean,gorra,zapatos,camisaCorta));
		HashSet<Prenda> atuendo2 = new HashSet<Prenda>(Arrays.asList(jean,ojotas,camisaCorta));
		juan.calificar(Categoria.CALZADO,TipoSensaciones.FRIO);
		juan.calificar(Categoria.CALZADO,TipoSensaciones.FRIO);

		juan.cargarPrenda(armario, jean);
		juan.cargarPrenda(armario,ojotas);
		assertTrue(armario.pedirAtuendosSegun(APIDeMentiritas,juan).contains(atuendo)
				&& !armario.pedirAtuendosSegun(APIDeMentiritas,juan).contains(atuendo2));
	}
	@Test
	public void calificacionesFrio() {
		juan.calificar(Categoria.CALZADO,TipoSensaciones.FRIO);
		juan.calificar(Categoria.CALZADO,TipoSensaciones.FRIO);
		juan.calificar(Categoria.CALZADO,TipoSensaciones.FRIO);
		juan.calificar(Categoria.CALZADO,TipoSensaciones.FRIO);
		assertEquals(juan.getCalificaciones().size(),4);

	}
	@Test
	public void siJuanCalificaFrioEnTorsoDarleMasAbrgio() {
		HashSet<Prenda> atuendo = new HashSet<Prenda>(Arrays.asList(pantalonAbrigo,buzo,botas,camisaCorta,camperaGucci));
		HashSet<Prenda> atuendo2 = new HashSet<Prenda>(Arrays.asList(pantalonAbrigo,botas,camisaCorta,camperaGucci));
		juan.cargarPrenda(armario, pantalonAbrigo);
		juan.cargarPrenda(armario, botas);
		juan.cargarPrenda(armario,buzo);
		juan.calificar(Categoria.SUPERIOR,TipoSensaciones.FRIO);

		assertTrue(armario.pedirAtuendosSegun(APIMockInvierno,juan).contains(atuendo)
				&& !armario.pedirAtuendosSegun(APIMockInvierno,juan).contains(atuendo2));
	}
	@Test
	public void siJuanCalificaFrioEnPantalonesDarleMasAbrigoYCalorEnSuperiorDarleMenosAbrigo() {
		HashSet<Prenda> atuendo = new HashSet<Prenda>(Arrays.asList(pantalonAbrigo,zapatos,camisaCorta,camperaGucci));
		HashSet<Prenda> atuendo2 = new HashSet<Prenda>(Arrays.asList(jean,zapatos,camisaCorta,camperaGucci,buzo));
		HashSet<Prenda> atuendo3 = new HashSet<Prenda>(Arrays.asList(pantalonAbrigo,zapatos,camisaCorta,camperaGucci,buzo));

		juan.cargarPrenda(armario, pantalonAbrigo);
		juan.cargarPrenda(armario, jean);
		juan.cargarPrenda(armario, buzo);
		juan.calificar(Categoria.INFERIOR,TipoSensaciones.FRIO);
		juan.calificar(Categoria.SUPERIOR,TipoSensaciones.CALOR);

		assertTrue(armario.pedirAtuendosSegun(APIMockTemplado,juan).contains(atuendo)
				&& !armario.pedirAtuendosSegun(APIMockTemplado,juan).contains(atuendo2)
				&& !armario.pedirAtuendosSegun(APIMockTemplado,juan).contains(atuendo3));
	}
	
	
}
