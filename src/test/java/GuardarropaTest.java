import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import domain.apisClima.*;
import domain.enums.*;
import domain.exceptions.*;
import domain.*;

//import static org.mockito.Mockito.*;

public class GuardarropaTest {

	MetaWeatherAPI weatherAPI2 = new MetaWeatherAPI();
	OpenWeatherMapAPI weatherAPI = new OpenWeatherMapAPI();
	ProveedorClima APIDeMentiritas = new MockAPI(21,23,false);
	ProveedorClima APIDeMentiritasDeInvierno = new MockAPI(10,23,false);
	Sugeridor sugeridor = new Sugeridor(weatherAPI2);
	Usuario juan = new Usuario(TipoUsuario.PREMIUM, 0);
	Guardarropa armario = new Guardarropa();
	Guardarropa otroArmario = new Guardarropa();
	Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.ALGODON).conColorPrimario(Color.ROJO).conColorSecundario(Color.AMARILLO).conAbrigo(0).crearPrenda();
	Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).conAbrigo(0).crearPrenda();
	Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.ALGODON).conAbrigo(0).crearPrenda();
	Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL).conAbrigo(0).crearPrenda();
	Prenda camperaGucci = new PrendaBuilder().conTipo(TipoPrenda.Campera).conTela(Material.ALGODON).conColorPrimario(Color.NEGRO).conAbrigo(2).crearPrenda();
	
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
		assertTrue(armario.pedirAtuendosSegun(APIDeMentiritas).isEmpty());
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
		//assertEquals(armario.pedirAtuendosSegun(APIDeMentiritas),atuendosEsperados);
		assertTrue(armario.pedirAtuendosSegun(APIDeMentiritas).contains(atuendo)
				&& armario.pedirAtuendosSegun(APIDeMentiritas).contains(atuendo2));
	}
/*	
	@Test
	public void siJuanSolicitaSusAtuendosSeObtendranDosDistintos() {
		HashSet<Prenda> atuendo = new HashSet<Prenda>(Arrays.asList(jean,gorra,zapatos,camisaCorta,camperaGucci));
		HashSet<HashSet<Prenda>> atuendosEsperados = new HashSet<HashSet<Prenda>>(Arrays.asList(atuendo));
		juan.cargarPrenda(armario, jean);
		sugeridor.setProveedorDeClima(APIDeMentiritasDeInvierno);
		assertEquals(sugeridor.sugerirPrendasPara(juan),atuendosEsperados);
	}
	*/
	
}
