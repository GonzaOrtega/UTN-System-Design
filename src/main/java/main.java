import java.time.*;
import java.util.Date;
import apisClima.*;
import enums.*;
import frecuenciasDeEventos.*;

public class main {
	public static void main(String args[]) {
		System.out.println("Hola :)");
		ProveedorClima metaWeather = new MetaWeatherAPI();
		ProveedorClima openWeatherMap = new OpenWeatherMapAPI();
		ProveedorClima mock = new MockAPI(13,23,true);
		/*System.out.println("OpenWeatherMap:");
		System.out.println("Lluvias fuertes: "+openWeatherMap.lluviasFuertes());
		System.out.println("Velocidad viento: "+openWeatherMap.velocidadViento());
		System.out.println("¿Vientos fuertes?:"+openWeatherMap.vientosFuertes());
		System.out.println("MetaWeather:");
		System.out.println("Lluvias fuertes: "+metaWeather.lluviasFuertes());
		System.out.println("Velocidad viento: "+metaWeather.velocidadViento());
		System.out.println("¿Vientos fuertes?:"+metaWeather.vientosFuertes());*/
		System.out.println("--------------------");
		Sugeridor sugeridor = new Sugeridor(mock);
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
		Usuario karen = new Usuario(TipoUsuario.PREMIUM,0);
		LocalDateTime _20190812= LocalDateTime.of(2019, 8, 12,0,0);
		LocalDateTime fechaActual = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		Evento evento = new Evento(_20190812,sugeridor,new FrecuenciaUnicaVez());
		
		juan.agendarEvento(evento);
		karen.agendarEvento(evento);
		RepositorioDeUsuarios.getInstance().agregar(juan);
		System.out.println("¿Es proximo? "+evento.esProximo(fechaActual));

		System.out.println("Job ejecutandose..");
		JobsUsuarios job = new JobsUsuarios();
		job.run();
		System.out.println("Job finalizado.");


		System.out.println("--------------------");
		System.out.println("Adio'");
	}
}
