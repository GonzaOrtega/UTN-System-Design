import org.junit.Before;
import domain.apisClima.OpenWeatherMapAPI;
import domain.exceptions.*;

public class OpenWeatherMapAPITest {

	OpenWeatherMapAPI weatherAPI;
	
	@Before
	public void setUp() throws Exception {
		weatherAPI = new OpenWeatherMapAPI();
	}


}
