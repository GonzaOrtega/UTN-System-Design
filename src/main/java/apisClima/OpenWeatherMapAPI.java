package apisClima;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import openweathermap.ClimaOpenweathermap;
import openweathermap.RetrofitUsersService;
import exceptions.*;

public class OpenWeatherMapAPI implements ProveedorClima{	

	private int COUNTRY_ID = 3433955;
	private String APP_ID = "840f5cd8488cbd8d00decbd2bb8cd6a0";

	Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RetrofitUsersService service = retrofit.create(RetrofitUsersService.class);
    Call<ClimaOpenweathermap> call = service.getTemperatura(APP_ID, COUNTRY_ID);

    
    public double temperatura() {
    	try{
        	Response<ClimaOpenweathermap> response = call.execute();
        	ClimaOpenweathermap user = response.body();
        	return user.getTemperatura();

    	}
    	catch (Exception ex){
    		throw new ErrorConAPIException(ex.getMessage());
    	}
    }

}
