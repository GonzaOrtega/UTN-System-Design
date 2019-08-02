import org.junit.Before;

import apisClima.OpenWeatherMapAPI;
import exceptions.*;

public class OpenWeatherMapAPITest {

	OpenWeatherMapAPI weatherAPI;
	
	@Before
	public void setUp() throws Exception {
		weatherAPI = new OpenWeatherMapAPI();
	}


}
