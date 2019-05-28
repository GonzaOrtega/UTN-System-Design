import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import openweathermap.ClimaOpenweathermap;

public class ErrorConAPIException extends BadRequestException{

	public ErrorConAPIException(String mensaje ,retrofit2.Response<ClimaOpenweathermap> response){
		super();
	}
	
	
}
