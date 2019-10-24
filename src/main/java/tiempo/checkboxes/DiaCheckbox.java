package tiempo.checkboxes;

import java.util.HashMap;

import domain.frecuenciasDeEventos.FrecuenciaDeEvento;
import domain.frecuenciasDeEventos.FrecuenciaDiaria;
import spark.Request;

public class DiaCheckbox implements Tiempo{
	public Integer hora = 1; // Establezco hora default
	boolean esDiario = true;
	boolean error = false;
	
	public FrecuenciaDeEvento obtenerFrecuencia(Request req, HashMap<String, Object> viewModel) {
		this.vincularWeb(req, viewModel);
		if(!error) {
			return new FrecuenciaDiaria(hora);
		}
		return null;
	}
	
	public boolean verificarTiempo(String tiempo) {
		return tiempo.equals("Diaria");
	}
	
	public void esPeriodico(HashMap<String, Object> viewModel) {
		viewModel.put("esDiaria", esDiario);
	}
	
	public void vincularWeb(Request req, HashMap<String, Object> viewModel) {
		String horaString = req.queryParams("hora");
		viewModel.put("hora", horaString);
		if(horaString == null) {
			noRecibioFechaPorAhora();
		}else {
			try {
				hora = Integer.parseInt(horaString);
				if(!this.validarFecha())
					setError(viewModel);
			}catch(Exception e){
				setError(viewModel);
			}
		}
	}
	
	public boolean validarFecha() {
		return hora >=0 && hora <=24;
	}
	
	public void noRecibioFechaPorAhora() {
		error = true;
	}
	
	public void setError(HashMap<String, Object> viewModel) {
		error = true;
		viewModel.put("fechaIncorrecta", true);
	}
	
}