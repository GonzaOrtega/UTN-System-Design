package climaMetaWeather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"the_temp"
})

public class ClimaGeneral {
	@JsonProperty("the_temp")
	private Double theTemp;
	
	@JsonProperty("the_temp")
	public Double getTheTemp() {
	return theTemp;
	}

	@JsonProperty("the_temp")
	public void setTheTemp(Double theTemp) {
	this.theTemp = theTemp;
	}
}
