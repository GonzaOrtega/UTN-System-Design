import static org.junit.Assert.assertEquals;
import apisClima.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class main {
	public static void main(String args[]) {
		System.out.println("Hola :)");
		ProveedorClima metaWeather = new MetaWeatherAPI();
		ProveedorClima openWeatherMap = new OpenWeatherMapAPI();
		//System.out.println("La temperatura es de: "+temp);
		System.out.println("OpenWeatherMap:");
		System.out.println("Lluvias fuertes: "+openWeatherMap.lluviasFuertes());
		System.out.println("Velocidad viento: "+openWeatherMap.velocidadViento());
		System.out.println("¿Vientos fuertes?:"+openWeatherMap.vientosFuertes());
		System.out.println("MetaWeather:");
		System.out.println("Lluvias fuertes: "+metaWeather.lluviasFuertes());
		System.out.println("Velocidad viento: "+metaWeather.velocidadViento());
		System.out.println("¿Vientos fuertes?:"+metaWeather.vientosFuertes());
		System.out.println("Adio'");
	}
}
