import static org.junit.Assert.*;

import openweathermap.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

//import static org.mockito.Mockito.*;

public class OpenWeatherMapAPITest {

	OpenWeatherMapAPI weatherAPI;
	
	@Before
	public void setUp() throws Exception {
		weatherAPI = new OpenWeatherMapAPI();
	}


}
