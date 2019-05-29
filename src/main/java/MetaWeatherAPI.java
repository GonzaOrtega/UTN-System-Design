import com.sun.jersey.api.client.ClientResponse;

import api.ClimaMetaWeather;
import api.JsonFactory;
import api.RequestServiceMetaWeather;

public class MetaWeatherAPI implements ProveedorClima {

	public double temperatura() {
		
		RequestServiceMetaWeather requester = new RequestServiceMetaWeather();

		ClientResponse response = requester.getInfoAPI();

		String json = response.getEntity(String.class);

		JsonFactory jsonFactory = new JsonFactory();
		
		ClimaMetaWeather clima = jsonFactory.fromJson(json,ClimaMetaWeather.class);
		
		return clima.getConsolidatedWeather().get(0).getTheTemp();
	}

}
