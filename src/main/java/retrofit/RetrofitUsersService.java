package retrofit;

import openweathermap.ClimaOpenweathermap;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitUsersService {
	
    @GET("weather")
    Call<ClimaOpenweathermap> getTemperatura(
    		@Query("appid") String appid,
    		@Query("id") int id);
   
}
