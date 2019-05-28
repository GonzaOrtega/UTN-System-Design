import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import javax.ws.rs.BadRequestException;

import api.ResponseUser;
import api.RetrofitUsersService;
import openweathermap.ClimaOpenweathermap;

public class OpenWeatherMapAPI implements ProveedorClima{	

	private int COUNTRY_ID = 3433955;
	private String APP_ID = "840f5cd8488cbd8d00decbd2bb8cd6a0";
    private Response<ClimaOpenweathermap> respuesta;
	
	public void setRespuesta(Response<ClimaOpenweathermap> respuesta) {
		this.respuesta = respuesta;
	}

	public Response<ClimaOpenweathermap> getRespuesta() {
		return respuesta;
	}

	Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RetrofitUsersService service = retrofit.create(RetrofitUsersService.class);
    
	public void obtenerTemperaturaDesdeAPI() throws IOException {
		 Call<ClimaOpenweathermap> call = service.getTemperatura(APP_ID, COUNTRY_ID);
		 this.setRespuesta(call.execute());
	}
    
	public double temperatura() {
		ClimaOpenweathermap clima = this.getRespuesta().body();
        return clima.getTemperatura()-273;
    }   

}
