import static org.junit.Assert.assertTrue;
import java.time.*;
import org.junit.Test;
import frecuenciasDeEventos.*;
import apisClima.MetaWeatherAPI;
import apisClima.MockAPI;
import apisClima.ProveedorClima;
import enums.*;

public class JobsEventosTest {/*
	//Test provisorio para asegurarse de que JobsEventos funciona para probarlo hay que
	//sacar el comentario el codigo a continuacion sacar el comentario en contador que esta
	//en evento y cambia TimeUnit.DAYS a TimeUnit.SECONDS en JobsEventos
	@Test
	public void prueba() {
		ProveedorClima metaWeather = new MetaWeatherAPI();
		Sugeridor sugeridor = new Sugeridor(metaWeather);
		Usuario juan = new Usuario(TipoUsuario.PREMIUM, 0);
		Guardarropa armario = new Guardarropa();
		Guardarropa otroArmario = new Guardarropa();
		Prenda camisaCorta = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.ALGODON).conColorPrimario(Color.ROJO).conColorSecundario(Color.AMARILLO).crearPrenda();
		Prenda zapatos = new PrendaBuilder().conTipo(TipoPrenda.Zapatos).conTela(Material.CUERO).conColorPrimario(Color.AMARILLO).crearPrenda();
		Prenda gorra= new PrendaBuilder().conTipo(TipoPrenda.Gorra).conColorPrimario(Color.NEGRO).conTela(Material.ALGODON).crearPrenda();
		Prenda jean = new PrendaBuilder().conTipo(TipoPrenda.Pantalon).conTela(Material.JEAN).conColorPrimario(Color.AZUL).crearPrenda();
		Prenda camperaGucci = new PrendaBuilder().conTipo(TipoPrenda.Campera).conTela(Material.ALGODON).conColorPrimario(Color.NEGRO).crearPrenda();
		Prenda buzo = new PrendaBuilder().conTipo(TipoPrenda.Buzo).conTela(Material.ALGODON).conColorPrimario(Color.VERDE).crearPrenda();
		Prenda camisaCorta2 = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.ALGODON).conColorPrimario(Color.ROJO).conColorSecundario(Color.AMARILLO).crearPrenda();
		Prenda camisaCorta3 = new PrendaBuilder().conTipo(TipoPrenda.CamisaMangaCorta).conTela(Material.ALGODON).conColorPrimario(Color.ROJO).conColorSecundario(Color.AMARILLO).crearPrenda();
		Prenda camperaGucci2 = new PrendaBuilder().conTipo(TipoPrenda.Campera).conTela(Material.ALGODON).conColorPrimario(Color.NEGRO).crearPrenda();
		camisaCorta.setNivelAbrigo(0);
		camperaGucci.setNivelAbrigo(3);
		camperaGucci2.setNivelAbrigo(3);
		buzo.setNivelAbrigo(0);
		juan.agregarGuardarropa(armario);
		juan.cargarPrenda(armario, camisaCorta);
		juan.cargarPrenda(armario,zapatos);
		juan.cargarPrenda(armario,gorra);
		juan.cargarPrenda(armario,camperaGucci);
		juan.cargarPrenda(armario, jean);
		juan.cargarPrenda(armario, buzo);
		juan.cargarPrenda(armario, camperaGucci2);
		LocalDateTime fechaActual = LocalDateTime.now();
		Evento evento = new Evento(LocalDateTime.of(LocalDate.of(2019,Month.AUGUST,13),LocalTime.now()),sugeridor, new FrecuenciaUnicaVez());
		JobsUsuarios jobs= new JobsUsuarios();	
		juan.agendarEvento(evento);
		
		assertTrue(evento.contador() ==0);
		assertTrue(evento.esProximo(fechaActual));
		jobs.run();
		System.out.println(evento.contador());
		//assertTrue(RepositorioDeUsuarios.getInstance().eventos().size()==1); 
	}*/
}
