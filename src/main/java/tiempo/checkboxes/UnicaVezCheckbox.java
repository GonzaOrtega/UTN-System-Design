package tiempo.checkboxes;

import java.util.HashMap;

import domain.frecuenciasDeEventos.FrecuenciaAnual;
import domain.frecuenciasDeEventos.FrecuenciaDeEvento;
import domain.frecuenciasDeEventos.FrecuenciaUnicaVez;
import spark.Request;

public class UnicaVezCheckbox implements Tiempo{
	public Integer mes = 1;
	public Integer dia = 1;
	public Integer anio = 1;
	boolean esUnicaVez = true;
	boolean error = false;
	
	public FrecuenciaDeEvento obtenerFrecuencia(Request req, HashMap<String, Object> viewModel) {
		this.vincularWeb(req, viewModel);
		if(!error) {
			return new FrecuenciaUnicaVez(anio, mes, dia);
		}
		return null;
	}
	
	public boolean verificarTiempo(String tiempo) {
		return tiempo.equals("UnicaVez");
	}
	
	public void esPeriodico(HashMap<String, Object> viewModel) {
		viewModel.put("esUnicaVez", esUnicaVez);
	}
	
	public void vincularWeb(Request req, HashMap<String, Object> viewModel) {
		String mesString = req.queryParams("mes");
		String diaString = req.queryParams("dia");
		String anioString = req.queryParams("anio");
		viewModel.put("mes", mesString);
		viewModel.put("dia", diaString);
		viewModel.put("anio", anioString);
		if(!this.seIngresoFecha(mesString, diaString, anioString)) {
			noRecibioFechaPorAhora();
		}else {
			try {
				mes = Integer.parseInt(mesString);
				dia = Integer.parseInt(diaString);
				anio = Integer.parseInt(anioString);
				if(!this.validarFecha())
					setError(viewModel);
			}catch(Exception e){
				setError(viewModel);
			}
		}
	}
	
	public boolean seIngresoFecha(String mesString, String diaString, String anioString) {
		return mesString != null && diaString != null && anioString != null;
	}
	
	public boolean validarFecha() {
		return (mes >=1 && mes <=12) && (dia>=1 && dia<=30);
	}
	
	public void noRecibioFechaPorAhora() {
		error = true;
	}
	
	public void setError(HashMap<String, Object> viewModel) {
		error = true;
		viewModel.put("fechaIncorrecta", true);
	}
}
